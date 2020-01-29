package com.iServices.iContact.service;

import java.util.List;


import org.springframework.ui.ModelMap;

import com.iServices.iContact.entites.Contact;

public interface ContactService {

	Contact saveContact(Contact contact,ModelMap modelMap);
	Contact updateContact(Contact contact,ModelMap modelMap);
	void deleteContact(Contact contact);
	Contact getContact(int id);
	List<Contact> getAllContacts(ModelMap modelmap);
	List<Contact> getSpecificContact(String type);
	List<Contact> getPendingContacts();
	void SetApprovedinService(int id);
	
}
