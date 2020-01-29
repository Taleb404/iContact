package com.iServices.iContact.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.iServices.iContact.entites.Contact;
import com.iServices.iContact.entites.User;
import com.iServices.iContact.repos.ContactRepository;
import com.iServices.iContact.repos.RoleRepository;
import com.iServices.iContact.repos.UserRepository;
import com.iServices.iContact.util.EmailUtil;

@Service
@SessionAttributes("email")
public class ContactServiceImpl implements ContactService {
	
	
	@Autowired
	private ContactRepository contactRep;
	
	@Autowired
	private UserRepository userRep;
	
	 @PersistenceContext
	 private EntityManager entityManager;
	
	 @Autowired
	 ContactService service;
		
		
		@Autowired
		private RoleRepository roleRep;
		
		@Autowired
		EmailUtil emailUtil;
		
	public ContactRepository getContactRep() {
		return contactRep;
	}

	public void setContactRep(ContactRepository contactRep) {
		
		this.contactRep = contactRep;
	}

	@Override
	public Contact saveContact(Contact contact,ModelMap modelMap) {
		//System.out.println("AAAAAServiceAAAAAAAAAAAAAAAAAAAAA");

	
		 String email = modelMap.get("email").toString();

		 User user = userRep.findByEmail(email);
		 contact.setUid(user);
		//String userType = user.getType();
		//System.out.println("VVVVVVVVVVVVVVVVVVV" + userType);
	
		if(!(roleRep.getRoleID(user.getUid()).toString()).equals("1"))
		{
			contact.setVersion(0);
			contact.setPending(1l);
			emailUtil.sendEmail("tallebibrahem@gmail.com", "New Contact on Pending !", "Contact : \" " +contact.getFirstname() + " " + contact.getLastname()+" \" waiting to check!");

		}
		else
		{
			contact.setVersion(0);
			contact.setPending(0l);
		}
		
		return contactRep.save(contact);
	}

	@Transactional
	@Override
	public Contact updateContact(Contact contact,ModelMap modelMap)  {
		

		 Contact c1 = contact;
		Contact c2 = service.getContact(c1.getId());
		 if(c1.getVersion()!=c2.getVersion())
		 {
			 System.out.print(1/0);
		 }
		 
		String email = modelMap.get("email").toString();
		User user = userRep.findByEmail(email);
		Contact contactw  = entityManager.find(Contact.class, contact.getId());
		contactw.setUid(user);
		contactw.setEmail(contact.getEmail());
		contactw.setAddress(contact.getAddress());
		contactw.setFirstname(contact.getFirstname());
		contactw.setLastname(contact.getLastname());
		contactw.setPhone(contact.getPhone());
		
		 entityManager.persist(contactw);

		return contactw;
	}

	@Override
	public void deleteContact(Contact contact) {
		
		contactRep.deleteContactRep(contact.getId());
	}

	@Override
	public Contact getContact(int id) {
		return contactRep.findById(id).orElse(null);
	}

	@Override
	public List<Contact> getAllContacts(ModelMap modelmap) {
		String email = modelmap.get("email").toString();
	
		User user = userRep.findByEmail(email);
		//System.out.println("VVVVVVVVVVVVVVVVVVVV " + user.getType());
		if(!roleRep.getRoleID(user.getUid()).toString().equals("1"))
		{
	        System.out.println(1/0); 

		}
		return contactRep.getthemall();
	} 



	@Override
	public List<Contact> getSpecificContact(String type) {
		 return contactRep.getNormalUserContact(type);
	
	}

	@Override
	public List<Contact> getPendingContacts() {
		return contactRep.getOnPendingContacts();
	}

	@Override
	public void SetApprovedinService(int id) {
		contactRep.setApprovedinModel(id);
	}



}
