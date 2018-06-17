package com.example.demo.REST.Controllers;

import com.example.demo.Model.City.City;
import com.example.demo.Model.CityTile.CityTile;
import com.example.demo.Model.Garrison.Garrison;
import com.example.demo.Model.Player.Player;
import com.example.demo.Model.Production.Production;
import com.example.demo.Model.Storage.Storage;
import com.example.demo.Model.Unit.UnitTypes;
import com.example.demo.Model.WorldTile.WorldTile;
import com.example.demo.REST.ModelREST.ModelResponses.CityResponse;
import com.example.demo.REST.ModelREST.ModelResponses.CityTileResponse;
import com.example.demo.REST.ModelREST.ModelResponses.GarrisonResponse;
import com.example.demo.REST.ModelREST.ModelResponses.WorldTileResponse;
import com.example.demo.REST.ModelREST.ModelServices.*;
import com.example.demo.REST.Requests.SettlementRequest;
import com.example.demo.REST.Responses.SettlementResponse;
import com.example.demo.REST.Services.GarrisonManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RestController
public class SettlementController {

    private CityService cityService;
    private WorldService worldService;
    private PlayerService playerService;
    private GarrisonManagementService garrisonManagementService;
    private GarrisonService garrisonService;
    private ProductionService productionService;
    private StorageService storageService;


    @Autowired
    public SettlementController(
            CityService cityService,
            WorldService worldService,
            PlayerService playerService,
            GarrisonManagementService garrisonManagementService,
            GarrisonService garrisonService,
            ProductionService productionService,
            StorageService storageService
    ) {
        this.cityService = cityService;
        this.worldService = worldService;
        this.playerService = playerService;
        this.garrisonManagementService = garrisonManagementService;
        this.garrisonService = garrisonService;
        this.productionService = productionService;
        this.storageService = storageService;
    }

    @PostMapping("/settle")
    public ResponseEntity<SettlementResponse> settle(@RequestBody SettlementRequest request) {
        Optional<Player> player = playerService.retrievePlayerByUsername(request.getPlayer());
        Optional<WorldTile> tile = worldService.retrieveWorldTile(request.getWorld(), request.getTileRow(), request.getTileColumn());
        if((!player.isPresent()) || (!tile.isPresent()) )
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        Garrison settlers = garrisonManagementService.dismiss(request.getCity(), UnitTypes.SETTLER.name, 1);
        if(settlers == null)
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        City city = cityService.buildNewCity(player.get(), tile.get(), false);
        if(city == null)
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);

        List<CityTile> cityTiles = cityService.createCityTiles(city);
        if(cityTiles == null)
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);

        List<Storage> storage = storageService.createCityStorage(city);
        List<Production> production = productionService.createCityProduction(city);
        List<Garrison> garrison = garrisonService.createCityGarrison(city);
        WorldTile worldTile = worldService.updateTile(tile.get(), city);

        WorldTileResponse tileResponse = new WorldTileResponse(tile.get());
        CityResponse cityResponse = CityResponse.createResponse(city, storage, production, garrison);
        tileResponse.setCity(cityResponse);
        tileResponse.setStructureType(city.getType().getName());
        GarrisonResponse garrisonResponse = new GarrisonResponse(settlers);

        SettlementResponse response = new SettlementResponse(tileResponse, Collections.singletonList(garrisonResponse));

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
