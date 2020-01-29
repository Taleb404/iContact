package com.iServices.iContact.controllers;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.iServices.iContact.entites.User;
import com.iServices.iContact.repos.UserRepository;
import com.iServices.iContact.service.SecurityService;

@Controller
@SessionAttributes("email")
public class UserController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	SecurityService securityService;
	
	@Autowired
	private BCryptPasswordEncoder encode;
	
	@RequestMapping("/showReg")
	public String showRegistrationPage()
	{
		return "login/registerPage";
	}
	
	
	@RequestMapping(value="registerUser")
	public String register(@ModelAttribute("user") User user)
	{
		user.setPassword(encode.encode(user.getPassword()));
		userRepository.save(user);
		return "login/login";
	}
	
	@RequestMapping(value="/log" , method = RequestMethod.GET)
	public String login(@ModelAttribute("user") User user)
	{

		LOGGER.info("Contact login page");
		
		//userRepository.save(user);
		return "login/login";
	}
	
	@RequestMapping(value="/login" , method = RequestMethod.POST)
	public String Login(@RequestParam("email") String email ,@RequestParam("password") String password, ModelMap modelmap)
	{
		
		//User user = userRepository.findByEmail(email);
		boolean loginResponse = securityService.login(email, password);
		if(loginResponse)
		{
			
			modelmap.put("email" ,email);
			LOGGER.info("Login with email : " + email);
			
			return "redirect:/displayContactss";
		}
		
		modelmap.addAttribute("msg","Invalid username or password,please try again");
		return "login/login";
		
		
	}
	
	@RequestMapping("/logout")
	public String logout(ModelMap modelmap) {
		
		modelmap.remove("email");
		return "login/login";
	}

}
