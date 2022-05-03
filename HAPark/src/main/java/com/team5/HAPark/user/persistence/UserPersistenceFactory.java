package com.team5.HAPark.user.persistence;

import com.team5.HAPark.database.mysql.MySQLDatabase;

public class UserPersistenceFactory implements IUserPersistenceFactory {

    private static IUserPersistence userPersistence;

    @Override
    public IUserPersistence createUserPersistence() {
        if (userPersistence == null) {
            userPersistence = new MySQLUserPersistence(MySQLDatabase.getInstance());
        }
        return userPersistence;
    }
}
