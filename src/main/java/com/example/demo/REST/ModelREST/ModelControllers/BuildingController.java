package com.example.demo.REST.ModelREST.ModelControllers;

import com.example.demo.Model.Building.Building;
import com.example.demo.REST.ModelREST.ModelResponses.BuildingResponse;
import com.example.demo.REST.ModelREST.ModelServices.BuildingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Collection;

@RestController
public class BuildingController {

    private BuildingService buildingService;

    @Autowired
    public BuildingController (
            BuildingService buildingService
    ) {
        this.buildingService = buildingService;
    }

    @GetMapping(name = "/building")
    public ResponseEntity<Collection<BuildingResponse>> getAllBuildings() {
        Collection<Building> buildings = buildingService.retrieveAllBuildings();
        Collection<BuildingResponse> response = new ArrayList<>();
        for(Building building: buildings) {
            response.add(new BuildingResponse(building));
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
