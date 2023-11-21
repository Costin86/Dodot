package com.example.Dodot;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.Dodot.web.CustomerController;


@SpringBootTest
class DodotApplicationTests {

	@Autowired
	private CustomerController controller;
	@Test
	void contextLoads() throws Exception {
			assertThat(controller).isNotNull();
		}

	}
