package com.team5.HAPark.ride.model;

import com.team5.HAPark.ride.persistence.IRidePersistence;
import com.team5.HAPark.ride.persistence.RidePersistenceFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.*;

@Slf4j
@Service
public class RideService implements IRideService {

    private IRidePersistence ridePersistence = new RidePersistenceFactory().createRidePersistence();

    public RideService(IRidePersistence ridePersistence) {
        this.ridePersistence = ridePersistence;
    }

    public RideService() {
    }

    public List<Ride> getAllRides() throws SQLException {
        List<Ride> Rides = ridePersistence.getAllRides();
        return Rides;
    }

    public List<String> getAllRideNames() throws SQLException {
        List<String> names = new ArrayList<>();
        List<Ride> Rides = ridePersistence.getAllRides();
        for (Ride ride:Rides){
            names.add(ride.getName());
        }
        return names;
    }

    public List<Ride> getAllGroundRides() throws SQLException {
        List<Ride> Rides = ridePersistence.getAllRides();
        List<Ride> groundRides = new ArrayList<>();
        for(Ride ride:Rides) {
            if(Objects.equals(ride.getType(), "Ground")){
                groundRides.add(ride);
            }
        }
        return groundRides;
    }

    public List<Ride> getAllWaterRides() throws SQLException {
        List<Ride> Rides = ridePersistence.getAllRides();
        List<Ride> waterRides = new ArrayList<>();
        for(Ride ride:Rides) {
            if(Objects.equals(ride.getType(), "Water")){
                waterRides.add(ride);
            }
        }
        return waterRides;
    }

    public Ride getRide(int id) throws SQLException {
        Ride ride=ridePersistence.getRide(id);
        return ride;
    }

}
