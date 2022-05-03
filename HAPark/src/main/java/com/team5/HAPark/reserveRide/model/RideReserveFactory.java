package com.team5.HAPark.reserveRide.model;

public class RideReserveFactory {
    RideReserve rideReserve;

    public RideReserve getRideReserve(){
        if(rideReserve == null){
            rideReserve = new RideReserve();
        }
        return rideReserve;
    }
}
