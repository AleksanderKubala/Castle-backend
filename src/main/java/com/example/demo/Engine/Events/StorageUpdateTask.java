package com.example.demo.Engine.Events;

import com.example.demo.Engine.GameEventManager;

import java.util.Observable;
import java.util.Observer;

public class StorageUpdateTask implements Runnable, Observer {

    private GameEventManager eventManager;
    private int checkInterval;
    private int checkRetry;
    private boolean done;


    public StorageUpdateTask(GameEventManager eventManager) {
        this.eventManager = eventManager;
        checkInterval = 1;
        checkRetry = 35;
        done = false;
        eventManager.addObserver(this);

    }

    @Override
    public void run() {
        for(int i = 0; (i < checkRetry) && (!done); i++) {
            try {
                Thread.sleep(checkInterval * 1000);
            } catch (InterruptedException e) {
                done = true;
            }
        }
        eventManager.deleteObserver(this);
    }

    @Override
    public void update(Observable o, Object arg) {
        done = true;
    }
}
