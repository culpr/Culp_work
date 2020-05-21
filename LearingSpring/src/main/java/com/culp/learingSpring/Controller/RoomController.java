package com.culp.learingSpring.Controller;


import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.culp.learingSpring.Model.Guest;
import com.culp.learingSpring.Model.Reservation;
import com.culp.learingSpring.Model.Room;
import com.culp.learingSpring.Repository.GuestRepository;
import com.culp.learingSpring.Repository.ReservationRepository;
import com.culp.learingSpring.Repository.RoomRepository;
import com.culp.learingSpring.business.domain.GuestList;
import com.culp.learingSpring.business.domain.RoomReservation;
import com.culp.learingSpring.business.service.ReservationService;

@Controller
public class RoomController {
	
	@Autowired
	private RoomRepository roomRepository; 
	
	
	@RequestMapping("/rooms")
	@ResponseBody
	public Iterable<Room> getRooms(){
		return  this.roomRepository.findAll();  
	}
	
	
	
	
	private final ReservationService reservationService;
	
	@Autowired
	public RoomController(ReservationService reservationService) {
		super();
		this.reservationService = reservationService;
	}


	@GetMapping("/guests")
	public String getGuest(Model model){
		List<Guest> guests = this.reservationService.getGuestList();
		model.addAttribute("guests", guests);
	
		return "guests";  
	}
	
	
	@RequestMapping("/api/reservations")
	@ResponseBody
	public List<RoomReservation> getRoomReservations(@RequestParam(name="date", required = false)String dateString){
		Date date = Dateutils.createDateFromDateString(dateString);
		return this.reservationService.getRoomReservationsForDate(date);
	}
	
	
	

}






