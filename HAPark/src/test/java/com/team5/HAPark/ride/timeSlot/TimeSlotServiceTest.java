package com.team5.HAPark.ride.timeSlot;

import com.team5.HAPark.ride.model.Ride;
import com.team5.HAPark.ride.persistence.IRidePersistence;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TimeSlotServiceTest {
    static ITimeSlotService timeSlotService;
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
        ridePersistenceMock = Mockito.mock((IRidePersistence.class));
        timeSlotService=new TimeSlotService(ridePersistenceMock);
        Mockito.when(ridePersistenceMock.getAllTimeSlots()).thenReturn(maps);
    }

    @Test
    void getMorningTimeSlotName(){
        assertEquals("Morning timeslot at 10:00AM",timeSlotService.getTimeSlotName(1));
    }

    @Test
    void getAfternoonTimeSlotName(){
        assertEquals("Afternoon timeslot at 2:00PM",timeSlotService.getTimeSlotName(2));
    }

    @Test
    void getEveningTimeSlotName(){
        assertEquals("Evening timeslot at 6:00PM",timeSlotService.getTimeSlotName(3));
    }

    @Test
    void getAllTimeSlots() throws SQLException {
        assertEquals(maps,timeSlotService.getAllTimeSlots());
    }
}
