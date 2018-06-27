package com.example.demo.WebSocket;

import com.example.demo.Engine.GameEventManager;
import com.example.demo.Model.City.City;
import com.example.demo.Model.Storage.Storage;
import com.example.demo.REST.ModelREST.ModelResponses.CityResponse;
import com.example.demo.REST.ModelREST.ModelServices.CityService;
import com.example.demo.REST.ModelREST.ModelServices.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

@Controller()
public class GreetingController implements Observer {

    private SimpMessagingTemplate template;
    //private GameEventManager eventManager;
    private CityService cityService;
    private StorageService storageService;


    @Autowired
    public GreetingController(
            SimpMessagingTemplate template,
            CityService citySerivce,
            StorageService storageService
    ) {
        this.template = template;
        //this.eventManager = eventManager;
        this.cityService = citySerivce;
        this.storageService = storageService;
        //this.eventManager.addObserver(this);
        //this.template.setUserDestinationPrefix("http://localhost:4200");
        //this.template.setDefaultDestination("/update");
    }

    public GreetingController() {}

    @GetMapping("/socket")
    public void validate() {
        System.out.println("Prawie...");
    }

    public void fireGreeting() {
        List<City> cities = cityService.retrieveAllCities();
        List<CityResponse> response = new ArrayList<>();

        for(City city: cities) {
            List<Storage> storage = storageService.retrieveCityStorage(city);
            response.add(CityResponse.createResponse(city, storage));
        }

        this.template.convertAndSend("http://localhost:4200/update", response);
    }

    @Override
    public void update(Observable o, Object arg) {
        fireGreeting();
    }
}