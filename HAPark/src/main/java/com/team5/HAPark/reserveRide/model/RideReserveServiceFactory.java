package com.team5.HAPark.reserveRide.model;

public class RideReserveServiceFactory implements IRideReserveServiceFactory{
    IRideReserveService rideReserveService;

    public IRideReserveService getRideReserveService() {
        if (rideReserveService == null) {
            rideReserveService = new RideReserveService();
        }
        return rideReserveService;
    }
}
