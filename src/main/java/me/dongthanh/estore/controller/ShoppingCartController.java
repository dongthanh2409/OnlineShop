package me.dongthanh.estore.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import me.dongthanh.estore.service.CartService;

@Controller
public class ShoppingCartController {
	@Autowired
	CartService cart;
	
	@ResponseBody
	@RequestMapping("/cart/add/{id}") 
	public Object[] add(@PathVariable("id") int id) {
		cart.add(id);
		Object[] info = {
				cart.getCount(),
				cart.getAmount()
		};
		
		return info;
	}
	
	@RequestMapping("/cart/view")
	public String view(Model model) {
		Map<Integer, Integer> mapMaxQty = cart.maxQty();
		model.addAttribute("maxQty", mapMaxQty);
		return "cart/view";
	}
	
	@ResponseBody
	@RequestMapping("/cart/clear") 
	public void clear() {
		cart.clear();		
	}
	
	@ResponseBody
	@RequestMapping("/cart/remove/{id}") 
	public Object[] remove(@PathVariable("id") int id) {
		cart.remove(id);
		Object[] info = {
				cart.getCount(),
				cart.getAmount()
		};
		
		return info;
	}
	
	@ResponseBody
	@RequestMapping("/cart/update/{id}/{qty}") 
	public Object[] update(
			@PathVariable("id") int id, 
			@PathVariable("qty") int qty) {
		cart.update(id, qty);
		Object[] info = {
				cart.getCount(),
				cart.getAmount()
		};
		
		return info;
	}
}
