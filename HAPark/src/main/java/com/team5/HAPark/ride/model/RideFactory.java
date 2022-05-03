package com.team5.HAPark.ride.model;

public class RideFactory implements IRideFactory{
    public Ride getRide(String ride){
        if(ride==null){
            return null;
        }
        if(ride.equalsIgnoreCase("RIDE")){
            return new Ride();
        }

        return null;
    }
}
