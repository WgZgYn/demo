package com.example.demo;

import com.example.demo.entity.Account;
import com.example.demo.service.AccountService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

@SpringBootTest
class DemoApplicationTests {
	@Autowired
	AccountService accountService;

	@Test
	void testRepository() {
		List<String> names = accountService.listUsername();
		for (String name : names) {
			System.out.println(name);
		}
	}

	@Test
	void testInterface() {

	}
}
