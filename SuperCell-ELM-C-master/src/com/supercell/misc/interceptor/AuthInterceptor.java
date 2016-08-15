package com.supercell.misc.interceptor;

import com.supercell.entity.Customer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.interceptor.Interceptor;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AuthInterceptor extends HandlerInterceptorAdapter {
    @Value("${Customer}")
    private String user;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Customer user = (Customer) request.getSession().getAttribute(this.user);
        if (
                (user == null) &&
                        !(request.getRemoteAddr().contentEquals("10.222.232.30")) &&
                        !(request.getRemoteHost().contentEquals("10.222.232.30"))) {
            response.sendRedirect("/SuperCell-ELM-C");
            return false;
        } else {
            return true;
        }
    }
}
