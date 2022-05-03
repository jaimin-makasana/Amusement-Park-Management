package com.team5.HAPark.reserveRide.persistence;

import com.team5.HAPark.reserveRide.model.RideReserve;
import com.team5.HAPark.database.mysql.IMySQLDatabase;
import com.team5.HAPark.ride.persistence.RidePersistenceFactory;
import com.team5.HAPark.ride.timeSlot.TimeSlot;
import com.team5.HAPark.ride.persistence.IRidePersistence;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class RideReservePersistence implements IRideReservePersistence {

    private final IMySQLDatabase mySQLDatabase;

    public RideReservePersistence(IMySQLDatabase mySQLDatabase) {
        this.mySQLDatabase = mySQLDatabase;
    }

    public void addReservationToDB(int rideId,int timeSlotId,int seats) throws SQLException {
        String auth = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
        Connection con = mySQLDatabase.getConnection();
        Statement stmt = con.createStatement();
        stmt.executeUpdate("INSERT INTO ride_reserve (user_mail_id,ride_id,timeslot_id,seats) VALUES (\""+auth+"\","+rideId+","+timeSlotId+","+seats+");");
    }

    //Get seats available for a given ride at a given timeslot
    public int getRideAvailability(int rideId, int timeSlotId) throws SQLException {
        IRidePersistence ridePersistence = new RidePersistenceFactory().createRidePersistence();
        TimeSlot timeSlot = ridePersistence.getRideTimeSlot(rideId);
        HashMap<Integer,Integer> map = timeSlot.getMap();
        int availability = map.get(timeSlotId);
        return availability;
    }

    //Update reserved seats by user in com.team5.HAPark.database
    public void updateRideAvailability(int rideId, int timeslotId, int availability) throws SQLException {
        Connection con=mySQLDatabase.getConnection();
        Statement stmt= con.createStatement();
        stmt.executeUpdate("UPDATE ride_timeslot SET availability="+availability+" WHERE ride_id="+rideId+" AND timeslot_id="+timeslotId);
    }

    public List<RideReserve> getReservations() throws SQLException {
        Authentication currentUser = SecurityContextHolder.getContext().getAuthentication();
        String username = currentUser.getName();
        Connection con = mySQLDatabase.getConnection();
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM ride_reserve WHERE user_mail_id=\""+username+"\"");
        List<RideReserve> ridesReserved=new ArrayList<>();
        while (rs.next()){
            RideReserve rideReserve = new RideReserve();
            rideReserve.setRideId(rs.getInt("ride_id"));
            rideReserve.setTimeslotId(rs.getInt("timeslot_id"));
            rideReserve.setReserveSeats(rs.getInt("seats"));
            ridesReserved.add(rideReserve);
        }

        return ridesReserved;
    }

}
