package com.team5.HAPark.ride.model;

import java.sql.SQLException;
import java.util.List;

public interface IRideService {
    List<Ride> getAllRides() throws SQLException;
    Ride getRide(int id) throws SQLException;
    List<String> getAllRideNames() throws SQLException;
    List<Ride> getAllGroundRides() throws SQLException;
    List<Ride> getAllWaterRides() throws SQLException;
}
