package com.revature.hotelreservation.controller;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;

import com.revature.hotelreservation.pojo.Guest;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class GuestControllerLiveTest {
	
	@LocalServerPort
	private int port;
	
	@Autowired
	private TestRestTemplate restTemplate;

	@Test
	void test() {
		Guest expectedGuest = new Guest(53, "Even Cooler", "Guest", null, "5555555556", 999.99);
		assertThat(this.restTemplate
				.getForObject("http://localhost:" + port + "/guest/lastName/Guest", Guest[].class))
		.contains(expectedGuest);
	}

}
