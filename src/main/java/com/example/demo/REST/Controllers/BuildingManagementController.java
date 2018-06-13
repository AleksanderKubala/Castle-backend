package com.example.demo.REST.Controllers;

import com.example.demo.Model.CityTile.CityTile;
import com.example.demo.Model.Production.Production;
import com.example.demo.Model.Storage.Storage;
import com.example.demo.REST.ModelREST.ModelResponses.CityResponse;
import com.example.demo.REST.ModelREST.ModelResponses.CityTileResponse;
import com.example.demo.REST.ModelREST.ModelServices.ProductionService;
import com.example.demo.REST.ModelREST.ModelServices.StorageService;
import com.example.demo.REST.Requests.ConstructionRequest;
import com.example.demo.REST.Services.BuildingManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
public class BuildingManagementController {

    private BuildingManagementService managementService;

    @Autowired
    public BuildingManagementController(
            BuildingManagementService managementService
    ) {
        this.managementService = managementService;
    }

    @PostMapping("/citymanage/construct")
    public ResponseEntity<CityResponse> construct(@RequestBody ConstructionRequest request) {
        CityTile tile = managementService.constructBuilding(
                request.getCityId(), request.getRow(), request.getColumn(), request.getBuildingType());

        if(tile == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        CityTileResponse tileResponse = new CityTileResponse(tile);
        List<Storage> storages = managementService.retrieveCityStorage(tile.getCity());
        List<Production> productions = managementService.retrieveCityProduction(tile.getCity());
        CityResponse response = CityResponse.createResponse(tile.getCity(), storages, productions, null, tileResponse);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/citymanage/destroy")
    public ResponseEntity<CityResponse> destroy(@RequestBody ConstructionRequest request) {
        CityTile tile = managementService.destroyBuilding(
                request.getCityId(), request.getRow(), request.getColumn());
        if(tile == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        CityTileResponse tileResponse = new CityTileResponse(tile);
        List<Production> productions = managementService.retrieveCityProduction(tile.getCity());
        List<Storage> storage = managementService.retrieveCityStorage(tile.getCity());
        CityResponse response = CityResponse.createResponse(tile.getCity(), storage, productions, null, tileResponse);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
