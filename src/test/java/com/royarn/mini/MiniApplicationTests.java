package com.royarn.mini;

import com.alibaba.fastjson.JSON;
import com.royarn.mini.entity.TUser;
import com.royarn.mini.service.TUserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MiniApplicationTests {

//	@Test
//	public void contextLoads() {
//	}

	@Resource
	TUserService userService;

	@Test
	public void test() {
		List<TUser> userList = userService.findAll();
		System.out.println(JSON.toJSON(userList));
	}
}