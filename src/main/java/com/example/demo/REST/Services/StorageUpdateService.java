package com.example.demo.REST.Services;

import com.example.demo.Engine.GameEventManager;
import com.example.demo.Model.City.City;
import com.example.demo.Model.Storage.Storage;
import com.example.demo.REST.ModelREST.ModelResponses.CityResponse;
import com.example.demo.REST.ModelREST.ModelServices.CityService;
import com.example.demo.REST.ModelREST.ModelServices.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Supplier;

@Service
public class StorageUpdateService {

    private CityService cityService;
    private StorageService storageService;
    private GameEventManager eventManager;
    private boolean done;
    private int resultAwaitTime;
    private int checkInterval;

    @Autowired
    public StorageUpdateService(
            CityService cityService,
            StorageService storageService,
            GameEventManager eventManager) {
        this.cityService = cityService;
        this.storageService = storageService;
        this.eventManager = eventManager;
        done = false;
        resultAwaitTime = 65;
        checkInterval = 1;
    }

}
