package com.example.demo.REST.Services;

import com.example.demo.Engine.Events.GameEvent;
import com.example.demo.Engine.Events.StorageUpdateEvent;
import com.example.demo.Engine.Events.StorageUpdateTask;
import com.example.demo.Engine.GameEventManager;
import com.example.demo.REST.ModelREST.ModelResponses.CityResponse;
import com.example.demo.REST.ModelREST.ModelServices.CityService;
import com.example.demo.REST.ModelREST.ModelServices.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.async.DeferredResult;


import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

@Service
public class StorageUpdateService {

    private CityService cityService;
    private StorageService storageService;
    private GameEventManager eventManager;
    private ExecutorService executor;
    private boolean requestMade;
    private boolean done;
    private int resultAwaitTime;

    @Autowired
    public StorageUpdateService(
            CityService cityService,
            StorageService storageService,
            GameEventManager eventManager) {
        this.cityService = cityService;
        this.storageService = storageService;
        this.eventManager = eventManager;
        this.executor = Executors.newSingleThreadExecutor();
        requestMade = false;
        done = false;
        resultAwaitTime = 65;
    }

    /*
    public GameEvent getStorageUpdate(DeferredResult<List<CityResponse>> result) {
        result.onCompletion(new StorageUpdateTask(eventManager));
        /*StorageUpdateTask update = new StorageUpdateTask(eventManager);
        Future<StorageUpdateEvent> future = executor.submit(new StorageUpdateTask(eventManager));
        try {
            return future.get();
        } catch (InterruptedException | ExecutionException e) {
            return null;
        }

    }
    */

}
