package com.revature.hotelreservation.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import com.revature.hotelreservation.GuestRepository;
import com.revature.hotelreservation.pojo.Guest;
import com.revature.hotelreservation.service.GuestService;

//Used for non-SpringBoot app
//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(classes = {AppConfig.class})
//@WebAppConfiguration
@WebMvcTest(GuestController.class)  //only used for Spring boot app
class GuestControllerUnitTest {

	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private GuestService service;
	
	@MockBean
	private GuestRepository guestRepo;
	
	@Test
	void test() throws Exception{
		List<Guest> guestList = new ArrayList<>();
		Guest expectedGuest = new Guest(53, "Even Cooler", "Guest", null, "5555555556", 999.99);
		guestList.add(expectedGuest);
		when(service.getGuestsByLastName("Guest")).thenReturn(guestList);
		this.mockMvc.perform(get("/guest/lastName/Guest")).andDo(print())
		.andExpect(status().isOk())
		.andExpect(content().json("[{\"guestId\":53,\"firstName\":\"Even Cooler\",\"lastName\":\"Guest\",\"room\":null,\"phoneNumber\":\"5555555556\",\"payment\":999.99}]"));
	}

}
