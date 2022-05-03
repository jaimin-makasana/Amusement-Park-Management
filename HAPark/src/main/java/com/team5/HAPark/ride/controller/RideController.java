package com.team5.HAPark.ride.controller;

import com.team5.HAPark.ride.model.*;
import com.team5.HAPark.ride.persistence.IRidePersistence;
import com.team5.HAPark.ride.persistence.RidePersistenceFactory;
import com.team5.HAPark.ride.timeSlot.ITimeSlotService;
import com.team5.HAPark.ride.timeSlot.TimeSlotFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import lombok.extern.slf4j.Slf4j;

import java.sql.SQLException;

@org.springframework.stereotype.Controller
@Slf4j
public class RideController {

    RideServiceFactory rideServiceFactory=new RideServiceFactory();

    @Autowired
    private IRideService rideService=rideServiceFactory.getRideService("RIDESERVICE");

    @Autowired
    ITimeSlotService timeSlotService=new TimeSlotFactory().createTimeSlotService();

    @GetMapping("/rides")
    public String rides(){
        return "RideMainPage";
    }

    @GetMapping("/rides/all")
    public String allrides(Model model) throws SQLException {
        model.addAttribute("allrides", rideService.getAllRides());
        model.addAttribute("maps",timeSlotService.getAllTimeSlots());
        return "Ride";
    }

    public void allTimeSlots(Model model) throws SQLException {
        IRidePersistence ridePersistence = new RidePersistenceFactory().createRidePersistence();
        model.addAttribute("alltimeslots", ridePersistence.getAllTimeSlots());
    }

    @GetMapping("/rides/ground")
    public String groundRides(Model model) throws SQLException {
        model.addAttribute("groundrides",rideService.getAllGroundRides());
        return "GroundRides";
    }

    @GetMapping("/rides/water")
    public String waterRides(Model model) throws SQLException {
        model.addAttribute("waterrides",rideService.getAllWaterRides());
        return "WaterRides";
    }
}
