package com.team5.HAPark.reserveRide.persistence;

import com.team5.HAPark.reserveRide.model.RideReserve;

import java.sql.SQLException;
import java.util.List;

public interface IRideReservePersistence {
    void addReservationToDB(int rideId,int timeSlotId,int seats) throws SQLException;
    int getRideAvailability(int rideId, int timeSlotId) throws SQLException;
    void updateRideAvailability(int rideId, int timeslotId, int availability) throws SQLException;
    List<RideReserve> getReservations() throws SQLException;
}
