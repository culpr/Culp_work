package com.culp.learingSpring.business.service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.culp.learingSpring.Model.Guest;
import com.culp.learingSpring.Model.Reservation;
import com.culp.learingSpring.Model.Room;
import com.culp.learingSpring.Repository.GuestRepository;
import com.culp.learingSpring.Repository.ReservationRepository;
import com.culp.learingSpring.Repository.RoomRepository;
import com.culp.learingSpring.business.domain.GuestList;
import com.culp.learingSpring.business.domain.RoomReservation;


@Service
public class ReservationService {
    private final RoomRepository roomRepository;
    private final GuestRepository guestRepository;
    private final ReservationRepository reservationRepository;

    @Autowired
    public ReservationService(RoomRepository roomRepository, GuestRepository guestRepository, ReservationRepository reservationRepository) {
        this.roomRepository = roomRepository;
        this.guestRepository = guestRepository;
        this.reservationRepository = reservationRepository;
    }

    public List<RoomReservation> getRoomReservationsForDate(Date date){
        Iterable<Room> rooms = this.roomRepository.findAll();
        Map<Long, RoomReservation> roomReservationMap = new HashMap();
        rooms.forEach(room -> {
            RoomReservation roomReservation = new RoomReservation();
            roomReservation.setRoomId(room.getRoomId());
            roomReservation.setRoomName(room.getRoomName());
            roomReservation.setRoomNumber(room.getRoomNumber());
            roomReservationMap.put(room.getRoomId(), roomReservation);
        });
        Iterable<Reservation> reservations = this.reservationRepository.findReservationByReservationDate(new java.sql.Date(date.getTime()));
        reservations.forEach(reservation -> {
            RoomReservation roomReservation = roomReservationMap.get(reservation.getRoomId());
            roomReservation.setDate(date);
            Guest guest = this.guestRepository.findById(reservation.getGuestId()).get();
            roomReservation.setFirstName(guest.getFirstName());
            roomReservation.setLastName(guest.getLastName());
            roomReservation.setGuestId(guest.getGuestID());
        });
        List<RoomReservation> roomReservations = new ArrayList<>();
        for(Long id: roomReservationMap.keySet()){
            roomReservations.add(roomReservationMap.get(id));
        }
        System.out.println("RoomReservation List: "+roomReservations);
        return roomReservations;
    }
    
    
    
    
    public List<Guest> getGuestList(){
    	Iterable<Guest> guests = this.guestRepository.findAll();
    	 List<Guest> guestList = new ArrayList<>();
    	 
    	 guests.forEach(guest-> {guestList.add(guest);});
    	 guestList.sort(new Comparator<Guest>() {
    		 public int compare(Guest o1, Guest o2) {
    			 if(o1.getLastName()== o2.getLastName()) {
    				 return o1.getFirstName().compareTo(o2.getFirstName());
    			 }
    			 return o1.getLastName().compareTo(o2.getLastName());
    		 }
    	 });
    	
    	System.out.println("Guest list: "+guestList);
    	return guestList;
    	
    }
}
