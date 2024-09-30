package com.excelr.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.excelr.model.User;
import com.excelr.service.UserService;

@RestController
@RequestMapping("/home")
public class HomeController {
	
	@Autowired
	private UserService userService;

	
	//http://localhost:8001/home/users
	@GetMapping("/users")
	public List<User> getUser() {
		return this.userService.getUsers();
		
	}
	
//	@GetMapping("/current-user")
//	public String getLoggedInUser(Principal principal) {
//		return principal.getName();
//	}

}
