package me.dongthanh.estore.admin.controller;

import java.io.File;
import java.util.List;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import me.dongthanh.estore.dao.ProductDAO;
import me.dongthanh.estore.entity.Product;

@Controller
public class ProductManager {
	@Autowired
	ProductDAO dao;
	
	@Autowired
	ServletContext app;
	
	@RequestMapping("/admin/product/index")
	public String index(Model model) {
		
		Product entity = new Product();
		List<Product> list = dao.findAll();
		model.addAttribute("entity", entity);
		model.addAttribute("list", list);
		return "admin/product/index";
	}
	
	@RequestMapping("/admin/product/edit/{id}")
	public String edit(Model model,
			@PathVariable("id") int id) {
		
		Product entity = dao.findById(id);
		List<Product> list = dao.findAll();
		model.addAttribute("entity", entity);
		model.addAttribute("list", list);
		return "admin/product/index";		

	}
	
	@RequestMapping("/admin/product/create")
	public String create(
			@ModelAttribute("entity") Product entity,
			@RequestParam("image_file") MultipartFile file,
			RedirectAttributes model) throws Exception {
		
		if(file.isEmpty()) {
			entity.setImage("user.png");
		}else {
			entity.setImage(file.getOriginalFilename());
			String path = app.getRealPath("/static/images/products/"+ entity.getImage());
			file.transferTo(new File(path));
		}
		try {
			dao.create(entity);	
			model.addAttribute("message", "Thêm mới thành công!");	
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("message", "Thêm mới thất bại!");	
		}
					
		return "redirect:/admin/product/index";
	}
	
	@RequestMapping("/admin/product/update")
	public String update(
			@ModelAttribute("entity") Product entity,
			@RequestParam("image_file") MultipartFile file,
			RedirectAttributes model) throws Exception {
		
		if(!file.isEmpty()) {
			entity.setImage(file.getOriginalFilename());
			String path = app.getRealPath("/static/images/products/"+ entity.getImage());
			file.transferTo(new File(path));
		}
		
		dao.update(entity);
		model.addAttribute("message", "Cập nhật thành công!");
		return "redirect:/admin/product/edit/"+entity.getId();
	}
	
	@RequestMapping(value={"/admin/product/delete/{id}"})
	public String delete(
			@PathVariable("id") int id,
			RedirectAttributes model) {
		
		dao.delete(id);
		model.addAttribute("message", "Xóa thành công!");
		return "redirect:/admin/product/index";
	}
}
