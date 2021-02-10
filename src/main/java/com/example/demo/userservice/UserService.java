package com.example.demo.userservice;

import com.example.demo.model.User;
import com.example.demo.model.UserDetailsRequestModel;

public interface UserService {

	public User createUser(UserDetailsRequestModel model);
}
