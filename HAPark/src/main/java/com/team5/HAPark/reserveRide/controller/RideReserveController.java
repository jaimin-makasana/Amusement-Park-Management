package com.team5.HAPark.reserveRide.controller;

import com.team5.HAPark.reserveRide.model.IRideReserveService;
import com.team5.HAPark.reserveRide.model.RideReserve;
import com.team5.HAPark.reserveRide.model.RideReserveFactory;
import com.team5.HAPark.reserveRide.model.RideReserveService;
import com.team5.HAPark.reserveRide.persistence.IRideReservePersistence;
import com.team5.HAPark.reserveRide.persistence.IRideReservePersistenceFactory;
import com.team5.HAPark.reserveRide.persistence.RideReservePersistenceFactory;
import com.team5.HAPark.ride.model.IRideService;
import com.team5.HAPark.ride.model.RideServiceFactory;
import com.team5.HAPark.ride.timeSlot.ITimeSlotService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.sql.SQLException;

@Slf4j
@Controller
public class RideReserveController {
    @Autowired
    private IRideService rideService=new RideServiceFactory().getRideService("RIDESERVICE");
    @Autowired
    private ITimeSlotService timeSlotService;
    private RideReserve rideReserve=new RideReserveFactory().getRideReserve();

    @GetMapping("/reserve")
    public String reserveForm(Model model) throws SQLException {
        model.addAttribute("allrides", rideService.getAllRides());
        model.addAttribute("ride",rideReserve);
        model.addAttribute("maps",timeSlotService.getAllTimeSlots());
        return "RideForm";
    }

    @PostMapping("/reserved")
    public String submitForm(Model model,@ModelAttribute("ride") RideReserve ride) throws SQLException {
        IRideReservePersistenceFactory rideReservePersistenceFactory = new RideReservePersistenceFactory();
        IRideReservePersistence rideReservePersistence = rideReservePersistenceFactory.createRideReservePersistence();
        IRideReserveService rideReserveService = new RideReserveService(rideReservePersistence);
        //https://stackoverflow.com/questions/31159075/how-to-find-out-the-currently-logged-in-user-in-spring-boot
        Authentication currentUser = SecurityContextHolder.getContext().getAuthentication();
        String username = currentUser.getName();
        rideReserveService.reserveSeats(ride.getRideId(),ride.getTimeslotId(),ride.getReserveSeats());
        rideReserveService.reserve(ride.getRideId(),ride.getTimeslotId(),ride.getReserveSeats());
        model.addAttribute("rideReserved",rideService.getRide(ride.getRideId()));
        model.addAttribute("timeslot",timeSlotService.getTimeSlotName(ride.getTimeslotId()));
        return "RideReserved";
    }

    @GetMapping("/reservations")
    public String getAllReservations(Model model) throws SQLException {
        IRideReservePersistenceFactory rideReservePersistenceFactory=new RideReservePersistenceFactory();
        IRideReservePersistence rideReservePersistence = rideReservePersistenceFactory.createRideReservePersistence();
        IRideReserveService rideReserveService=new RideReserveService(rideReservePersistence);
        model.addAttribute("reservations",rideReserveService.getReservations());
        model.addAttribute("rideNames",rideReserveService.getReservedRideNames());
        model.addAttribute("timeSlotNames",rideReserveService.getReservedTimeSlots());
        return "RideReservations";
    }
}
