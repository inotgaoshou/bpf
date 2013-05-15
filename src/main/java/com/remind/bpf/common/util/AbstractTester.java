package com.remind.bpf.common.util;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

//使用@RunWith(SpringJUnit4ClassRunner.class),才能使测试运行于Spring测试环境  
@RunWith(SpringJUnit4ClassRunner.class)  
//@ContextConfiguration 注解有以下两个常用的属性：  
//locations：可以通过该属性手工指定 Spring 配置文件所在的位置,可以指定一个或多个 Spring 配置文件  
//inheritLocations：是否要继承父测试类的 Spring 配置文件，默认为 true  
@ContextConfiguration(locations={"classpath:spring/*-context.xml"})  
public class AbstractTester {

	
}
