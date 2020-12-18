package com.revature.hotelservice.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.revature.hotelservice.pojo.Room;

@Repository
public interface RoomRepo extends JpaRepository<Room, Integer>{

}
