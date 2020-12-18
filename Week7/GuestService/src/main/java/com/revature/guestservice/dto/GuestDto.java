package com.revature.guestservice.dto;

import com.revature.guestservice.pojo.Guest;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GuestDto {
	
	private int guestId;

	private String firstName;

	private String lastName;

	private RoomDto room;

	private String phoneNumber;

	private double payment;
	
	public GuestDto(Guest guest, RoomDto room) {
		this(
				guest.getGuestId(), 
				guest.getFirstName(),
				guest.getLastName(), 
				room, 
				guest.getPhoneNumber(), 
				guest.getPayment());
	}


}
