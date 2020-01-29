package com.iServices.iContact.controllers;

import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.iServices.iContact.entites.Contact;
import com.iServices.iContact.entites.User;
import com.iServices.iContact.repos.ContactRepository;
import com.iServices.iContact.repos.RoleRepository;
import com.iServices.iContact.repos.UserRepository;
import com.iServices.iContact.service.ContactService;
import com.opencsv.CSVWriter;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;

@Controller
@SessionAttributes("email")
public class ContactController { 
	
	@Autowired
	ContactService service;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private ContactRepository conRep;

	@Autowired
	private RoleRepository roleRep;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ContactController.class);

	
	@RequestMapping("/createContact")
	public String showCreateContact()  
	{
		return "createContact";
	}
	
	private List<Contact> getSpecContacts(ModelMap model)
	{
	
		 String email = model.get("email").toString();

		 User user = userRepository.findByEmail(email);
		
		String userType = user.getType();
		
		List<Contact> contacts ;
		if(roleRep.getRoleID(user.getUid()).toString().equals("1"))
		 contacts = service.getAllContacts(model);
		else
		 contacts = service.getSpecificContact(userType);
		
		return contacts;
	}
	
	@RequestMapping("displayContactss")
	public String displayContacts(ModelMap modelMap ,HttpSession session)
	{
		LOGGER.info("Display Contacts Page");
		String email = "";
		try {
		 email = modelMap.get("email").toString();
		}
		catch(Exception e)
		{
			if(email=="")
			{
				return "login/login";
			}
		}
		 User user = userRepository.findByEmail(email);
		 
			if(roleRep.getRoleID(user.getUid()).toString().equals("1"))
			{
				modelMap.put("submitted", "<a href='pendingContacts'>Show Pending Contacts</a><br>");
			}
			
		List<Contact> contacts =getSpecContacts(modelMap);
		
		modelMap.addAttribute("contacts",contacts);
	
		modelMap.addAttribute("userWel",user.getFirstname());
		return "displayContacts";
	}
	
	
	@RequestMapping("/saveContact")
	public String saveContact(@ModelAttribute("contact")Contact contact,ModelMap modelMap)
	{
		Contact con = conRep.findByEmail(contact.getEmail().toString());
		if(!con.getEmail().equals(""))
		{
			modelMap.addAttribute("existed","username already existed");
			return "createContact";
		}
	
		 String email = modelMap.get("email").toString();

		 User user = userRepository.findByEmail(email);
			
		String userType = user.getType();
		
		if(roleRep.getRoleID(user.getUid()).toString().equals("1"))
		{
			modelMap.get("email");
			contact.setVersion(0);
			contact.setPending(0l);
			Contact contactSaved = service.saveContact(contact,modelMap);
			String msg = "Contact saved with id : " + contactSaved.getId();
			modelMap.addAttribute("msg",msg);
		
		
		}
		else
		{
			modelMap.get("email");
			contact.setVersion(0);
			contact.setPending(1l);
			Contact contactSaved = service.saveContact(contact,modelMap);
			String msg = "Contact saved with id : " + contactSaved.getId();
			modelMap.addAttribute("msg",msg);
			

		
		}
		
		
		
		return "createContact";
	}
	
	
	@RequestMapping("/deleteContact")
	public String deleteContact(@RequestParam("id") int id,ModelMap modelMap)
	{
		
		modelMap.get("email");
		Contact contact = service.getContact(id);
		try {
		 service.deleteContact(contact);
		}
		catch(Exception e)
		{
			LOGGER.error("Contact not found Exception");
			 List<Contact> contacts =getSpecContacts(modelMap);
				modelMap.addAttribute("contacts",contacts);
			modelMap.addAttribute("notFound", "Contact Not found or is already has been deleted");
			return "displayContacts";
		}
		 List<Contact> contacts =getSpecContacts(modelMap);
			modelMap.addAttribute("contacts",contacts);
		return "displayContacts";
	}
	
	
	@RequestMapping("/showUpdatePage")
	public String showUpdate(@RequestParam("id") int id,ModelMap modelMap)
	{
		modelMap.get("email");
		Contact contact = service.getContact(id);
		modelMap.addAttribute("contact",contact);
		modelMap.addAttribute("vers",contact.getVersion());
		return "updateContact";
	}
	
	
	@RequestMapping("/updateContact")
	public String updateContact(@ModelAttribute("contact")Contact contact,ModelMap modelMap)
	{
		
		
		 Contact c1 = contact;
		Contact c2 = service.getContact(c1.getId());
		 if(c1.getVersion()!=c2.getVersion())
		 {
		
			modelMap.put("transF", "Data updated By another User, Kindly try Again");
			List<Contact> contacts =getSpecContacts(modelMap);
			modelMap.addAttribute("contacts",contacts);
			
			LOGGER.error("Contact Updating by another user");
			
			return "displayContacts";

		 }
		
		 service.updateContact(contact,modelMap);
		 List<Contact> contacts =getSpecContacts(modelMap);
		 modelMap.addAttribute("contacts",contacts);
		return "displayContacts";
	}
	
	 @GetMapping("/export-users")
	    public void exportCSV(HttpServletResponse response,ModelMap modelMap) throws Exception {

	        //set file name and content type
	        String filename = "users.csv";

	        response.setContentType("text/csv");
	        response.setHeader(HttpHeaders.CONTENT_DISPOSITION,
	                "attachment; filename=\"" + filename + "\"");

	        //create a csv writer
	        StatefulBeanToCsv<Contact> writer = new StatefulBeanToCsvBuilder<Contact>(response.getWriter())
	                .withQuotechar(CSVWriter.NO_QUOTE_CHARACTER)
	                .withSeparator(CSVWriter.DEFAULT_SEPARATOR)
	                .withOrderedResults(false)
	                .build();
	        List<Contact> contacts =getSpecContacts(modelMap);
	        //write all users to csv file
	        writer.write(contacts);
					
	    }
	
	
}
