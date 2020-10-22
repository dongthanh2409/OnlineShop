package me.dongthanh.estore.controller;

import java.util.List;

import javax.mail.MessagingException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import me.dongthanh.estore.bean.MailInfo;
import me.dongthanh.estore.dao.ProductDAO;
import me.dongthanh.estore.entity.Product;
import me.dongthanh.estore.service.CookieService;
import me.dongthanh.estore.service.MailService;

@Controller
public class ProductController {
	@Autowired
	ProductDAO pdao;
	
	@Autowired
	CookieService cookie;
	
	@Autowired
	MailService mail;
	
	@RequestMapping("/product/list-by-category/{cid}")
	public String listByCategory (Model model,
			@PathVariable("cid") int categoryId) {
		
		List<Product>list = pdao.findByCategoryId(categoryId);
		model.addAttribute("list", list);
		
		return "product/list";
	}
	
	@RequestMapping("/product/list-by-keywords")
	public String listByKeywords (Model model,
			@RequestParam("keywords") String keywords) {
		
		List<Product>list = pdao.findByKeywords(keywords);
		model.addAttribute("list", list);
		
		return "product/list";
	}
	
	@RequestMapping("/product/detail/{id}")
	public String detail (Model model,
			@PathVariable("id") int id) {
		
		//find product by id
		Product prod = pdao.findById(id);
		model.addAttribute("prod", prod);
		
		//count views
		prod.setViewCount(prod.getViewCount() + 1);
		pdao.update(prod);
		
		
		//return list products have the same category
		List<Product> list = pdao.findByCategoryId(prod.getCategory().getId());
		int index = list.indexOf(prod);
		list.remove(index);
		model.addAttribute("list", list);
		
		//return list favorite products
		Cookie favo = cookie.read("favo");
		if(favo != null) {
			String ids = favo.getValue();
			List<Product> favo_list =pdao.findByIds(ids);
			model.addAttribute("favo", favo_list);
		}
		
		//return list viewed products 
		Cookie viewed = cookie.read("viewed");
		String value = String.valueOf(id);
		if(viewed!=null) {
			value = viewed.getValue();
			if(!value.contains(String.valueOf(id))) {
				value += "," + String.valueOf(id);
			}
		}
		
		cookie.create("viewed", value, 10);		
		List<Product> viewed_list =pdao.findByIds(value);
		model.addAttribute("viewed", viewed_list);
				
		return "product/detail";
	}
	
	@ResponseBody
	@RequestMapping("/product/add-to-favo/{id}")
	public Boolean addToFavo (Model model,
			@PathVariable("id") Integer id) {
		 
		Cookie favo = cookie.read("favo");
		String value = id.toString();
		
		if(favo != null) {
			value = favo.getValue();
			if(!value.contains(id.toString())) {
				value += "," + id.toString();
			} else {
				return false;
			}
		}
		
		cookie.create("favo", value, 30);	
		
		return true;
	}
	
	
	@ResponseBody
	@RequestMapping("/product/send-to-friend")
	public Boolean sendToFriend (Model model, MailInfo info,
			HttpServletRequest request) {		
		//send mail
		info.setSubject("Thông tin hàng hóa"); 
		
		try {
			String id = request.getParameter("id");
			String link = request.getRequestURL().toString().replace("send-to-friend", "detail/" + id);
			info.setBody(info.getBody() + "\n" + link);
			mail.send(info);
			return true;
		} catch (MessagingException e) {			
			e.printStackTrace();
			return false;
		}
	}
	
	@RequestMapping("/product/list-by-special/{id}")
	public String listBySpecial (Model model,
			@PathVariable("id") int id) {
		
		List<Product>list = pdao.findBySpecial(id);
		model.addAttribute("list", list);
		
		return "product/list";
	}
}
