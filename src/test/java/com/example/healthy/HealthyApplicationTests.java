package com.example.healthy;

import com.example.healthy.security.services.RoleService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UserDetailsService;

@SpringBootTest
class HealthyApplicationTests {

	@Test
	void contextLoads() {
	}
	@Autowired
	UserDetailsService userDetailsService;
	@Autowired
	RoleService roleService;
	@Test
	public void createUser(){

	}

}
