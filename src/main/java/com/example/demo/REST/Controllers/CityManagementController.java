package com.example.demo.REST.Controllers;

import com.example.demo.Model.CityTile.CityTile;
import com.example.demo.REST.ModelREST.ModelResponses.CityTileResponse;
import com.example.demo.REST.Requests.ConstructionRequest;
import com.example.demo.REST.Services.CityManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class CityManagementController {

    private CityManagementService managementService;

    @Autowired
    public CityManagementController(CityManagementService managementService) {
        this.managementService = managementService;
    }

    @PostMapping("/citymanage/construct")
    public ResponseEntity<CityTileResponse> construct(@RequestBody ConstructionRequest request) {
        CityTile tile = managementService.constructBuilding(
                request.getCityId(), request.getRow(), request.getColumn(), request.getBuildingType());

        if(tile == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        CityTileResponse response = new CityTileResponse(tile);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/citymanage/destroy")
    public ResponseEntity<CityTileResponse> destroy(@RequestBody ConstructionRequest request) {
        CityTile tile = managementService.destroyBuilding(
                request.getCityId(), request.getRow(), request.getColumn());
        if(tile == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        CityTileResponse response = new CityTileResponse(tile);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
