package com.example.demo.Engine;

import com.example.demo.Engine.Events.GameEvent;
import com.example.demo.Engine.Events.StorageUpdateEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

@Component
public class GameEventManager implements Observer {

    private GameEventCalendar calendar;

    @Autowired
    public GameEventManager(GameEventCalendar calendar) {
        this.calendar = calendar;
        calendar.addObserver(this);
    }

    @Override
    public void update(Observable o, Object arg) {
        if(arg.getClass() == StorageUpdateEvent.class) {
            System.out.println("Storage update!!!");
        }
    }


}
