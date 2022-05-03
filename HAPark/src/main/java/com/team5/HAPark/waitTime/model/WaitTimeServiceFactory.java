package com.team5.HAPark.waitTime.model;

public class WaitTimeServiceFactory implements IWaitTimeServiceFactory{
    IWaitTimeService waitTimeService;
    WaitTime waitTime;

    public IWaitTimeService createWaitTimeService(){
        if(waitTimeService == null){
            waitTimeService = new WaitTimeService();
        }
        return waitTimeService;
    }

    public WaitTime createWaitTime(){
        if(waitTime == null){
            waitTime = new WaitTime();
        }
        return waitTime;
    }
}
