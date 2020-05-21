package com.culp.learingSpring.Repository;

import java.sql.Date;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.culp.learingSpring.Model.Reservation;


@Repository
public interface ReservationRepository extends CrudRepository<Reservation, Long> {

	
	Iterable<Reservation> findReservationByReservationDate(Date date);
}
