package com.team5.HAPark.ride;

import com.team5.HAPark.reserveRide.model.IRideReserveService;
import com.team5.HAPark.reserveRide.model.RideReserve;
import com.team5.HAPark.reserveRide.model.RideReserveService;
import com.team5.HAPark.reserveRide.persistence.IRideReservePersistence;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

    public class RideReserveServiceTest {
        static IRideReserveService rideReserveService;
        public static IRideReservePersistence rideReservePersistenceMock;
        static RideReserve rideReserve1;
        static RideReserve rideReserve2;
        static List<RideReserve> rideReserves = new ArrayList<>();

        @BeforeAll
        static void init() throws SQLException {
            rideReserve1=new RideReserve(1,1,1);
            rideReserve2=new RideReserve(2,2,2);
            rideReserves.add(rideReserve1);
            rideReserves.add(rideReserve2);
            rideReservePersistenceMock= Mockito.mock(IRideReservePersistence.class);
            rideReserveService=new RideReserveService(rideReservePersistenceMock);
            Mockito.when(rideReservePersistenceMock.getReservations()).thenReturn(rideReserves);
            Mockito.when(rideReservePersistenceMock.getRideAvailability(1,2)).thenReturn(100);
        }

        @Test
        public void getReservations() throws SQLException {
            assertEquals(rideReserves,rideReserveService.getReservations());
        }

        @Test
        public void reserveSeats() throws SQLException {
            assertEquals(70,rideReserveService.reserveSeats(1,2,30));
        }


}
