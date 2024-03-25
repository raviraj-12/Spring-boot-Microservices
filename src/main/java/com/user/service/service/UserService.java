package com.user.service.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.user.service.entity.User;

public interface UserService {

	
	//create 
	User saveUser(User user);
	
	//get all user
	List<User> getAll();
	
	//get single user 
	
	User getUser(String userId);
	
	//delete
	void delete(String userId);
	
}
