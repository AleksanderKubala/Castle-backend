package com.example.demo.REST.ModelREST.ModelControllers;

import com.example.demo.Model.WorldStructureType.WorldStructureType;
import com.example.demo.Model.WorldStructureType.WorldStructureTypeRepository;
import com.example.demo.REST.ModelREST.ModelResponses.WorldStructureTypeResponse;
import com.example.demo.REST.ModelREST.ModelServices.WorldStructureTypeService;
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
public class WorldStructureTypeController {

    private WorldStructureTypeService structureTypeService;

    @Autowired
    public WorldStructureTypeController(WorldStructureTypeService structureTypeService) {
        this.structureTypeService = structureTypeService;
    }

    @GetMapping("/structuretypes")
    public ResponseEntity<List<WorldStructureTypeResponse>> getStructureTypes() {
        List<WorldStructureType> types = structureTypeService.retrieveAllStructureTypes();
        List<WorldStructureTypeResponse> response = new ArrayList<>();
        for(WorldStructureType type: types) {
            response.add(new WorldStructureTypeResponse(type));
        }

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
