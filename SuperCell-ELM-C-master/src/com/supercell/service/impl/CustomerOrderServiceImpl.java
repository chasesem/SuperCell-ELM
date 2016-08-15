package com.supercell.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;

import com.supercell.dao.*;
import com.supercell.entity.*;
import com.supercell.misc.data.*;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;

import com.supercell.service.CustomerOrderService;
import com.supercell.service.JMSProducerService;
import com.supercell.misc.JSONUtil;
import com.supercell.misc.enums.OrderState;

import static org.hibernate.criterion.Restrictions.eq;
import static org.hibernate.criterion.Restrictions.ne;

@Service
public class CustomerOrderServiceImpl implements CustomerOrderService {

    @Resource
    private CustomerOrderDao customerOrderDao;
    @Resource
    private OrderItemDao orderItemDao;
    @Resource
    private CustomerDao customerDao;
    @Resource
    private DishesDao dishesDao;
    @Resource
    private MerchantDao merchantDao;
    @Resource
    private ComplaintDao complaintDao;
    @Resource
    private JMSProducerService jmsProduceService;

    //更新后的接口

    @Override
    public Map<CustomerOrder, List<OrderItem>> getOrderHistory(Integer customerId) {
        Map<CustomerOrder, List<OrderItem>> map = new HashMap<CustomerOrder, List<OrderItem>>();
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(CustomerOrder.class);
        detachedCriteria.add(eq(CustomerOrder.USER_ID, customerId));
        List<CustomerOrder> customerOrdersList = customerOrderDao.search(detachedCriteria);

        for (CustomerOrder order : customerOrdersList) {
            DetachedCriteria orderItemDC = DetachedCriteria.forClass(OrderItem.class);
            detachedCriteria.add(eq(OrderItem.ORDER_ID, order.getId()));
            List<OrderItem> orderItemsList = orderItemDao.search(orderItemDC);
            map.put(order, orderItemsList);
        }
        return map;
    }

