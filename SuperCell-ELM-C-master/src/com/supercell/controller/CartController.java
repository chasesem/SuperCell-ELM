package com.supercell.controller;

import com.supercell.misc.data.CartItem;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

/**
 * Created by WUJO2 on 8/9/2016.
 */
@Controller
@RequestMapping(path = "cart")
public class CartController {
    @Value("${cart_items}")
    private String cartItems;

    @ResponseBody
    @RequestMapping(method = RequestMethod.GET)
    public Object getCartItems(HttpSession session) {
        return (session.getAttribute(cartItems) != null) ? session.getAttribute(cartItems) : false;
    }

    @RequestMapping(value = "gotoPreview.do", method = RequestMethod.GET)
    public String gotoPreview() {
        return "orderPreview";
    }

    @RequestMapping(value = "clear")
    public Object clearCart(HttpSession session) {
        session.removeAttribute(cartItems);
        return true;
    }

    @ResponseBody
    @SuppressWarnings("unchecked")
    @RequestMapping(value = "getUpdate/{did}/{dcount}", method = RequestMethod.PUT)
    public Object updateCartItems(@PathVariable("did") Integer did, @PathVariable("dcount") Integer dcount, HttpSession session) {
        CartItem cartItem = new CartItem();
        cartItem.setDishesId(did);
        cartItem.setCount(dcount);
        List<CartItem> cartItems = (List<CartItem>) session.getAttribute(this.cartItems);
        // 第一次添加CartItem
        if (cartItems == null) {
            if (cartItem.getCount() > 0) {
                cartItems = new ArrayList<>();
                cartItems.add(cartItem);
                session.setAttribute(this.cartItems, cartItems);
            }
        } else {
            // 是否需要添加新的菜品
            boolean addNew = true;
            Iterator<CartItem> iterator = cartItems.iterator();
            while (iterator.hasNext()) {
                CartItem item = iterator.next();
                if (Objects.equals(item.getDishesId(), cartItem.getDishesId())) {
                    addNew = false;  // 菜品已经存在
                    if (cartItem.getCount() <= 0) {
                        iterator.remove();
                        break;
                    } else {
                        item.setCount(cartItem.getCount());
                        break;
                    }
                }
            }
            if (addNew) { // 添加新菜品
                cartItems.add(cartItem);
            }
            // 菜品列表为空
            cartItems = (cartItems.isEmpty()) ? null : cartItems;
        }
        session.setAttribute(this.cartItems, cartItems);
        return cartItem;
    }
}