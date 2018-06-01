package com.example.demo.REST.ModelREST.ModelControllers;

import com.example.demo.Model.TerrainType.TerrainType;
import com.example.demo.REST.ModelREST.ModelResponses.TerrainTypeResponse;
import com.example.demo.REST.ModelREST.ModelServices.TerrainTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@RestController
public class TerrainTypeController {

    private TerrainTypeService terrainService;

    @Autowired
    public TerrainTypeController(
            TerrainTypeService terrainService
    ) {
        this.terrainService = terrainService;
    }

    @GetMapping(name = "/terrain")
    public ResponseEntity<Collection<TerrainTypeResponse>> getTerrainTypes() {
        Collection<TerrainType> terrainTypes = terrainService.retrieveAllTerrain();
        Collection<TerrainTypeResponse> response = new ArrayList<>();
        for(TerrainType terrain: terrainTypes) {
            response.add(new TerrainTypeResponse(terrain));
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
