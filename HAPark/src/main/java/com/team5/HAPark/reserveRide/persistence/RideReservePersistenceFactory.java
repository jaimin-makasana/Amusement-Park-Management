package com.team5.HAPark.reserveRide.persistence;

import com.team5.HAPark.database.mysql.MySQLDatabase;

public class RideReservePersistenceFactory implements IRideReservePersistenceFactory{
    private static IRideReservePersistence rideReservePersistence;
    @Override
    public IRideReservePersistence createRideReservePersistence() {
        if (rideReservePersistence == null){
            rideReservePersistence = new RideReservePersistence(MySQLDatabase.getInstance());
        }
        return rideReservePersistence;
    }
}

