package com.example.demo.REST.ModelREST.ModelControllers;

import com.example.demo.Model.City.City;
import com.example.demo.Model.CityTile.CityTile;
import com.example.demo.REST.ModelREST.ModelResponses.CityResponse;
import com.example.demo.REST.ModelREST.ModelResponses.CityTileResponse;
import com.example.demo.REST.ModelREST.ModelServices.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@CrossOrigin(origins = "http://localhost:4200/")
@RestController
public class CityController {

    private CityService cityService;

    @Autowired
    public CityController(CityService cityService) {
        this.cityService = cityService;
    }

    @GetMapping("/city")
    @CrossOrigin(origins = "http://localhost:4200/")
    public ResponseEntity<CityResponse> getCity() {
        City city = cityService.retrieveCity(1);
        if(city == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        List<CityTile> tiles = cityService.retrieveCityTiles(city);
        List<CityTileResponse> responseTiles = new ArrayList<>();
        for(CityTile tile: tiles) {
            responseTiles.add(new CityTileResponse(tile));
        }

        CityResponse response = new CityResponse(city, responseTiles);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
