package com.revature.hotelservice.pojo;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "hotel")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "hotelId")
@Data
@AllArgsConstructor
public class Hotel {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "hotel_id")
	private int hotelId;
	
	@Column(name = "max_capacity")
	private final int MAX_CAPACITY;
	
	@Column(name = "current_capacity")
	private int currentCapacity;
	
	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "hotelId")
	private List<Room> rooms;
	
	@Column(name = "hotel_name")
	private String hotelName;
	
	public boolean hasCapacity() {
		return currentCapacity < MAX_CAPACITY;
	}
	
	public Hotel() {
		super();
		MAX_CAPACITY = Integer.MAX_VALUE;
	}

}
