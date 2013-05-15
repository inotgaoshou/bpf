package com.remind.bpf.user.service;

import java.util.Date;

import javax.annotation.Resource;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.remind.bpf.common.util.AbstractTester;
import com.remind.bpf.user.mapper.UserMapper;
import com.remind.bpf.user.model.User;

public class TestUserService extends AbstractTester {

	
	@Resource(name="userMapper")
	private UserMapper userMapper;
	
	@Test
	public void testSaveUser(){
		System.out.println("userMapper:"+userMapper);
		User user = new User();
//		user.setId(1L);
		user.setCity_code(100);
		user.setCreated(new Date());
		user.setCreated_by(1L);
		user.setDef_menu_id(1L);
		user.setStatus("0");
		user.setEmail("test@126.com");
		user.setLast_upd(new Date());
		user.setLast_upd_by(1L);
		user.setLocked("0");
		user.setLogin_date(new Date());
		user.setLogin_diyname("aaa");
		user.setMsisdn("1111");
		user.setPassword("111");
		user.setProv_code(110);
		user.setTelphone("111");
		user.setType(1);
		user.setUser_name("test");
		user.setLogin_name("aaaaa11");
		
		
		this.userMapper.insertUser(user);
	}
}
