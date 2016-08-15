package com.supercell.controller;

import com.supercell.entity.Customer;
import com.supercell.misc.JSONUtil;
import com.supercell.service.CustomerService;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created by WUJO2 on 8/6/2016.
 */
@Controller
@RequestMapping(path = "customer")
public class CustomerController {
    @Value("${Customer}")
    private String customerAttr;
    @Value("${cart_items}")
    private String cartItems;
    @Resource
    private CustomerService customerService;

    @RequestMapping(path = "userInfo", method = RequestMethod.GET)
    public String userInfo() {
        return "userInfo";
    }

    @RequestMapping(path = "login", method = RequestMethod.GET)
    public String loginPage() {
        return "login";
    }

    @RequestMapping(path = "register", method = RequestMethod.GET)
    public String registerPage() {
        return "register";
    }

    @ResponseBody
    @RequestMapping(method = RequestMethod.GET)
    public Object getCustomer(HttpSession session) {
        Customer customer = (Customer) session.getAttribute(customerAttr);
        if (customer != null) {
            Customer info = new Customer(customer.getPhoneNumber(), null, customer.getAddress());
            info.setId(customer.getId());
            return info;
        }
        return false;
    }

    @ResponseBody
    @RequestMapping(path = "logout")
    public Boolean logout(HttpSession session) {
        session.removeAttribute(customerAttr);
        session.removeAttribute(cartItems);
        return true;
    }

    @ResponseBody
    @RequestMapping(path = "updatePassword", method = RequestMethod.POST)
    public boolean updatePassword(
            @RequestParam("originalPassword") String originalPassword,
            @RequestParam("newPassword") String newPassword,
            HttpSession session) {
        Customer customer = (Customer) session.getAttribute(customerAttr);
        // 原密码与输入的密码不一致
        if (!DigestUtils.md5Hex(originalPassword).contentEquals(customer.getPassword())) {
            return false;
        } else {
            // 更新密码
            customer.setPassword(DigestUtils.md5Hex(newPassword));
            return customerService.updateMsg(customer);
        }
    }

    @ResponseBody
    @RequestMapping(path = "updateAddress", method = RequestMethod.POST)
    public Boolean updateAddress(@RequestParam("newAddress") String newAddress, HttpSession session) {
        Customer customer = (Customer) session.getAttribute(customerAttr);
        customer.setAddress(newAddress);
        return customerService.updateMsg(customer);
    }

    @RequestMapping(value = "{phoneNumber}/{password}", method = RequestMethod.POST)
    public void login(
            @PathVariable("phoneNumber") String phoneNumber,
            @PathVariable("password") String password,
            HttpSession httpSession,
            HttpServletResponse response) {
        Customer customer = customerService.login(phoneNumber, password);
        if (customer != null) {
            httpSession.setAttribute(customerAttr, customer);
            Customer info = new Customer(customer.getPhoneNumber(), null, customer.getAddress());
            info.setId(customer.getId());
            JSONUtil.writeJSONToFrontEnd(response, JSONUtil.convertToJSON(info));
        } else {
            JSONUtil.writeJSONToFrontEnd(response, JSONUtil.convertToJSON(false));
        }
    }

    @RequestMapping(value = "{phoneNumber}/{password}/{address}", method = RequestMethod.POST)
    public void register(
            @PathVariable("phoneNumber") String phoneNumber,
            @PathVariable("password") String password,
            @PathVariable("address") String address,
            HttpSession session,
            HttpServletResponse response) {
        Customer customer = new Customer(phoneNumber, password, address);
        Integer customerId;
        if ((customerService.queryPhoneNumber(phoneNumber) == null)
                && ((customerId = customerService.register(customer)) != null)) {
            customer.setId(customerId);
            customer.setPassword(customer.getPassword());
            // session中保存用户信息
            session.setAttribute(customerAttr, customer);
            // 返回用户信息
            Customer info = new Customer(customer.getPhoneNumber(), null, customer.getAddress());
            info.setId(customerId);
            JSONUtil.writeJSONToFrontEnd(response, JSONUtil.convertToJSON(info));
        } else {
            JSONUtil.writeJSONToFrontEnd(response, JSONUtil.convertToJSON(false));
        }
    }
}
