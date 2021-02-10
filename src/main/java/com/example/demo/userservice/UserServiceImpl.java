package com.example.demo.userservice;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.User;
import com.example.demo.model.UserDetailsRequestModel;
import com.example.demo.shared.Utils;

@Service
public class UserServiceImpl implements UserService
{

	@Autowired
	Utils utils;
	
	public UserServiceImpl() {
	}
	
	@Autowired
	//placing autwire here will autowire utils class in this constructor
	public UserServiceImpl(Utils utils) {
		this.utils = utils;
	}
	
	@Override
	public User createUser(UserDetailsRequestModel model) {
		System.out.println("Inside create user******************");
		User user = new User();
		user.setEmail(model.getEmail());
		user.setFirstname(model.getFirstname());
		user.setLastname(model.getLastname());
		
		String userid = utils.generateUserId();
		user.setId(userid);
		return user;
	}

}
