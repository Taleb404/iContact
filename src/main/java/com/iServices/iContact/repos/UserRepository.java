package com.iServices.iContact.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.iServices.iContact.entites.User;


public interface UserRepository extends JpaRepository<User, Long> {

	User findByEmail(String email);

}
