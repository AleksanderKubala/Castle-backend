package com.example.demo.Engine;

import com.example.demo.Engine.Events.GameEvent;
import com.example.demo.Model.City.City;
import com.example.demo.Model.Production.Production;
import com.example.demo.Model.Resource.Resource;
import com.example.demo.Model.Storage.Storage;
import com.example.demo.REST.Controllers.StorageUpdateController;
import com.example.demo.REST.ModelREST.ModelServices.CityService;
import com.example.demo.REST.ModelREST.ModelServices.ProductionService;
import com.example.demo.REST.ModelREST.ModelServices.StorageService;
import com.example.demo.REST.Services.StorageUpdateService;
import com.example.demo.WebSocket.GreetingController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class GameEventManager extends Observable implements Runnable {

    private CityService cityService;
    private StorageService storageService;
    private ProductionService productionService;
    private StorageUpdateController controller;
    private List<GameEvent> pendingEvents;
    private final Object semaphore = new Object();
    private final Object updateSemaphore = new Object();
    private int sleepTime;
    private boolean done;
    private boolean eventsProcessing;
    private boolean updateRequested;
    private Thread thread;

    @Autowired
    public GameEventManager(
            CityService cityService,
            StorageService storageService,
            ProductionService productionService,
            StorageUpdateController controller) {
        this.cityService = cityService;
        this.storageService = storageService;
        this.productionService = productionService;
        this.controller = controller;
        pendingEvents = new ArrayList<>();
        sleepTime = 70;
        done = false;
        eventsProcessing = false;
        updateRequested = false;
        this.thread = new Thread(this);
        this.thread.start();
    }

    @Override
    public void run() {
        while(!done) {
            if(pendingEvents.size() > 0) {
                GameEvent event = fetchEvent();
                handleEvent(event);
                controller.sendUpdate();
            } else {
                try {
                    Thread.sleep(sleepTime * 1000);
                } catch (InterruptedException e) {
                    System.out.println("Game event occurred");
                }
            }
        }
    }

    public void passEvents(GameEvent event) {
        synchronized(semaphore) {
            if(eventsProcessing) {
                try {
                    semaphore.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            eventsProcessing = true;
            pendingEvents.add(event);
            eventsProcessing  = false;
            semaphore.notify();
        }
        this.thread.interrupt();
    }

    public void requestUpdate() {
        synchronized(updateSemaphore) {
            updateRequested = true;
            try {
                updateSemaphore.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private GameEvent fetchEvent() {
        GameEvent event;
        synchronized (semaphore) {
            if (eventsProcessing) {
                try {
                    semaphore.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            eventsProcessing = true;
            event = pendingEvents.get(0);
            pendingEvents.remove(0);
            eventsProcessing = false;
            semaphore.notify();
        }
        return event;
    }

    private void handleEvent(GameEvent event) {
        switch (event.getType()) {
            case STORAGE_UPDATE:
                this.updateStorage();
                break;
        }
    }

    private void updateStorage() {
        List<City> cities = cityService.retrieveAllCities();
        List<Storage> storages = new ArrayList<>();
        for(City city: cities) {
            List<Production> productions = productionService.retrieveCityProduction(city);
            List<Storage> cityStorages = storageService.retrieveCityStorage(city);
            for(Production production: productions) {
                for(Storage storage: cityStorages) {
                    if(storage.getResource().equals(production.getResource())) {
                        Resource resource = storage.getResource();
                        int quantity = storage.getQuantity() + production.getQuantity();
                        if(quantity < 0) {
                            quantity = resource.getLoanable() ? quantity : 0;
                        }
                        storage.setQuantity(quantity);
                        storages.add(storage);
                    }
                }
            }
        }

        storageService.updateStorages(storages);
    }

}
