package com.training.interceptor;

import com.training.model.Account;
import com.training.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

@Component
public class AuthenticationInterceptor implements HandlerInterceptor {

    @Autowired
    private AccountService service;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("pre");
        String tokenAuthentication = request.getParameter("token");
        if(tokenAuthentication == null) {
            response.getWriter().write("You must attach token");
            return false;
        }

        String[] account = tokenAuthentication.split("-");

        Optional<Account> accountData = service.findAccount(account[0], account[1]);
        if (accountData.isPresent()) {
            response.getWriter().write("Login success!");
            return true;
        }

        response.getWriter().write("Login failed!");
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        System.out.println("post");
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        System.out.println("after");
    }
}
