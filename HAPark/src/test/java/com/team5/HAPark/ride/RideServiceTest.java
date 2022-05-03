package com.team5.HAPark.ride;

import com.team5.HAPark.ride.model.IRideService;
import com.team5.HAPark.ride.model.Ride;
import com.team5.HAPark.ride.model.RideService;
import com.team5.HAPark.ride.timeSlot.ITimeSlotService;
import com.team5.HAPark.ride.timeSlot.TimeSlot;
import com.team5.HAPark.ride.persistence.IRidePersistence;
import com.team5.HAPark.ride.timeSlot.TimeSlotService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RideServiceTest {
    static IRideService rideService;
    static ITimeSlotService timeSlotService=new TimeSlotService();
    public static IRidePersistence ridePersistenceMock;
    static Ride ride1;
    static Ride ride2;
    static List<Ride> rides = new ArrayList<>();
    static List<HashMap<Integer,Integer>> maps=new ArrayList<>();

    @BeforeAll
    static void init() throws SQLException {
        HashMap<Integer,Integer> map1= new HashMap<>();
        HashMap<Integer,Integer> map2= new HashMap<>();
        map1.put(1,50);
        map1.put(2,50);
        map1.put(3,50);
        maps.add(map1);
        map2.put(1,100);
        map2.put(2,100);
        map2.put(3,100);
        maps.add(map2);
        ride1=new Ride(1,"Test Ride","Ground",100, Time.valueOf("00:30:00"),new TimeSlot(map1));
        ride2=new Ride(2,"Test Ride2","Water",50, Time.valueOf("00:35:00"),new TimeSlot(map2));
        rides.add(ride1);
        rides.add(ride2);
        ridePersistenceMock = Mockito.mock((IRidePersistence.class));
        rideService= new RideService(ridePersistenceMock);
        Mockito.when(ridePersistenceMock.getRide(1)).thenReturn(ride1);
        Mockito.when(ridePersistenceMock.getRide(2)).thenReturn(ride2);
        Mockito.when(ridePersistenceMock.getAllRides()).thenReturn(rides);
        Mockito.when(ridePersistenceMock.getAllTimeSlots()).thenReturn(maps);
    }

    @Test
    void getRide() throws SQLException {
        assertEquals(ride1,rideService.getRide(1));
    }

    @Test
    void getAnotherRide() throws SQLException {
        assertEquals(ride2,rideService.getRide(2));
    }

    @Test
    void getAllRides () throws SQLException {
        assertEquals(2,rideService.getAllRides().size());
    }

    @Test
    void getAllRideNames() throws SQLException {
        List<String> testRideNames = new ArrayList<>();
        testRideNames.add("Test Ride");
        testRideNames.add("Test Ride2");
        assertEquals(testRideNames,rideService.getAllRideNames());
    }

    @Test
    void getAllWaterRides() throws SQLException {
        assertEquals(List.of(ride2),rideService.getAllWaterRides());
    }

    @Test
    void getAllGroundRides() throws SQLException {
        assertEquals(List.of(ride1),rideService.getAllGroundRides());
    }

}
