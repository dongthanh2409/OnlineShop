package me.dongthanh.estore.admin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import me.dongthanh.estore.dao.CategoryDAO;
import me.dongthanh.estore.entity.Category;

@Controller
public class CategoryManager {
	@Autowired
	CategoryDAO dao;
	
	@RequestMapping("/admin/category/index")
	public String index(Model model) {
		
		Category entity = new Category();
		List<Category> list = dao.findAll();
		model.addAttribute("entity", entity);
		model.addAttribute("list", list);
		return "admin/category/index";
	}
	
	@RequestMapping("/admin/category/edit/{id}")
	public String edit(Model model,
			@PathVariable("id") int id) {
		
		Category entity = dao.findById(id);
		List<Category> list = dao.findAll();
		model.addAttribute("entity", entity);
		model.addAttribute("list", list);
		return "admin/category/index";		

	}
	
	@RequestMapping("/admin/category/create")
	public String create(
			@ModelAttribute("entity") Category entity,
			RedirectAttributes model) {
		
		if (entity.getName() != null && entity.getNameVN() != null
				&& !entity.getName().equals("") && !entity.getNameVN().equals("")) 
		{
			dao.create(entity);	
			model.addAttribute("message", "Thêm mới thành công!");
		} else {
			model.addAttribute("message", "Name is empty");
		}
		
		return "redirect:/admin/category/index";
	}
	
	@RequestMapping("/admin/category/update")
	public String update(
			@ModelAttribute("entity") Category entity,
			RedirectAttributes model) {
		
		dao.update(entity);
		model.addAttribute("message", "Cập nhật thành công!");
		return "redirect:/admin/category/edit/"+entity.getId();
	}
	
	@RequestMapping(value={"/admin/category/delete","/admin/category/delete/{id}"})
	public String delete(
			@PathVariable("id") Integer id,
			RedirectAttributes model) {
		
		dao.delete(id);
		model.addAttribute("message", "Xóa thành công!");
		return "redirect:/admin/category/index";
	}
}
