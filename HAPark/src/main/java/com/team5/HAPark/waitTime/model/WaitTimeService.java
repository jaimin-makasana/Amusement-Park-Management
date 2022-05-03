package com.team5.HAPark.waitTime.model;

import com.team5.HAPark.ride.model.Ride;
import com.team5.HAPark.ride.timeSlot.TimeSlot;
import com.team5.HAPark.ride.persistence.IRidePersistence;
import com.team5.HAPark.ride.persistence.RidePersistenceFactory;
import com.team5.HAPark.waitTime.persistence.IWaitTimePersistence;
import com.team5.HAPark.waitTime.persistence.WaitTimePersistenceFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.sql.Time;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
@Slf4j
public class WaitTimeService implements IWaitTimeService{

    private IRidePersistence ridePersistence = new RidePersistenceFactory().createRidePersistence();
    private IWaitTimePersistence waitTimePersistence = new WaitTimePersistenceFactory().createWaitTimePersistence();

    public WaitTimeService(IRidePersistence ridePersistence) {
        this.ridePersistence = ridePersistence;
    }

    public WaitTimeService(IWaitTimePersistence waitTimePersistence) {
        this.waitTimePersistence=waitTimePersistence;
    }

    public WaitTimeService() {

    }

    public WaitTimeService(IRidePersistence ridePersistence, IWaitTimePersistence waitTimePersistence) {
        this.waitTimePersistence=waitTimePersistence;
        this.ridePersistence=ridePersistence;
    }

    public List<HashMap<Integer,LocalTime>> getWaitTimes() throws SQLException {
        List<Ride> rides= ridePersistence.getAllRides();
        List<HashMap<Integer,LocalTime>> waitTimes=new ArrayList<>();
        for (Ride ride:rides){
            IWaitTimeService waitTimeService = new WaitTimeServiceFactory().createWaitTimeService();
            WaitTime waitTime = waitTimeService.calculateWaitTime(ride.getId());
            waitTimes.add(waitTime.getWaitTime());
        }
        return waitTimes;
    }

    public LocalTime getDuration(int rideId) throws SQLException {
        Time duration = waitTimePersistence.getRideDuration(rideId);
        LocalTime durationInLocalTime = duration.toLocalTime();
        return durationInLocalTime;
    }

    public String getDurationString(int rideId) throws SQLException {
        Time duration = waitTimePersistence.getRideDuration(rideId);
        return duration.toString();
    }

    //Number of currently reservated seats = max occupancy for a time slot - available seats for a time slot
    //Rounds of rides according to reservations = number of seats reserved / capacity per round
    //WaitTime = Rounds of rides * duration of ride per round
    public WaitTime calculateWaitTime(int rideId) throws SQLException {
        WaitTime waitTime = new WaitTimeServiceFactory().createWaitTime();
        LocalTime initialWaitTime = LocalTime.of(00,00,00);

        TimeSlot timeSlot = ridePersistence.getRideTimeSlot(rideId);

        for (Integer key : timeSlot.getMap().keySet()) {
            int numberOfSeatsReserved = waitTimePersistence.getRideMaxOccupancy(rideId) - timeSlot.getMap().get(key);

            int rideRounds = numberOfSeatsReserved / 10;

            LocalTime durationInLocalTime = getDuration(rideId);
            String durationString=getDurationString(rideId);

            Long hours = Long.parseLong(durationString.substring(0, 2));
            Long mins = Long.parseLong(durationString.substring(3, 5));
            Long secs = Long.parseLong(durationString.substring(7));

            if(rideRounds>0) {
                for (int i = 0; i < rideRounds - 1; i++) {
                    initialWaitTime = durationInLocalTime.plusSeconds(secs);
                    initialWaitTime = initialWaitTime.plusMinutes(mins);
                    initialWaitTime = initialWaitTime.plusHours(hours);
                    durationInLocalTime = initialWaitTime;
                }
                waitTime.getWaitTime().put(key, initialWaitTime);
            }
            else {
                waitTime.getWaitTime().put(key,initialWaitTime);
            }
        }
        return waitTime;
    }
}