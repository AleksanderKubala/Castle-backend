package com.example.demo.Engine.Events;


import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Observable;

@Component
public class StorageUpdateScheduler extends Observable implements Runnable {

    private boolean done;

    public StorageUpdateScheduler() {
        done = true;
        new Thread(this).start();
    }

    @Override
    public void run() {
        //LocalDateTime systemTime = LocalDateTime.now();
        //int difference = 60 - systemTime.getMinute();
        //LocalDateTime eventTime = systemTime.plusMinutes(difference);
        while(!done) {
            LocalDateTime eventTime = LocalDateTime.now().plusSeconds(20);
            int difference = 20;
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
