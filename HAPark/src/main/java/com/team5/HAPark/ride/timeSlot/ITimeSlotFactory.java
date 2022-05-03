package com.team5.HAPark.ride.timeSlot;

import java.util.HashMap;

public interface ITimeSlotFactory {
    ITimeSlotService createTimeSlotService();
    TimeSlot createTimeSlot(HashMap<Integer,Integer> map);
}
