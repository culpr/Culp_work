package com.culp.learingSpring.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.culp.learingSpring.Model.Guest;
import com.culp.learingSpring.business.service.ReservationService;

//@Controller
//@RequestMapping("/guests")
public class GuestWebController {

    private final ReservationService reservationService;

    @Autowired
    public GuestWebController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    //@GetMapping
    public String getGuests(Model model){
        List<Guest> guests = this.reservationService.getGuestList();
        model.addAttribute("guests", guests);
        return "guests";
    }
}