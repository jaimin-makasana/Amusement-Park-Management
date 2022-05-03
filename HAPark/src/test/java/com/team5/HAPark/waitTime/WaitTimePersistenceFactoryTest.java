package com.team5.HAPark.waitTime;

import com.team5.HAPark.waitTime.persistence.IWaitTimePersistence;
import com.team5.HAPark.waitTime.persistence.IWaitTimePersistenceFactory;
import com.team5.HAPark.waitTime.persistence.WaitTimePersistenceFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class WaitTimePersistenceFactoryTest {
    private IWaitTimePersistence waitTimePersistence1;
    private IWaitTimePersistence waitTimePersistence2;

    @BeforeEach
    void setUp() {
        IWaitTimePersistenceFactory waitTimePersistenceFactory1 = new WaitTimePersistenceFactory();
        IWaitTimePersistenceFactory waitTimePersistenceFactory2 = new WaitTimePersistenceFactory();

        waitTimePersistence1 = waitTimePersistenceFactory1.createWaitTimePersistence();
        waitTimePersistence2 = waitTimePersistenceFactory2.createWaitTimePersistence();

    }

    @Test
    void createWaitTimePersistenceNotNull() {
        assertNotNull(waitTimePersistence1);
    }

    @Test
    void createWaitTimePersistenceSameInstance() {
        assertEquals(waitTimePersistence1,waitTimePersistence2);
    }
}
