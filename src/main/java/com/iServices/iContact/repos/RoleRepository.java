package com.iServices.iContact.repos;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.iServices.iContact.entites.Role;

public interface RoleRepository extends JpaRepository<Role,Long> {

	@Query(value="select role_id from user_role where user_id=:id",nativeQuery = true)
	public String getRoleID(@Param("id") long id);
	
}
