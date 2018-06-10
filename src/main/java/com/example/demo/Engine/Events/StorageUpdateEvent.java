package com.example.demo.Engine.Events;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class StorageUpdateEvent extends GameEvent {

    public StorageUpdateEvent(LocalDateTime time) {
        super(GameEventType.STORAGE_UPDATE, time);
    }
}
