package com.culp.learingSpring.Repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.culp.learingSpring.Model.Room;

@Repository
public interface RoomRepository extends CrudRepository<Room, Long>{

	
}
