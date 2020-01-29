package com.iServices.iContact.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.iServices.iContact.entites.Contact;
import com.iServices.iContact.entites.User;
import com.iServices.iContact.repos.ContactRepository;
import com.iServices.iContact.repos.UserRepository;
import com.iServices.iContact.service.ContactService;
import com.iServices.iContact.util.EmailUtil;

@Controller
@SessionAttributes("email")
public class ContactApprovalController {
		
	@Autowired
	ContactService service;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	ContactRepository contactRep;
	
	@Autowired
	EmailUtil emailUtil;
	
	@RequestMapping("/pendingContacts")
	public String displayContacts(ModelMap modelMap)
	{
		

		 String email = modelMap.get("email").toString();

		 User user = userRepository.findByEmail(email);
		
		String userType = user.getType();
		if(userType!="admin")
		{
			List<Contact> contacts =service.getPendingContacts();
			modelMap.addAttribute("contacts",contacts);
		}
		else
		{
			modelMap.addAttribute("msg","Only Admin can have access to this page.");
		}
		
		return "pendingContacts";
	}
	
	@RequestMapping("/SetApproved")
	public String SetApproved(@RequestParam("id") int id,ModelMap modelMap)
	{
		
		service.SetApprovedinService(id);
		List<Contact> contacts =service.getPendingContacts();
		modelMap.addAttribute("contacts",contacts);
		return "pendingContacts";
	}
	
}
