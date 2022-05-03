package com.team5.HAPark.waitTime.model;

import java.sql.SQLException;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.List;

public interface IWaitTimeService {
    List<HashMap<Integer, LocalTime>> getWaitTimes() throws SQLException;
    LocalTime getDuration(int rideId) throws SQLException;
    String getDurationString(int rideId) throws SQLException;
    WaitTime calculateWaitTime(int rideId) throws SQLException;
}
