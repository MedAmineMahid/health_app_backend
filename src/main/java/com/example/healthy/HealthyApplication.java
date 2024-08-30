package com.example.healthy;
import com.example.healthy.security.entities.User;
import com.example.healthy.security.services.AccountServiceImp;
import com.example.healthy.security.services.RoleService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import com.example.healthy.security.entities.Role;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class HealthyApplication {
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	public static void main(String[] args) {
		SpringApplication.run(HealthyApplication.class, args);
	}
	//@Bean
	public CommandLineRunner fillTables(RoleService roleService, AccountServiceImp accountServiceImp) {
		return args -> {
			Role r = accountServiceImp.createRole("Customer");
			User user = new User( "soumiaoukhira66@gmail.com", "123456");
			accountServiceImp.NewUser(user,r);

		};
	}
}
