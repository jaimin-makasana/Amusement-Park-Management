package com.team5.HAPark.waitTime.model;

import java.time.LocalTime;
import java.util.HashMap;

public class WaitTime {
    //Stores timeslot id and waittime for that timeslot
    HashMap<Integer, LocalTime> waitTime=new HashMap<>();

    public WaitTime(HashMap<Integer, LocalTime> waitTime) {
        this.waitTime = waitTime;
    }

    public WaitTime() {
    }

    public HashMap<Integer, LocalTime> getWaitTime() {
        return waitTime;
    }

    public void setWaitTime(HashMap<Integer, LocalTime> waitTime) {
        this.waitTime = waitTime;
    }

    @Override
    public String toString() {
        return "WaitTime{" +
                "waitTime=" + waitTime +
                '}';
    }
}