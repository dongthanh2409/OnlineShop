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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import me.dongthanh.estore.dao.CustomerDAO;
import me.dongthanh.estore.entity.Customer;

@Controller
public class CustomerManager {
	@Autowired
	CustomerDAO dao;
	
	@Autowired
	ServletContext app;
	
	@RequestMapping("/admin/customer/index")
	public String index(Model model) {
		
		Customer entity = new Customer();
		List<Customer> list = dao.findAll();
		model.addAttribute("entity", entity);
		model.addAttribute("list", list);
		return "admin/customer/index";
	}
	
	@RequestMapping("/admin/customer/edit/{id}")
	public String edit(Model model,
			@PathVariable("id") String id) {
		
		Customer entity = dao.findById(id);
		List<Customer> list = dao.findAll();
		model.addAttribute("entity", entity);
		model.addAttribute("list", list);
		return "admin/customer/index";		

	}
	
	@RequestMapping("/admin/customer/create")
	public String create(
			@ModelAttribute("entity") Customer entity,
			@RequestParam("photo_file") MultipartFile file,
			RedirectAttributes model) throws Exception {
		
		if(file.isEmpty()) {
			entity.setPhoto("user.png");
		}else {
			entity.setPhoto(file.getOriginalFilename());
			String path = app.getRealPath("/static/images/customers/"+ entity.getPhoto());
			file.transferTo(new File(path));
		}
		
		try {
			dao.create(entity);	
			model.addAttribute("message", "Thêm mới thành công!");	
			
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("message", "Thêm mới thất bại!");
		}
		
		
		return "redirect:/admin/customer/index";
	}
	
	@RequestMapping("/admin/customer/update")
	public String update(
			@ModelAttribute("entity") Customer entity,
			@RequestParam("photo_file") MultipartFile file,
			RedirectAttributes model) throws Exception {
		
		if(!file.isEmpty()) {
			entity.setPhoto(file.getOriginalFilename());
			String path = app.getRealPath("/static/images/customers/"+ entity.getPhoto());
			file.transferTo(new File(path));
		}
		
		dao.update(entity);
		model.addAttribute("message", "Cập nhật thành công!");
		return "redirect:/admin/customer/edit/"+entity.getId();
	}
	
	@RequestMapping(value={"/admin/customer/delete/{id}"})
	public String delete(
			@PathVariable("id") String id,
			RedirectAttributes model) {
		
		dao.delete(id);
		model.addAttribute("message", "Xóa thành công!");
		return "redirect:/admin/customer/index";
	}
	
	
	int pageSize = 5;
	@ResponseBody
	@RequestMapping("/pager/customer/page-count")
	public long pageCount() {		
		return dao.getPageCount(pageSize);
	}
	
	@ResponseBody
	@RequestMapping("/pager/customer/page/{pageNo}")
	public List<Customer> getPage(@PathVariable("pageNo") int pageNo) {		
		List<Customer> list = dao.getPage(pageSize, pageNo);
		return list;
	}
}
