package com.example.demo.Engine.Events;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public abstract class GameEvent implements Comparable<GameEvent> {

    private GameEventType type;
    private LocalDateTime time;

    protected GameEvent(GameEventType type, LocalDateTime time) {
        this.type = type;
        this.time = time;
    }

    @Override
    public int compareTo(GameEvent other) {
        return time.compareTo(other.getTime());
    }
}
