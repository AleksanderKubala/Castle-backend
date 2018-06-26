package com.example.demo.REST.ModelREST.ModelControllers;

import com.example.demo.Model.TerrainType.TerrainType;
import com.example.demo.Model.TerrainType.TerrainTypeRepository;
import com.example.demo.REST.ModelREST.ModelResponses.TerrainTypeResponse;
import com.example.demo.REST.ModelREST.ModelServices.TerrainTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@RestController
public class TerrainTypeController {

    private TerrainTypeService terrainService;

    @Autowired
    public TerrainTypeController(TerrainTypeService terrainService) {
        this.terrainService = terrainService;
    }

    @GetMapping("/terraintypes")
    public ResponseEntity<List<TerrainTypeResponse>> getTerrainTypes() {
        List<TerrainType> types = terrainService.retrieveAllTerrain();
        List<TerrainTypeResponse> response = new ArrayList<>();
        for(TerrainType type: types) {
            response.add(new TerrainTypeResponse(type));
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/terrain/type/{name}")
    public ResponseEntity<TerrainTypeResponse> getTerrainTypes(@PathVariable String name) {
        Optional<TerrainType> terrain = terrainService.retrieveTerrainTypeByName(name);
        if(!terrain.isPresent())
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        TerrainTypeResponse response = new TerrainTypeResponse(terrain.get());

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
