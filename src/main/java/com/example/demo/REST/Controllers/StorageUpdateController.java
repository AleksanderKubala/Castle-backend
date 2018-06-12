package com.example.demo.REST.Controllers;

import com.example.demo.Engine.Events.StorageUpdateEvent;
import com.example.demo.Model.City.City;
import com.example.demo.Model.Storage.Storage;
import com.example.demo.REST.ModelREST.ModelResponses.CityResponse;
import com.example.demo.REST.ModelREST.ModelServices.CityService;
import com.example.demo.REST.ModelREST.ModelServices.StorageService;
import com.example.demo.REST.Services.StorageUpdateService;
import org.springframework.beans.factory.annotation.Autowired;
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

    private StorageUpdateService storageUpdateService;
    private CityService cityService;
    private StorageService storageService;

    @Autowired
    public StorageUpdateController(
            StorageUpdateService storageUpdateService,
            CityService cityService,
            StorageService storageService) {
        this.cityService = cityService;
        this.storageService = storageService;
        this.storageUpdateService = storageUpdateService;
    }


    /*
    @GetMapping("/update")
    public @ResponseBody DeferredResult<List<CityResponse>> getStorageUpdate() {
        final DeferredResult<List<CityResponse>> deferredResult = new DeferredResult<>();
        storageUpdateService.getStorageUpdate(deferredResult);

        return deferredResult;
    }

    private List<CityResponse> formResponse() {
        List<City> cities = cityService.retrieveAllCities();
        List<CityResponse> response = new ArrayList<>();
        for(City city: cities) {
            List<Storage> storages = storageService.retrieveCityStorage(city);
            response.add(CityResponse.createResponse(city, storages));
        }

        return response;
    }
*/

}
