package com.example.demo.REST.Controllers;

import com.example.demo.Engine.Events.StorageUpdateEvent;
import com.example.demo.Model.City.City;
import com.example.demo.Model.Storage.Storage;
import com.example.demo.REST.ModelREST.ModelResponses.CityResponse;
import com.example.demo.REST.ModelREST.ModelServices.CityService;
import com.example.demo.REST.ModelREST.ModelServices.StorageService;
import com.example.demo.REST.Responses.UpdateResponse;
import com.example.demo.REST.Services.StorageUpdateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletableFuture;

@RestController
public class StorageUpdateController {

    private SimpMessagingTemplate template;
    private CityService cityService;
    private StorageService storageService;

    @Autowired
    public StorageUpdateController(
            SimpMessagingTemplate template,
            CityService cityService,
            StorageService storageService) {
        this.template = template;
        this.cityService = cityService;
        this.storageService = storageService;
    }

    public void sendUpdate() {
        List<City> cities = cityService.retrieveAllCities();
        List<CityResponse> cityResponse = new ArrayList<>();

        for(City city: cities) {
            List<Storage> storage = storageService.retrieveCityStorage(city);
            cityResponse.add(CityResponse.createResponse(city, storage));
        }

        UpdateResponse response = new UpdateResponse(cityResponse);

        this.template.convertAndSend("/update", response);
    }

}
