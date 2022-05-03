package com.team5.HAPark.reserveRide.model;

import java.sql.SQLException;
import java.util.List;

public interface IRideReserveService {
    void reserve(int rideId, int timeslotId, int reserveSeats) throws SQLException;
    int reserveSeats(int rideId, int timeslotId, int seats) throws SQLException;
    List<RideReserve> getReservations() throws SQLException;
    List<String> getReservedRideNames() throws SQLException;
    List<String> getReservedTimeSlots() throws SQLException;
}
