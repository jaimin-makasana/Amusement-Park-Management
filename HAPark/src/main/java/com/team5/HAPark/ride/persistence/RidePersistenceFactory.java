package com.team5.HAPark.ride.persistence;

import com.team5.HAPark.database.mysql.MySQLDatabase;

public class RidePersistenceFactory implements IRidePersistenceFactory{

    private static IRidePersistence ridePersistence;

    @Override
    public IRidePersistence createRidePersistence() {
        if (ridePersistence == null){
            ridePersistence = new RidePersistence(MySQLDatabase.getInstance());
        }
        return ridePersistence;
    }

}
