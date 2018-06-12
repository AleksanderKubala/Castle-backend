package com.example.demo.Engine;

import com.example.demo.Engine.Events.GameEvent;
import com.example.demo.Engine.Events.StorageUpdateEvent;
import com.example.demo.Engine.Events.StorageUpdateScheduler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

@Service
public class GameEventCalendar extends Observable implements Runnable, Observer {

    private List<GameEvent> eventCalendar;
    private Boolean done;
    private int defaultSleepTime;
    private Thread calendarThread;
    private GameEventManager eventManager;

    @Autowired
    public GameEventCalendar(
            GameEventManager eventManager,
            StorageUpdateScheduler storageScheduler) {
        this.eventCalendar = new ArrayList<>();
        this.eventManager = eventManager;
        done = false;
        defaultSleepTime = 70;
        storageScheduler.addObserver(this);
        calendarThread = new Thread(this);
        calendarThread.start();
    }

    @Override
    public void run() {
        long sleepTime;
        while(!done) {
            if(eventCalendar.size() > 0) {
                GameEvent event = eventCalendar.get(0);
                sleepTime = countTimeDifference(event.getTime());
            } else {
                sleepTime = defaultSleepTime * 1000;
            }
            try {
                Thread.sleep(sleepTime);
            } catch (InterruptedException e) {
                continue;
            }
            if(eventCalendar.size() > 0) {
                GameEvent currentEvent = eventCalendar.get(0);
                eventCalendar.remove(0);
                eventManager.passEvents(currentEvent);
                clearChanged();
            }
        }

    }

    @Override
    public void update(Observable o, Object arg) {
        if((arg == null) || (!(arg instanceof GameEvent)))
            return;
        GameEvent newEvent = (GameEvent)arg;
        boolean placed = false;
        int index;
        for(index = 0 ; (index < eventCalendar.size()) && (!placed); index++) {
            GameEvent event = eventCalendar.get(index);
            if(newEvent.compareTo(event) < 0) {
                placed = true;
                eventCalendar.add(index, newEvent);
            }
        }
        if(!placed)
            eventCalendar.add(newEvent);
        setChanged();
        if(index == 0)
            calendarThread.interrupt();

    }

    private long countTimeDifference(LocalDateTime time) {
        int secondsOfTime = time.toLocalTime().toSecondOfDay();
        int secondsOfNow = LocalDateTime.now().toLocalTime().toSecondOfDay();
        return (secondsOfTime - secondsOfNow)*1000;
    }

}
