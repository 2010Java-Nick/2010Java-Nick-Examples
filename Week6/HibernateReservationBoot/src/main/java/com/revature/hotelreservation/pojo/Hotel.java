package com.revature.hotelreservation.pojo;

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
import com.fasterxml.jackson.annotation.ObjectIdGenerator;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@Table(name = "hotel")
@JsonIdentityInfo(
		generator = ObjectIdGenerators.PropertyGenerator.class,
		property = "hotelId")
public class Hotel {
	
	@Column(name = "max_capacity")
	private final int MAX_CAPACITY;
	
	@Column(name = "current_capacity")
	private int currentCapacity;
	
	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "hotel")
	private List<Room> rooms;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "hotel_id")
	private int hotelId;
	
	@Column(name = "hotel_name")
	private String hotelName;
	
	public boolean hasCapacity() {
		return currentCapacity < MAX_CAPACITY;
	}
	
	public Hotel() {
		super();
		MAX_CAPACITY = Integer.MAX_VALUE;
	}



	public Hotel(int mAX_CAPACITY, int currentCapacity, List<Room> rooms, int hotelId, String hotelName) {
		super();
		MAX_CAPACITY = mAX_CAPACITY;
		this.currentCapacity = currentCapacity;
		this.rooms = rooms;
		this.hotelId = hotelId;
		this.hotelName = hotelName;
	}

	public int getCurrentCapacity() {
		return currentCapacity;
	}

	public void setCurrentCapacity(int currentCapacity) {
		this.currentCapacity = currentCapacity;
	}

	public List<Room> getRooms() {
		return rooms;
	}

	public void setRooms(List<Room> rooms) {
		this.rooms = rooms;
	}

	public int getHotelId() {
		return hotelId;
	}

	public void setHotelId(int hotelId) {
		this.hotelId = hotelId;
	}

	public String getHotelName() {
		return hotelName;
	}

	public void setHotelName(String hotelName) {
		this.hotelName = hotelName;
	}

	public int getMAX_CAPACITY() {
		return MAX_CAPACITY;
	}


	@Override
	public String toString() {
		return "Hotel [MAX_CAPACITY=" + MAX_CAPACITY + ", currentCapacity=" + currentCapacity + ", rooms=" + rooms
				+ ", hotelId=" + hotelId + ", hotelName=" + hotelName + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + MAX_CAPACITY;
		result = prime * result + currentCapacity;
		result = prime * result + hotelId;
		result = prime * result + ((hotelName == null) ? 0 : hotelName.hashCode());
		result = prime * result + ((rooms == null) ? 0 : rooms.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Hotel other = (Hotel) obj;
		if (MAX_CAPACITY != other.MAX_CAPACITY)
			return false;
		if (currentCapacity != other.currentCapacity)
			return false;
		if (hotelId != other.hotelId)
			return false;
		if (hotelName == null) {
			if (other.hotelName != null)
				return false;
		} else if (!hotelName.equals(other.hotelName))
			return false;
		if (rooms == null) {
			if (other.rooms != null)
				return false;
		} else if (!rooms.equals(other.rooms))
			return false;
		return true;
	}

}
