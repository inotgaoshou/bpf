package com.remind.bpf.user.service;

import java.util.List;

import com.remind.bpf.user.model.User;


public interface UserService {

	public void saveUser(User user);
	
	public List<User> getUserList();
	
}
