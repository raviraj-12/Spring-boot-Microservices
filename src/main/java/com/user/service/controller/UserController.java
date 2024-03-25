package com.user.service.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.user.service.entity.User;
import com.user.service.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {

	@Autowired
	private UserService userService;
	
	// create user
	@PostMapping("/create")
	public ResponseEntity<User> createUser(@RequestBody User user){
		
		 User saveUser = userService.saveUser(user);
		 
		 return ResponseEntity.status(HttpStatus.CREATED).body(saveUser);
		
	}
	
	
		
	//get all User
	@GetMapping("/getAll")
	public ResponseEntity<List<User>> getAllUser(){
		
		List<User> allUser = userService.getAll();
	
	    return ResponseEntity.ok(allUser);
	}
	
	

	//get one user
	@GetMapping("/getbyId/{userId}")
	public ResponseEntity<User> getSingleUser(@PathVariable String userId){
		
		User user = userService.getUser(userId);
	
		return ResponseEntity.status(HttpStatus.OK).body(user);
		//or responseEntity.ok(user);
	
	}
}
