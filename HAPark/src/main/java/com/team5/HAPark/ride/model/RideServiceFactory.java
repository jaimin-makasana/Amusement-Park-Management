package com.team5.HAPark.ride.model;

public class RideServiceFactory implements IRideServiceFactory{
    public IRideService getRideService(String rideService){
        if(rideService==null){
            return null;
        }
        if(rideService.equalsIgnoreCase("RIDESERVICE")){
            return new RideService();
        }
        return null;
    }
}
