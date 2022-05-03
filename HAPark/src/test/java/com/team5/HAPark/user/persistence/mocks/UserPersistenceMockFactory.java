package com.team5.HAPark.user.persistence.mocks;

import com.team5.HAPark.user.persistence.IUserPersistence;

public class UserPersistenceMockFactory implements IUserPersistenceMockFactory {

    @Override
    public IUserPersistence createUserPersistenceMock() {
        return new UserPersistenceMock();
    }
}
