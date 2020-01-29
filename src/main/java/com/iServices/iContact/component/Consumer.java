package com.iServices.iContact.component;

import java.util.Map;

import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.ModelMap;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.iServices.iContact.entites.Contact;
import com.iServices.iContact.entites.User;
import com.iServices.iContact.repos.ContactRepository;
import com.iServices.iContact.repos.UserRepository;
import com.iServices.iContact.service.ContactService;
import com.iServices.iContact.service.SecurityService;

import ch.qos.logback.classic.Logger;

@Component
public class Consumer {
	
	@Autowired
	SecurityService securityService;

	@Autowired
	private ContactRepository contactRep;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	ContactService contactService;
	
	
	@RabbitListener(queues = "${icontact.rabbitmq.queue}")
	public void reciviedMessage(String msg) throws JsonMappingException, JsonProcessingException
	{
		//System.out.println(msg);
	
    
			readUserFromJson(msg);
	}
	
	 private final Logger logger = (Logger) LoggerFactory.getLogger(this.getClass());
	 
	    public String readUserFromJson(String msg) throws JsonMappingException, JsonProcessingException  {
	    	
	    	ObjectMapper mapper = new ObjectMapper();
	    
			Map<String,Object> map = mapper.readValue(msg, Map.class);
			boolean loginResponse = securityService.login(map.get("email").toString(), map.get("password").toString());
	        
	    	if(loginResponse)
			{
	    		Contact contact = new Contact();
	    		contact.setAddress(map.get("address").toString());
	    		contact.setEmail(map.get("conemail").toString());;
	    		contact.setFirstname(map.get("firstname").toString());
	    		contact.setLastname(map.get("lastname").toString());
	    		contact.setPhone(map.get("phone").toString());
	    		contact.setId(8);
	    		User user = userRepository.findByEmail(map.get("email").toString());
	    		contact.setUid(user);
	    		String em = map.get("email").toString();
	    		contact.setVersion(0);
	    		ModelMap tmp = new ModelMap();
	    		tmp.put("email", em);
	    		tmp.addAttribute("email", em);
	    		contactService.saveContact(contact,tmp);
			}
	    	else
	    		System.out.println("Errorrrrrrrrrr");
	      
	    	return null;
	     
	    }
	    
	    /*
	     * 
	     {
"email" : "springtaken@gmail.com",
"password" :"123",
"address" : "lll",
"conemail" : "eeeeee@gmail.com",
"firstname" : "queeu11",
"lastname" : "queueeelll",
"phone" : "5464456"
}
	      
	     */
}
