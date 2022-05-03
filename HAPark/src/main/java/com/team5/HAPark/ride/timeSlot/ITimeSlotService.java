package com.team5.HAPark.ride.timeSlot;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

public interface ITimeSlotService {
    String getTimeSlotName(int timeslotId);
    List<HashMap<Integer,Integer>> getAllTimeSlots() throws SQLException;
}
