package com.culp.learingSpring.Repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.culp.learingSpring.Model.Guest;

@Repository
public interface GuestRepository extends CrudRepository<Guest, Long> {

}