    @Override
    public Boolean acknowledgeOrder(Integer orderId, Double rating) {
        CustomerOrder orderToConfirm = customerOrderDao.get(orderId);
        orderToConfirm.setState(OrderState.CUSTOMER_ACKNOWLEDGED);
        orderToConfirm.setRating(rating);

        try {
            customerOrderDao.update(orderToConfirm);

            Merchant merchant = merchantDao.get(orderToConfirm.getMerchantId());
            Integer numOfOrders = merchant.getNumberOfOrders();
            Double totalRating = merchant.getRating();
            Double newRating = (numOfOrders * totalRating + rating) / (numOfOrders + 1);

            merchant.setNumberOfOrders(numOfOrders + 1);
            merchant.setRating(newRating);
            merchantDao.update(merchant);

            if (newRating < 5 && (numOfOrders+1) % 2 == 0) {
                LowRating lowRating = new LowRating();
                lowRating.setMerchantId(merchant.getId());
                lowRating.setRating(newRating);
                jmsProduceService.sendMerchantRatingToAdmin(JSONUtil.convertToJSON(lowRating));
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public Boolean complain(Integer orderId, String message) {
        DetachedCriteria complaintDC = DetachedCriteria.forClass(Complaint.class);
        complaintDC.add(eq(Complaint.ORDER_ID, orderId));
        if (complaintDao.search(complaintDC).isEmpty() == false) {
            return false;
        } else {
            CustomerOrder customerOrder = customerOrderDao.get(orderId);
            Complaint complaint = new Complaint();
            complaint.setComplainMessage(message);
            Integer customerId = customerOrder.getUserId();
            Customer customer = customerDao.get(customerId);
            complaint.setCustomerId(customerId);
            Integer merchantId = customerOrder.getMerchantId();
            Merchant merchant = merchantDao.get(merchantId);
            complaint.setMerchantId(merchantId);
            complaint.setPhoneNumber(customer.getPhoneNumber());
            complaint.setMerchantName(merchant.getShopName());
            complaint.setOrderID(orderId);
            try {
                Integer complaintId = (Integer) complaintDao.save(complaint);
                if (complaintId != null) {
                    Complaint complaint1 = complaintDao.get(complaintId);
                    Complain complain = new Complain();
                    complain.setComplainState(1);
                    complain.setOrderId(complaint1.getOrderId());
                    complain.setComplainMessage(complaint1.getComplainMessage());
                    complain.setCustomerId(complaint1.getCustomerId());
                    complain.setMerchantId(complaint1.getMerchantId());
                    complain.setMerchantName(complaint1.getMerchantName());
                    complain.setPhoneNumber(complaint1.getPhoneNumber());
                    jmsProduceService.sendCustomerComplaintToAdmin(JSONUtil.convertToJSON(complain));
                    return true;
                } else return false;
            } catch (Exception e) {
                return false;
            }
        }
    }

    @Override
    public CustomerOrder getOrder(Integer orderId) {
        return null;
    }

    @Override
    public Boolean makeOrder(Integer customerId, List<CartItem> cartItems) {

        Integer merchantId = dishesDao.get(cartItems.get(0).getDishesId()).getMerchantId();
        CustomerOrder customerOrder = new CustomerOrder();

        String merchantCheckURL = "http://10.222.232.30:8080/SuperCell-ELM-A/merchantmanage/getSimpleMerchantState.do/" + merchantId;
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(merchantCheckURL);
        Response resp = target.request().get();
        String merchantCheckString = resp.readEntity(String.class);
        client.close();
        MerchantCheck merchantCheck = (MerchantCheck) JSONUtil.convertToObject(merchantCheckString, MerchantCheck.class);
        Integer merchantState = merchantCheck.getMerchantState();
        if (merchantState == 1) {
            Customer customer = customerDao.get(customerId);
            Integer total = 0;
            for (CartItem cartItem : cartItems) {
                Dishes dish = dishesDao.get(cartItem.getDishesId());
                total += dish.getPrice() * cartItem.getCount();
            }
            customerOrder.setAddress(customer.getAddress());
            customerOrder.setDateOfOrder(new Date());
            customerOrder.setRating(10D);
            customerOrder.setState(OrderState.MERCHANT_NOT_ACKNOWLEDGED);
            customerOrder.setUserId(customerId);
            customerOrder.setTotal(total);
            customerOrder.setMerchantId(merchantId);
            try {
                Integer customerOrderId = (Integer) customerOrderDao.save(customerOrder);
                if (customerOrderId != null) {
                    for (CartItem cartItem : cartItems) {
                        OrderItem orderItem = new OrderItem();
                        orderItem.setCount(cartItem.getCount());
                        orderItem.setDishesId(cartItem.getDishesId());
                        orderItem.setOrderId(customerOrderId);
                        orderItemDao.save(orderItem);
                    }
                    return true;
                }
                return null;
            } catch (Exception e) {
                return false;
            }
        } else {
            return false;
        }

    }

    @Override
    public List<OrderToShow> getOrder(Integer customerId, Integer state) {
        List<OrderToShow> list = new ArrayList<>();
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(CustomerOrder.class);
        detachedCriteria.add(eq(CustomerOrder.USER_ID, customerId));
        detachedCriteria.addOrder(Order.desc(CustomerOrder.DATE_OF_ORDER));
        if (state == 2) {
            detachedCriteria.add(eq(CustomerOrder.STATE, OrderState.CUSTOMER_ACKNOWLEDGED));
        } else if (state == 0) {
            detachedCriteria.add(eq(CustomerOrder.STATE, OrderState.MERCHANT_NOT_ACKNOWLEDGED));
        } else if (state == 1) {
            detachedCriteria.add(eq(CustomerOrder.STATE, OrderState.MERCHANT_ACKNOWLEDGED));
        } else if (state == 3) {
            detachedCriteria.add(eq(CustomerOrder.STATE, OrderState.MERCHANT_REFUSED));
        }

        List<CustomerOrder> customerOrdersList = customerOrderDao.search(detachedCriteria);
        for (CustomerOrder customerOrder : customerOrdersList) {
            OrderToShow orderToShow = new OrderToShow();
            orderToShow.setDateOfOrder(customerOrder.getDateOfOrder());
            Integer orderId = customerOrder.getId();
            orderToShow.setOrderId(orderId);
            orderToShow.setTotal(customerOrder.getTotal());
            orderToShow.setState(customerOrder.getState());
            DetachedCriteria complaintDC = DetachedCriteria.forClass(Complaint.class);
            complaintDC.add(eq(Complaint.ORDER_ID, orderId));
            if (complaintDao.search(complaintDC).isEmpty() == true) {
                orderToShow.setComplaintState(0);
            } else {
                orderToShow.setComplaintState(1);
            }
            Integer merchantId = customerOrder.getMerchantId();
            Merchant merchant = merchantDao.get(merchantId);
            orderToShow.setShopName(merchant.getShopName());
            orderToShow.setShopPicPath(merchant.getShopPicPath());
            list.add(orderToShow);
        }
        return list;
    }

    @Override
    public OrderDetails viewOrderDetails(Integer orderId) {
        CustomerOrder customerOrder = customerOrderDao.get(orderId);
        OrderDetails orderDetails = new OrderDetails();
        orderDetails.setDateOfOrder(customerOrder.getDateOfOrder());
        orderDetails.setOrderId(customerOrder.getId());
        orderDetails.setTotal(customerOrder.getTotal());
        Customer customer = customerDao.get(customerOrder.getUserId());
        orderDetails.setUsername(customer.getPhoneNumber());
        orderDetails.setAddress(customer.getAddress());
        Merchant merchant = merchantDao.get(customerOrder.getMerchantId());
        orderDetails.setShopName(merchant.getShopName());
        orderDetails.setShopPicPath(merchant.getShopPicPath());
        orderDetails.setState(customerOrder.getState());
        DetachedCriteria complaintDC = DetachedCriteria.forClass((Complaint.class));
        complaintDC.add(eq(Complaint.ORDER_ID, orderId));
        if (complaintDao.search(complaintDC).isEmpty() == false) {
            Complaint complaint = complaintDao.search(complaintDC).get(0);
            orderDetails.setComplaintMessage(complaint.getComplainMessage());
        } else {
            orderDetails.setComplaintMessage(" ");
        }
        orderDetails.setMerchantId(merchant.getId());
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(OrderItem.class);
        detachedCriteria.add(eq(OrderItem.ORDER_ID, orderId));
        List<OrderItem> orderItemsList = orderItemDao.search(detachedCriteria);
        List<OrderItemDetails> orderItemDetailsList = new ArrayList<>();
        for (OrderItem orderItem : orderItemsList) {
            OrderItemDetails orderItemDetails = new OrderItemDetails();
            orderItemDetails.setCount(orderItem.getCount());
            Dishes dishes = dishesDao.get(orderItem.getDishesId());
            orderItemDetails.setDishesName(dishes.getDishesName());
            orderItemDetails.setPrice(dishes.getPrice());
            orderItemDetailsList.add(orderItemDetails);
        }
        orderDetails.setOrderItemsList(orderItemDetailsList);
        return orderDetails;
    }

    @Override
    public OrderToPreview previewOrder(Integer customerId, List<CartItem> cartItems) {
        OrderToPreview otp = new OrderToPreview();
        Customer customer = customerDao.get(customerId);
        String username = customer.getPhoneNumber();
        String address = customer.getAddress();
        if (cartItems.isEmpty() != true) {
            CartItem tCartItem = cartItems.get(0);
            Dishes tDishes = dishesDao.get(tCartItem.getDishesId());
            Integer tMerchantId = tDishes.getMerchantId();
            Merchant tMerchant = merchantDao.get(tMerchantId);
            String shopName = tMerchant.getShopName();
            List<OrderItemDetails> oidList = new ArrayList<>();
            Integer total = 0;
            for (CartItem cartItem : cartItems) {
                OrderItemDetails oid = new OrderItemDetails();
                Dishes dishes = dishesDao.get(cartItem.getDishesId());
                oid.setDishesName(dishes.getDishesName());
                oid.setPrice(dishes.getPrice());
                oid.setCount(cartItem.getCount());
                total += oid.getCount() * oid.getPrice();
                oidList.add(oid);
            }
            otp.setAddress(address);
            otp.setOipList(oidList);
            otp.setMerchantId(tMerchantId);
            otp.setShopName(shopName);
            otp.setTotal(total);
            otp.setUsername(username);
            return otp;
        }
        return null;
    }

    @Override
    public List<ComplaintDetails> viewComplaints(Integer customerId) {
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Complaint.class);
        detachedCriteria.add(eq(Complaint.CUSTOMER_ID, customerId));
        List<Complaint> complaintsList = complaintDao.search(detachedCriteria);
        List<ComplaintDetails> complaintDetailsList = new ArrayList<>();
        for (Complaint complaint : complaintsList) {
            ComplaintDetails complaintDetails = new ComplaintDetails();
            Merchant merchant = merchantDao.get(complaint.getMerchantId());
            CustomerOrder customerOrder = customerOrderDao.get(complaint.getOrderId());
            complaintDetails.setComplainMessage(complaint.getComplainMessage());
            complaintDetails.setCustomerId(complaint.getCustomerId());
            complaintDetails.setDateOfOrder(customerOrder.getDateOfOrder());
            complaintDetails.setMerchantId(complaint.getMerchantId());
            complaintDetails.setMerchantName(complaint.getMerchantName());
            Integer orderId = customerOrder.getId();
            complaintDetails.setOrderId(orderId);
            complaintDetails.setPhoneNumber(complaint.getPhoneNumber());
            complaintDetails.setShopPicPath(merchant.getShopPicPath());
            DetachedCriteria orderItemDC = DetachedCriteria.forClass(OrderItem.class);
            orderItemDC.add(eq(OrderItem.ORDER_ID, customerOrder.getId()));
            List<OrderItem> orderItemsList = orderItemDao.search(orderItemDC);
            List<OrderItemDetails> oidList = new ArrayList<>();
            for (OrderItem orderItem : orderItemsList) {
                Dishes dishes = dishesDao.get(orderItem.getDishesId());
                OrderItemDetails orderItemDetails = new OrderItemDetails();
                orderItemDetails.setCount(orderItem.getCount());
                orderItemDetails.setDishesName(dishes.getDishesName());
                orderItemDetails.setPrice(dishes.getPrice());
                oidList.add(orderItemDetails);
            }
            complaintDetails.setOidList(oidList);
            String url = "http://10.222.232.30:8080/SuperCell-ELM-A/merchantmanage/getComplaintState.do/" + orderId;
            Client client = ClientBuilder.newClient();
            WebTarget target = client.target(url);
            Response resp = target.request().get();
            String complaintString = resp.readEntity(String.class);
            client.close();
            Complain complain = (Complain) JSONUtil.convertToObject(complaintString, Complain.class);
            complaintDetails.setState(complain.getComplainState());
            complaintDetailsList.add(complaintDetails);
        }

        return complaintDetailsList;
    }
}
