package com.remind.bpf.user.mapper;

import java.util.List;

import com.remind.bpf.user.model.User;

public interface UserMapper {
	
	public User getUserById(Long id);
	
	public List<User> getUserList();
	
	public void insertUser(User user);
	
	public void deleteUser(User user);
	
	public void updateUser(User user);
}
