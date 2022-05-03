package com.team5.HAPark.waitTime.controller;

import com.team5.HAPark.ride.model.IRideService;
import com.team5.HAPark.ride.model.RideServiceFactory;
import com.team5.HAPark.waitTime.model.IWaitTimeService;
import com.team5.HAPark.waitTime.model.WaitTimeServiceFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.sql.SQLException;

@Controller
public class WaitTimeController {

    @Autowired
    private IRideService rideService= new RideServiceFactory().getRideService("RIDESERVICE");

    @Autowired
    IWaitTimeService waitTimeService = new WaitTimeServiceFactory().createWaitTimeService();

    @GetMapping("rides/waittime")
    public String waitTime(Model model) throws SQLException {
        model.addAttribute("allrides", rideService.getAllRides());
        model.addAttribute("wts",waitTimeService.getWaitTimes());
        return "WaitTime";
    }
}
