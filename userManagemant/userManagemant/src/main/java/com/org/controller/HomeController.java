package com.org.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.org.model.UserDtls;
import com.org.repository.UserRepository;
import com.org.service.UserService;

import jakarta.servlet.http.HttpSession;

@Controller
public class HomeController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private UserRepository userRepository;
	
	@ModelAttribute
	public void commonUser(Principal p, Model m)
	{
		if(p!=null)
		{
			String email=p.getName();
			UserDtls user=userRepository.findByEmail(email);
			m.addAttribute("user",user);
		}
	}

	
	@GetMapping("/")
	public String index() {
		return "index";
	}

	@GetMapping("/signin")
	public String login() {
		return "login";
	}

	@GetMapping("/register")
	public String register() {
		return "register";
	}
	
	/*
	 * @GetMapping("/user/home") public String home() { return "home"; }
	 * 
	 * @GetMapping("/user/profile") public String profile(Principal
	 * p,org.springframework.ui.Model m) { String email=p.getName(); UserDtls
	 * user=userRepository.findByEmail(email); m.addAttribute("user",user); return
	 * "profile"; }
	 */

	@PostMapping("/createUser")
	public String createuser(@ModelAttribute UserDtls user, HttpSession session) {

		// System.out.println(user);

		boolean f = userService.checkEmail(user.getEmail());

		if (f) {
			session.setAttribute("msg", "Email Id alreday exists");
		}

		else {
			UserDtls userDtls = userService.createUser(user);
			if (userDtls != null) {
				session.setAttribute("msg", "Register Sucessfully");
			} else {
				session.setAttribute("msg", "Something wrong on server");
			}
		}

		return "redirect:/register";
	}
}
