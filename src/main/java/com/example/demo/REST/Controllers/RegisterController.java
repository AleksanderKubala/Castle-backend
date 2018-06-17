package com.example.demo.REST.Controllers;

import com.example.demo.Model.City.City;
import com.example.demo.Model.Player.Player;
import com.example.demo.Model.World.World;
import com.example.demo.Model.WorldTile.WorldTile;
import com.example.demo.REST.ModelREST.ModelServices.*;
import com.example.demo.REST.Requests.RegisterRequest;
import com.example.demo.REST.Services.GarrisonManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class RegisterController {

    private CityService cityService;
    private WorldService worldService;
    private PlayerService playerService;
    private GarrisonService garrisonService;
    private ProductionService productionService;
    private StorageService storageService;


    @Autowired
    public RegisterController(
            CityService cityService,
            WorldService worldService,
            PlayerService playerService,
            GarrisonService garrisonService,
            ProductionService productionService,
            StorageService storageService
    ) {
        this.cityService = cityService;
        this.worldService = worldService;
        this.playerService = playerService;
        this.garrisonService = garrisonService;
        this.productionService = productionService;
        this.storageService = storageService;
    }

    @PostMapping("/signup")
    public ResponseEntity<?> signUp(@RequestBody RegisterRequest request) {
        Optional<Player> player = playerService.retrievePlayerByUsername(request.getUsername());
        if(player.isPresent())
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        Player newPlayer = playerService.registerNewPlayer(request.getUsername(), request.getEmail(), request.getPassword());
        if(newPlayer == null)
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);

        WorldTile worldTile = worldService.retrieveNonBoundaryWorldTile(newPlayer);
        City city = cityService.buildNewCity(newPlayer, worldTile, request.getCity(), true);
        cityService.createCityTiles(city);
        storageService.createCityStorage(city);
        productionService.createCityProduction(city);
        garrisonService.createCityGarrison(city);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
