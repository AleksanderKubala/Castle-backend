package com.example.demo.Engine.Events;


import com.example.demo.Properties.StorageUpdateProperties;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Observable;

@Component
public class StorageUpdateScheduler extends Observable implements Runnable {

    private boolean done;
    private StorageUpdateProperties updateProperties;

    public StorageUpdateScheduler(
            StorageUpdateProperties updateProperties
    ) {
        this.updateProperties = updateProperties;
        done = !updateProperties.getUpdateOccurs();
        new Thread(this).start();
    }

    @Override
    public void run() {
        //LocalDateTime systemTime = LocalDateTime.now();
        //int difference = 60 - systemTime.getMinute();
        //LocalDateTime eventTime = systemTime.plusMinutes(difference);
        while(!done) {
            int difference = updateProperties.getEventDelayInSeconds();
            LocalDateTime eventTime = LocalDateTime.now().plusSeconds(difference);
            setChanged();
            notifyObservers(new StorageUpdateEvent(eventTime));
            clearChanged();
            try {
                Thread.sleep(difference * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
