package com.team5.HAPark.ride.timeSlot;

import com.team5.HAPark.ride.persistence.IRidePersistence;
import com.team5.HAPark.ride.persistence.RidePersistenceFactory;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

@Service
public class TimeSlotService implements ITimeSlotService{
    private IRidePersistence ridePersistence = new RidePersistenceFactory().createRidePersistence();

    public TimeSlotService(IRidePersistence ridePersistence) throws SQLException {
        this.ridePersistence = ridePersistence;
    }

    public TimeSlotService() {

    }

    public List<HashMap<Integer,Integer>> getAllTimeSlots() throws SQLException {
        List<HashMap<Integer,Integer>> maps = ridePersistence.getAllTimeSlots();
        return maps;
    }

    public String getTimeSlotName(int timeslotId) {
        if(timeslotId==1){
            return "Morning timeslot at 10:00AM";
        }
        if (timeslotId==2){
            return "Afternoon timeslot at 2:00PM";
        }
        return "Evening timeslot at 6:00PM";
    }
}
