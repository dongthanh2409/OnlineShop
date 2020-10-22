package me.dongthanh.estore;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import me.dongthanh.estore.interceptor.AdminIntercepter;
import me.dongthanh.estore.interceptor.AuthorizeIntercepter;
import me.dongthanh.estore.interceptor.ShareInterceptor;

@Component
public class InterceptorConfig implements WebMvcConfigurer {
	@Autowired
	ShareInterceptor shareInterceptor;
	@Autowired
	AuthorizeIntercepter auth;
	@Autowired
	AdminIntercepter adm;
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(shareInterceptor).addPathPatterns("/**");
		
		registry.addInterceptor(auth)
		.addPathPatterns("/account/change", "/account/logout", "/account/edit", "/order/**" );
		
		registry.addInterceptor(adm).addPathPatterns("/admin/**");
	}
}
