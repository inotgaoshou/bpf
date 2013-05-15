package com.remind.bpf.user.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.remind.bpf.user.mapper.UserMapper;
import com.remind.bpf.user.model.User;
import com.remind.bpf.user.service.UserService;



@Service("userService")
public class UserServiceImpl implements UserService {
	
	@Resource(name="userMapper")
	private UserMapper userMapper;

	public void saveUser(User user) {
		this.userMapper.insertUser(user);
		System.out.println("uid:"+user.getId());
		System.out.println("save user successed!");
	}
	
	public List<User> getUserList(){
		return this.userMapper.getUserList();
	}

}
