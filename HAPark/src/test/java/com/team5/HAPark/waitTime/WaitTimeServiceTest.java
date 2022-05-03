package com.team5.HAPark.waitTime;

import com.team5.HAPark.ride.model.*;
import com.team5.HAPark.ride.persistence.IRidePersistence;
import com.team5.HAPark.ride.timeSlot.TimeSlot;
import com.team5.HAPark.waitTime.persistence.IWaitTimePersistence;
import com.team5.HAPark.waitTime.model.WaitTimeService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.sql.SQLException;
import java.sql.Time;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class WaitTimeServiceTest {

    static WaitTimeService waitTimeService;
    public static IWaitTimePersistence waitTimePersistenceMock;

    static IRideService rideService;
    public static IRidePersistence ridePersistenceMock;

    static Ride ride1;
    static Ride ride2;

    static List<Ride> rides = new ArrayList<>();
    static List<HashMap<Integer,Integer>> maps=new ArrayList<>();

    @BeforeAll
    static void init() throws SQLException {
        //Timeslots
        HashMap<Integer,Integer> map1= new HashMap<>();
        HashMap<Integer,Integer> map2= new HashMap<>();
        map1.put(1,50);
        map1.put(2,60);
        map1.put(3,70);
        maps.add(map1);
        map2.put(1,100);
        map2.put(2,100);
        map2.put(3,100);
        maps.add(map2);

        //Rides
        ride1=new Ride(1,"Test Ride","Ground",100, Time.valueOf("00:30:00"),new TimeSlot(map1));
        ride2=new Ride(2,"Test Ride2","Water",50, Time.valueOf("00:35:00"),new TimeSlot(map2));

        //All rides
        rides.add(ride1);
        rides.add(ride2);

        //RidePersistence Mock
        ridePersistenceMock = Mockito.mock((IRidePersistence.class));
        rideService=new RideService(ridePersistenceMock);
        Mockito.when(ridePersistenceMock.getAllRides()).thenReturn(rides);
        Mockito.when(ridePersistenceMock.getRideTimeSlot(1)).thenReturn(new TimeSlot(map1));
        Mockito.when(ridePersistenceMock.getRideTimeSlot(2)).thenReturn(new TimeSlot(map2));

        //WaitTimePersistence Mock
        waitTimePersistenceMock=Mockito.mock(IWaitTimePersistence.class);
        waitTimeService=new WaitTimeService(ridePersistenceMock,waitTimePersistenceMock);
        Mockito.when(waitTimePersistenceMock.getRideDuration(1)).thenReturn(Time.valueOf("00:30:00"));
        Mockito.when(waitTimePersistenceMock.getRideDuration(2)).thenReturn(Time.valueOf("00:35:00"));
        Mockito.when(waitTimePersistenceMock.getRideMaxOccupancy(1)).thenReturn(100);
        Mockito.when(waitTimePersistenceMock.getRideMaxOccupancy(2)).thenReturn(100);
    }

    @Test
    void getDuration() throws SQLException {
       assertEquals(Time.valueOf("00:30:00").toLocalTime(),waitTimeService.getDuration(1));
    }

    @Test
    void getDurationString() throws SQLException {
        assertEquals(Time.valueOf("00:30:00").toString(),waitTimeService.getDurationString(1));
    }

    @Test
    void checkWaitTime() throws SQLException {
        assertEquals(3,waitTimeService.calculateWaitTime(1).getWaitTime().values().size());
    }

    @Test
    void calculateWaitTime() throws SQLException {
        HashMap<Integer, LocalTime> waitTime=new HashMap<>();
        waitTime.put(1,LocalTime.of(02,30));
        waitTime.put(2,LocalTime.of(02,00));
        waitTime.put(3,LocalTime.of(01,30));
        assertEquals(waitTime,waitTimeService.calculateWaitTime(1).getWaitTime());
    }

    //Boundry conditon: When reserved seats are zero, the waittime would also be zero
    @Test
    void calculateWaitTimeWhenZeroRideRounds() throws SQLException {
        HashMap<Integer, LocalTime> waitTime=new HashMap<>();
        waitTime.put(1,LocalTime.of(00,00));
        waitTime.put(2,LocalTime.of(00,00));
        waitTime.put(3,LocalTime.of(00,00));
        assertEquals(waitTime,waitTimeService.calculateWaitTime(2).getWaitTime());
    }
}