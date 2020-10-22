package me.dongthanh.estore.admin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import me.dongthanh.estore.dao.OrderDAO;
import me.dongthanh.estore.dao.OrderDetailDAO;
import me.dongthanh.estore.dao.ProductDAO;
import me.dongthanh.estore.entity.Order;
import me.dongthanh.estore.entity.OrderDetail;
import me.dongthanh.estore.entity.Product;

@Controller
public class OrderManager {
	@Autowired
	OrderDAO dao;
	
	@Autowired
	OrderDetailDAO ddao;
	
	@Autowired
	ProductDAO pdao;
	
	@RequestMapping("/admin/order/index")
	public String index(Model model) {
		
		Order entity = new Order();

		model.addAttribute("entity", entity);
		System.out.println(entity.getCompleted());
		model.addAttribute("list", dao.findAll());
		model.addAttribute("details", ddao.findByOrder(entity));
		return "admin/order/index";
	}
	
	@RequestMapping("/admin/order/edit/{id}")
	public String edit(Model model,
			@PathVariable("id") int id) {
		
		Order entity = dao.findById(id);
		
		model.addAttribute("entity", entity);
		model.addAttribute("details", ddao.findByOrder(entity));
		model.addAttribute("list", dao.findAll());
		return "admin/order/index";		

	}
	
	@RequestMapping("/admin/order/create")
	public String create(
			@ModelAttribute("entity") Order entity,
			RedirectAttributes model) {
			
			dao.create(entity);	
			model.addAttribute("message", "Thêm mới thành công!");
		
		return "redirect:/admin/order/index";
	}
	
	@RequestMapping("/admin/order/update")
	public String update(
			@ModelAttribute("entity") Order entity,
			RedirectAttributes model) {
		if(entity.getCompleted()==true) {
			List<OrderDetail> details = ddao.findByOrder(entity);
			for(OrderDetail detail : details) {
				int productId = detail.getProduct().getId();
				int productOrderdQty = detail.getQuantity();
				Product product = pdao.findById(productId);
				product.setQuantity(product.getQuantity()- productOrderdQty);
				pdao.update(product);
			}
		}
		
		dao.update(entity);
		model.addAttribute("message", "Cập nhật thành công!");
		return "redirect:/admin/order/edit/"+entity.getId();
	}
	
	@RequestMapping(value={"/admin/order/delete","/admin/order/delete/{id}"})
	public String delete(
			@PathVariable("id") Integer id,
			RedirectAttributes model) {
		List<OrderDetail> list = ddao.findByOrder(dao.findById(id));
		for(OrderDetail detail: list) {
			ddao.delete(detail.getId());
		}
		dao.delete(id);
		model.addAttribute("message", "Xóa thành công!");
		return "redirect:/admin/order/index";
	}
}
