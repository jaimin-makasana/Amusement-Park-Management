package com.team5.HAPark.ride.model;

import com.team5.HAPark.ride.timeSlot.TimeSlot;

import java.sql.Time;

public class Ride {
    int id;
    private String name;
    private String type;
    private int maxOccupancy;
    private Time duration;
    private TimeSlot timeSlot;

    public Ride() {
    }

    public Ride(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public Ride(int id, String name, String type, int maxOccupancy, Time duration, TimeSlot timeSlot) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.maxOccupancy = maxOccupancy;
        this.duration = duration;
        this.timeSlot = timeSlot;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getMaxOccupancy() {
        return maxOccupancy;
    }

    public void setMaxOccupancy(int maxOccupancy) {
        this.maxOccupancy = maxOccupancy;
    }

    public Time getDuration() {
        return duration;
    }

    public void setDuration(Time duration) {
        this.duration = duration;
    }

    public TimeSlot getTimeSlot() {
        return timeSlot;
    }

    public void setTimeSlot(TimeSlot timeSlot) {
        this.timeSlot = timeSlot;
    }

    @Override
    public String toString() {
        return "Ride{" +
                "duration=" + duration +
                '}';
    }
}
