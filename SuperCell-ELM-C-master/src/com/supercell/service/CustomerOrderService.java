package com.supercell.service;

import java.util.List;
import java.util.Map;

import com.supercell.entity.Complaint;
import com.supercell.entity.CustomerOrder;
import com.supercell.entity.OrderItem;
import com.supercell.misc.data.*;

public interface CustomerOrderService {

	Boolean makeOrder(Integer customerId,List<CartItem> cartItems);

    Map<CustomerOrder, List<OrderItem>> getOrderHistory(Integer customerId);

    
    List<OrderToShow> getOrder(Integer customerId,Integer state);

    Boolean acknowledgeOrder(Integer orderId, Double rating);

    Boolean complain(Integer orderId, String message);

    CustomerOrder getOrder(Integer orderId);

	OrderDetails viewOrderDetails(Integer orderId);
	
	OrderToPreview previewOrder(Integer customerId,List<CartItem> cartItems);

    List<ComplaintDetails> viewComplaints(Integer customerId);


}
