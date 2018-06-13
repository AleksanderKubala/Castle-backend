package com.example.demo.REST.Controllers;

import com.example.demo.Model.City.City;
import com.example.demo.Model.Garrison.Garrison;
import com.example.demo.Model.Production.Production;
import com.example.demo.Model.Storage.Storage;
import com.example.demo.REST.ModelREST.ModelResponses.CityResponse;
import com.example.demo.REST.ModelREST.ModelServices.CityService;
import com.example.demo.REST.ModelREST.ModelServices.ProductionService;
import com.example.demo.REST.Requests.GarrisonRequest;
import com.example.demo.REST.Services.GarrisonManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
public class GarrisonManagementController {

    private GarrisonManagementService managementService;

    @Autowired
    public GarrisonManagementController(
            GarrisonManagementService managementService
    ) {
        this.managementService = managementService;
    }

    @PostMapping("/recruit/{cityId}")
    public ResponseEntity<CityResponse> recruitUnit(
            @PathVariable Integer cityId,
            @RequestBody GarrisonRequest request
    ) {
        Garrison garrison = managementService.recruit(
                cityId,
                request.getUnitName(),
                request.getQuantity()
        );

        City city = garrison.getCity();
        List<Production> production = managementService.retrieveCityProduction(city);
        List<Storage> storage = managementService.retrieveCityStorage(city);
        List<Garrison> troops = managementService.retrieveCityGarrison(city);
        CityResponse response = CityResponse.createResponse(city, storage, production, troops);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/dismiss/{cityId}")
    public ResponseEntity<CityResponse> dismissUnit(
            @PathVariable Integer cityId,
            @RequestBody GarrisonRequest request
    ) {
        Garrison garrison = managementService.dismiss(
                cityId,
                request.getUnitName(),
                request.getQuantity()
        );

        if(garrison == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        City city = garrison.getCity();
        List<Production> productions = managementService.retrieveCityProduction(city);
        List<Storage> storage = managementService.retrieveCityStorage(city);
        List<Garrison> troops = managementService.retrieveCityGarrison(city);
        CityResponse response = CityResponse.createResponse(city, storage, productions, troops);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
