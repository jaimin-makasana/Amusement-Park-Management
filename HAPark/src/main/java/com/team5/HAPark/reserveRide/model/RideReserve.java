package com.team5.HAPark.reserveRide.model;

public class RideReserve {
    private int rideId;
    private int timeslotId;
    private int reserveSeats;

    public RideReserve(int rideId, int timeslotid, int reserveSeats) {
        this.rideId = rideId;
        this.timeslotId = timeslotid;
        this.reserveSeats = reserveSeats;
    }

    public RideReserve() {
    }

    public int getRideId() {
        return rideId;
    }

    public void setRideId(int rideId) {
        this.rideId = rideId;
    }

    public int getTimeslotId() {
        return timeslotId;
    }

    public void setTimeslotId(int timeslotId) {
        this.timeslotId = timeslotId;
    }

    public int getReserveSeats() {
        return reserveSeats;
    }

    public void setReserveSeats(int reserveSeats) {
        this.reserveSeats = reserveSeats;
    }
}
