package com.iServices.iContact;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;

import com.iServices.iContact.entites.Contact;
import com.iServices.iContact.entites.User;
import com.iServices.iContact.repos.ContactRepository;
import com.iServices.iContact.repos.UserRepository;
import com.iServices.iContact.service.ContactService;

@SpringBootTest
class IContactApplicationTests {
/*
	@Autowired
	private ContactRepository contactRep;
	
	@Autowired
	ContactService service;
	
	@Autowired
	private UserRepository userRep;
	
	@Test
	void contextLoads() {
	}
	
	//2
	@Test
	void testAdminUserAccess()
	{
		//User user1 = userRep.getOne(1l);
		User adminUser = userRep.findById(1l).orElse(null);
		Contact contact = contactRep.getOne(2);
		ModelMap modelMap = new ModelMap();
		 // modelMap.put("email","admin@gmail.com");
		  modelMap.addAttribute("email",adminUser.getEmail().toString());
		service.getAllContacts(modelMap);
	}
	
	//2
	@Test
	void testTransactionalUpdate()
	{
		
		Contact contact1 = contactRep.findById(2).orElse(null);
		
		contact1.setPhone("9999999999");
		
		User user1 =userRep.findById(1l).orElse(null);
		User adminUser = userRep.findById(2l).orElse(null);
		
		ModelMap modelMap1 = new ModelMap();
		ModelMap modelMap2 = new ModelMap();
		
		modelMap1.addAttribute("email",adminUser.getEmail().toString());
		modelMap2.addAttribute("email",user1.getEmail().toString());
		modelMap1.addAttribute("contact",contact1);
		modelMap2.addAttribute("contact",contact1);
		
		//service.updateContact(contact1, modelMap1);
		service.updateContact(contact1, modelMap2);
	}
	
	@Test
	void TestAddContactsByNormalUser()
	{
		Contact contact1 = contactRep.findById(2).orElse(null);
		
		contact1.setId(77777);
		contact1.setFirstname("TestingNormal");
		
		User user1 =userRep.findById(1l).orElse(null);
		ModelMap modelMap2 = new ModelMap();
		
	
		modelMap2.addAttribute("email",user1.getEmail().toString());
		
		modelMap2.addAttribute("contact",contact1);
		
		service.saveContact(contact1, modelMap2);
		
	}
	
	@Test
	void TestAddContactsBySuperUser()
	{
		Contact contact1 = contactRep.findById(2).orElse(null);
		
		contact1.setId(99999765);
		contact1.setFirstname("TestingSuper");
		
		User adminUser = userRep.findById(5l).orElse(null);
		
		ModelMap modelMap1 = new ModelMap();
		
		modelMap1.addAttribute("email",adminUser.getEmail().toString());
		modelMap1.addAttribute("contact",contact1);
		
		
		service.saveContact(contact1, modelMap1);
		
	}
*/
}
