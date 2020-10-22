package me.dongthanh.estore.interceptor;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import me.dongthanh.estore.entity.Customer;



@Component
public class AdminIntercepter extends HandlerInterceptorAdapter {
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		HttpSession session = request.getSession();
		Customer user = (Customer) session.getAttribute("user");
		if(user==null) {
			session.setAttribute("back-url", request.getRequestURI());
			response.sendRedirect("/account/login");
			return false;
		}
		if(user.getAdmin()==false) {
			response.sendRedirect("/home/index");
			return false;
		}
		return true;
	}
}
