package com.example.demo.REST.ModelREST.ModelControllers;

import com.example.demo.Model.WorldStructure.WorldStructure;
import com.example.demo.REST.ModelREST.ModelResponses.WorldStructureResponse;
import com.example.demo.REST.ModelREST.ModelServices.WorldStructureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Collection;

@RestController
public class WorldStructureController {

    private WorldStructureService structureService;

    @Autowired
    public WorldStructureController (
            WorldStructureService structureService
    ) {
        this.structureService = structureService;
    }

    @GetMapping(name = "/structure")
    public ResponseEntity<Collection<WorldStructureResponse>> getAllStructures() {
        Collection<WorldStructure> structures = structureService.retrieveAllStructures();
        Collection<WorldStructureResponse> response = new ArrayList<>();
        for(WorldStructure structure: structures) {
            response.add(new WorldStructureResponse(structure));
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
