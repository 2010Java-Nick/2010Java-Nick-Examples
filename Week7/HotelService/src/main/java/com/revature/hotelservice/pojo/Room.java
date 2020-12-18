package com.revature.hotelservice.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "room")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "roomId")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Room {

	public enum RoomType {
		SINGLE_BED,
		DOUBLE_BED,
		SUITE,
		LUXURY_SUITE
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "room_id")
	private int roomId;
	
	@Column(name="num_beds")
	private int beds;
	
	@Column(name = "smoking")
	private boolean smoking;
	
	@Transient
	//@Enumerated(EnumType.STRING)
	private RoomType roomType;
	
	@Column(name = "roomservice")
	private boolean roomService;
	
	@Column(name = "room_number")
	private int roomNumber;
	
//	@ManyToOne
//	@JoinColumn(name = "hotel_id")
	@Column(name = "hotel_id")
	private int hotelId;
}
