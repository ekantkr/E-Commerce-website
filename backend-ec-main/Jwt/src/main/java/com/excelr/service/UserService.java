package com.excelr.service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.excelr.model.User;

@Service
public class UserService {
	
	private List<User> store =new ArrayList<>();
	
	
	public UserService() {
		store.add(new User(UUID.randomUUID().toString(),"Atika","atika@gmail.com"));
		store.add(new User(UUID.randomUUID().toString(),"kiran","kiran@gmail.com"));
		store.add(new User(UUID.randomUUID().toString(),"Tushar","tushar@gmail.com"));
		store.add(new User(UUID.randomUUID().toString(),"harsh","harsh@gmail.com"));
	}
	
	public List<User> getUsers(){
		return this.store;
	}

}
