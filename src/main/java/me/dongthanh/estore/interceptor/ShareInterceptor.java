package me.dongthanh.estore.interceptor;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import me.dongthanh.estore.dao.CategoryDAO;
import me.dongthanh.estore.entity.Category;

@Component
public class ShareInterceptor extends HandlerInterceptorAdapter {
	@Autowired
	CategoryDAO dao;	
	
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		List<Category> list = dao.findAll();
		request.setAttribute("cates", list);
		
	}
}
