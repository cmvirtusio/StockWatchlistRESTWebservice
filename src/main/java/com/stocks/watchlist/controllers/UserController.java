package com.stocks.watchlist.controllers;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.stocks.watchlist.models.Role;
import com.stocks.watchlist.models.User;
import com.stocks.watchlist.pojos.UserInputObject;
import com.stocks.watchlist.services.UserService;

@RestController
public class UserController {
	@Autowired
	private UserService userService;
	
	//Create a watchlist
	@PostMapping(value = "/register")
	public String createNewUser(@RequestBody UserInputObject user){
		if(!user.getPassword().equals(user.getPasswordConfirmation())) {
			return "Passwords Don't Match";
		} else if (userService.getUser(user.getUsername())!=null){
			return "Username already exists";
		} else {
			//roles
			List<Role> listOfRoles = Arrays.asList(new Role("USER"));
			userService.save(new User(user.getUsername(),user.getPassword(),listOfRoles));
			return "User Created";
		}
	}
	
	//Better than going to mySQL commandline client;
	@GetMapping(value = "/allUsers")
	public List<User> getAllUsers(){
		return userService.getAllUsers();
	}
}
