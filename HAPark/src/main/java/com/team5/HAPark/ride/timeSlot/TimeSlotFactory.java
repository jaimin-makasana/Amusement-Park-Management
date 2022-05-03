package com.team5.HAPark.ride.timeSlot;

import java.util.HashMap;

public class TimeSlotFactory implements ITimeSlotFactory{
    ITimeSlotService timeSlotService;
    TimeSlot timeSlot;

    @Override
    public ITimeSlotService createTimeSlotService(){
        if(timeSlotService == null){
            timeSlotService = new TimeSlotService();
        }
        return timeSlotService;
    }

    @Override
    public TimeSlot createTimeSlot(HashMap<Integer,Integer> map) {
        if(timeSlot == null){
            timeSlot = new TimeSlot(map);
        }
        return timeSlot;
    }
}
