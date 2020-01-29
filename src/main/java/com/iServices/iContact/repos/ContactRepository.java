package com.iServices.iContact.repos;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.iServices.iContact.entites.Contact;
import com.iServices.iContact.entites.User;

public interface ContactRepository extends JpaRepository<Contact, Integer> {
	
	Contact findByEmail(String email);

	@Query(value="select * from contact",nativeQuery = true)
	public List<Contact> getthemall();
	
    @Query(value = "SELECT t1.id,t1.firstname,t1.lastname,t1.email,t1.phone,t1.address,t1.uid,t1.version,t1.pending FROM contact t1 INNER JOIN user t2 ON t1.uid = t2.uid WHERE t2.type=:typo and t1.pending='0' ", nativeQuery = true)
	public List<Contact> getNormalUserContact(@Param("typo") String type);
    
    @Query(value="select * from contact where pending='1' ",nativeQuery = true)
	public List<Contact> getOnPendingContacts();
    
    @Modifying
    @Transactional
    @Query(value="UPDATE contact SET pending = '0' WHERE id=:theID  ",nativeQuery = true)
	void setApprovedinModel(@Param("theID") int id);
    
    @Modifying
    @Transactional
    @Query(value="DELETE from contact WHERE id=:theID  ",nativeQuery = true)
	void deleteContactRep(@Param("theID") int id);
    
    //UPDATE `icontact`.`contact` SET `pending` = '1' WHERE (`id` = '1');
 }