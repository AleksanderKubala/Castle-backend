package com.example.demo.REST.ModelREST.ModelControllers;

import com.example.demo.Model.City.City;
import com.example.demo.Model.CityTile.CityTile;
import com.example.demo.Model.Player.Player;
import com.example.demo.REST.ModelREST.ModelResponses.CityResponse;
import com.example.demo.REST.ModelREST.ModelResponses.CityTileResponse;
import com.example.demo.REST.ModelREST.ModelServices.CityService;
import com.example.demo.REST.ModelREST.ModelServices.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:4200/")
@RestController
public class CityController {

    private CityService cityService;
    private PlayerService playerService;

    @Autowired
    public CityController(CityService cityService, PlayerService playerService) {
        this.cityService = cityService;
        this.playerService = playerService;
    }

    @GetMapping("/city/{id}")
    @CrossOrigin(origins = "http://localhost:4200/")
    public ResponseEntity<CityResponse> getCity(@PathVariable Integer id) {
        Optional<City> city = cityService.retrieveCityById(id);
        if(!city.isPresent())
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        List<CityTile> tiles = cityService.retrieveCityTiles(city.get());
        Collections.sort(tiles);
        List<CityTileResponse> responseTiles = new ArrayList<>();
        for(CityTile tile: tiles) {
            responseTiles.add(new CityTileResponse(tile));
        }

        CityResponse response = new CityResponse(city.get(), responseTiles);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/city/player/{username}")
    public ResponseEntity<List<CityResponse>> getUserCities(@PathVariable String username) {
        Optional<Player> player = playerService.retrievePlayerByUsername(username);
        if(!player.isPresent())
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        List<City> cities = cityService.retrieveCitiesByPlayer(player.get());
        List<CityResponse> response = new ArrayList<>();
        for(City city: cities) {
            response.add(new CityResponse(city));
        }

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
