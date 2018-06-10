package com.example.demo.REST.ModelREST.ModelControllers;

import com.example.demo.Model.City.City;
import com.example.demo.Model.CityTile.CityTile;
import com.example.demo.Model.Player.Player;
import com.example.demo.Model.Production.Production;
import com.example.demo.Model.Storage.Storage;
import com.example.demo.REST.ModelREST.ModelResponses.CityResponse;
import com.example.demo.REST.ModelREST.ModelResponses.CityTileResponse;
import com.example.demo.REST.ModelREST.ModelResponses.ProductionResponse;
import com.example.demo.REST.ModelREST.ModelResponses.StorageResponse;
import com.example.demo.REST.ModelREST.ModelServices.CityService;
import com.example.demo.REST.ModelREST.ModelServices.PlayerService;
import com.example.demo.REST.ModelREST.ModelServices.ProductionService;
import com.example.demo.REST.ModelREST.ModelServices.StorageService;
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
    private ProductionService productionService;
    private StorageService storageService;

    @Autowired
    public CityController(
            CityService cityService,
            PlayerService playerService,
            ProductionService productionService,
            StorageService storageService) {
        this.cityService = cityService;
        this.playerService = playerService;
        this.productionService = productionService;
        this.storageService = storageService;
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

        List<Storage> storage = storageService.retrieveCityStorage(city.get());
        List<Production> productions = productionService.retrieveCityProduction(city.get());

        CityResponse response = new CityResponse(city.get(), storage, productions, responseTiles);

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
            List<Storage> storage = storageService.retrieveCityStorage(city);
            response.add(new CityResponse(city, storage));
        }

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /*

    @GetMapping("/city/{id}/production")
    public ResponseEntity<List<ProductionResponse>> getCityProduction(@PathVariable Integer id) {
        Optional<City> city = cityService.retrieveCityById(id);
        if(!city.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        List<Production> prods = productionService.retrieveCityProduction(city.get());
        List<ProductionResponse> response = new ArrayList<>();
        for(Production prod: prods) {
            response.add(new ProductionResponse(prod));
        }

        return new ResponseEntity<>(response, HttpStatus.OK);
    }*/
}
