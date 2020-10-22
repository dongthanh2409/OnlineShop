package me.dongthanh.estore.controller;

import java.io.File;

import javax.servlet.ServletContext;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.validation.BindingResult;

import me.dongthanh.estore.bean.MailInfo;
import me.dongthanh.estore.dao.CustomerDAO;
import me.dongthanh.estore.entity.Customer;
import me.dongthanh.estore.service.CartService;
import me.dongthanh.estore.service.CookieService;
import me.dongthanh.estore.service.MailService;


@Controller
public class AccountController {
	@Autowired
	CustomerDAO dao;

	@Autowired
	HttpSession session;

	@Autowired
	CookieService cookie;

	@Autowired
	ServletContext app;

	@Autowired
	MailService mailer;

	@Autowired
	HttpServletRequest request;
	
	@Autowired
	CartService cart;

	@GetMapping("/account/login")
	public String login(Model model) {
		Cookie ckid = cookie.read("userid");
		Cookie ckpwd = cookie.read("pass");

		if (ckid != null) {
			String uid = ckid.getValue();
			String pwd = ckpwd.getValue();

			model.addAttribute("uid", uid);
			model.addAttribute("pwd", pwd);

		}

		return "account/login";
	}

	@PostMapping("/account/login")
	public String login(Model model, @RequestParam("id") String id, @RequestParam("password") String pw,
			@RequestParam(value = "rm", defaultValue = "false") boolean rm) {

		Customer user = dao.findById(id);

		if (user == null) {
			model.addAttribute("message", "invalid username!");
		} else if (!pw.equals(user.getPassword())) {
			model.addAttribute("message", "Invalid password!");
		} else if (!user.getActivated()) {
			model.addAttribute("message", "Your account is Inactivated!");
		} else {
			// login
			model.addAttribute("message", "Login successfully!!!");
			session.setAttribute("user", user);

			// remember me?
			if (rm == true) {
				cookie.create("userid", user.getId(), 30);
				cookie.create("pass", user.getPassword(), 30);
			} else {
				cookie.delete("userid");
				cookie.delete("pass");
			}
			
			//return to last page user wanted to reach(if have)
			String backUrl = (String) session.getAttribute("back-url");
			if(backUrl != null) {
				return "redirect:" + backUrl;
			}
			
			return "home/index";
		}

		return "account/login";
	}

	@RequestMapping("/account/logout")
	public String logout() {
		cart.clear();
		session.removeAttribute("user");
		return "redirect:/home/index";
	}

	@GetMapping("/account/register")
	public String register(Model model) {
		Customer user = new Customer();
		model.addAttribute("form", user);
		return "account/register";
	}

	@PostMapping("/account/register")
	public String register(Model model,
			@Validated @ModelAttribute("form") Customer user, BindingResult errors,
			@RequestParam("photo_file") MultipartFile file) throws Exception {
		
		//validate register form
		if(errors.hasErrors()) {
			model.addAttribute("message", "Please fix following errors");
			return "account/register";
		} else {
			Customer user2 = dao.findById(user.getId());
			if(user2!=null) {
				model.addAttribute("message", "Username is used!");
				return "account/register";
			}
		}
		
		if (file.isEmpty()) {
			user.setPhoto("user.png");
		} else {
			String dir = app.getRealPath("/static/images/customers");
			File f = new File(dir, file.getOriginalFilename());
			file.transferTo(f);
			user.setPhoto(f.getName());
		}
		user.setActivated(false);
		user.setAdmin(false);
		dao.create(user);
		model.addAttribute("message", "Register succressfully");

		// send mail to active account
		String from = "dongthanh.24091@gmail.com";
		String to = user.getEmail();
		String subject = "Welcome";
		String url = request.getRequestURL().toString().replace("register", "activate/" + user.getId());
		String body = "Please click this link to active your account " + url;
		MailInfo mail = new MailInfo(from, to, subject, body);
		mailer.send(mail);
		
		return "account/register";
	}

	@GetMapping("/account/activate/{id}")
	public String active(Model model, @PathVariable("id") String id) {
		Customer user = dao.findById(id);
		user.setActivated(true);
		dao.update(user);
		String message = "Your account is activated. Please login now!";
		model.addAttribute("message", message);
		return "redirect:/account/login";
	}

	@GetMapping("/account/forget")
	public String forget() {
		return "account/forget";
	}

	@PostMapping("/account/forget")
	public void forget(Model model, @RequestParam("id") String id, @RequestParam("email") String email)
			throws Exception {

		Customer user = dao.findById(id);
		if (user == null) {
			model.addAttribute("message", "Invalid username!");
		} else if (!email.equals(user.getEmail())) {
			model.addAttribute("message", "Invalid email!");
		} else {
			// send mail for forget password
			String from = "dongthanh.24091@gmail.com";
			String to = user.getEmail();
			String subject = "Forget Password";
			String body = "Your password is: " + user.getPassword();
			MailInfo mail = new MailInfo(from, to, subject, body);
			mailer.send(mail);
			model.addAttribute("message", "Your password is sent, please check your email");
		}

	}

	@GetMapping("/account/change")
	public String change(Model model) {
		return "account/change";
	}

	@PostMapping("/account/change") 
	public String change (Model model,
			@RequestParam("pw") String pw,
			@RequestParam("pw1") String pw1,
			@RequestParam("pw2") String pw2) {
		
		Customer user = (Customer) session.getAttribute("user");
		
		if(!pw1.equals(pw2)) {
			model.addAttribute("message", "Confirm password is not matched!");
		} else {
			 if(!pw.equals(user.getPassword())) {
				model.addAttribute("message", "Invalid password!");
			} else {
				user.setPassword(pw1);
				dao.update(user);
				model.addAttribute("message", "Change password successfully! You need to login again!");
				return "redirect:/account/login";
			}			
		}
		return "account/change";
	}
	
	@GetMapping("/account/edit")
	public String edit(Model model) {		
		Customer user = (Customer) session.getAttribute("user");	
		if(user == null) {
			model.addAttribute("message", "You have not login yet!");
			return "account/login";
		} else {
			model.addAttribute("form", user);
			return "account/edit";
		}		
	}

	@PostMapping("/account/edit")
	public String edit(Model model, @ModelAttribute("form") Customer user,
			@RequestParam("photo_file") MultipartFile file) throws Exception {
		if (!file.isEmpty()) {
			String dir = app.getRealPath("/static/images/customers");
			File f = new File(dir, file.getOriginalFilename());
			file.transferTo(f);
			user.setPhoto(f.getName());
		}
		dao.update(user);
		session.setAttribute("user", user);
		model.addAttribute("message", "Update account successully");
		return "account/edit";
	}
}
