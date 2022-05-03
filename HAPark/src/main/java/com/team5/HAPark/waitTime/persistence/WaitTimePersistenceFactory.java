package com.team5.HAPark.waitTime.persistence;

import com.team5.HAPark.database.mysql.MySQLDatabase;

public class WaitTimePersistenceFactory implements IWaitTimePersistenceFactory {
    private static IWaitTimePersistence waitTimePersistence;

    @Override
    public IWaitTimePersistence createWaitTimePersistence() {
        if (waitTimePersistence == null){
            waitTimePersistence = new WaitTimePersistence(MySQLDatabase.getInstance());
        }
        return waitTimePersistence;
    }
}
