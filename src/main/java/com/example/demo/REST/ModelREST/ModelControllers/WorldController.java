package com.example.demo.REST.ModelREST.ModelControllers;

import com.example.demo.Model.World.World;
import com.example.demo.Model.WorldTile.WorldTile;
import com.example.demo.REST.ModelREST.ModelResponses.WorldResponse;
import com.example.demo.REST.ModelREST.ModelResponses.WorldTileResponse;
import com.example.demo.REST.ModelREST.ModelServices.WorldService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@CrossOrigin(origins = "http://localhost:4200/")
@RestController
public class WorldController {

    private WorldService worldService;

    @Autowired
    public WorldController(WorldService worldService) {
        this.worldService = worldService;
    }

    @GetMapping("/world/{id}")
    @CrossOrigin(origins = "http://localhost:4200/")
    public ResponseEntity<WorldResponse> getWorld(@PathVariable Integer id) {
        Optional<World> world = worldService.retrieveWorldById(id);
        if(!world.isPresent())
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        List<WorldTile> tiles = worldService.retrieveWorldTiles(world.get());
        Collections.sort(tiles);
        List<WorldTileResponse> responseTiles = new ArrayList<>();
        for(WorldTile tile: tiles) {
            responseTiles.add(new WorldTileResponse(tile));
        }
        WorldResponse response = new WorldResponse(world.get(), responseTiles);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
