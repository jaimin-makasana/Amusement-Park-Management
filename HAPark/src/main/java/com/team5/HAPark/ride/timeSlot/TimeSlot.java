package com.team5.HAPark.ride.timeSlot;

import java.util.HashMap;

public class TimeSlot {
    //Map key is timeslot id and value is availability
    HashMap<Integer,Integer> map= new HashMap<>();

    public TimeSlot(HashMap<Integer, Integer> map) {
        this.map = map;
    }

    public TimeSlot() {
    }

    public HashMap<Integer, Integer> getMap() {
        return map;
    }

    public void setMap(HashMap<Integer, Integer> map) {
        this.map = map;
    }

    @Override
    public String toString() {
        return "TimeSlot{" +
                "map=" + map +
                '}';
    }
}

