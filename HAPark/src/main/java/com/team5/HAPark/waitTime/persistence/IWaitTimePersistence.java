package com.team5.HAPark.waitTime.persistence;

import java.sql.SQLException;
import java.sql.Time;

public interface IWaitTimePersistence {
    int getRideMaxOccupancy(int rideId) throws SQLException;
    Time getRideDuration(int rideId) throws SQLException;
}
