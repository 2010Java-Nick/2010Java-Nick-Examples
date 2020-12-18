package com.revature.guestservice.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "guest")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "guestId")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Guest {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "guest_id")
	private int guestId;

	@Column(name = "guest_first_name")
	private String firstName;

	@Column(name = "guest_last_name")
	private String lastName;

	@Column(name = "room_id")
	private int roomId;

	@Column(name = "phone_number")
	private String phoneNumber;

	@Column(name = "payment")
	private double payment;

}
