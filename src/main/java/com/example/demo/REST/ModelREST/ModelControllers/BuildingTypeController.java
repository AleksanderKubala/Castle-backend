package com.example.demo.REST.ModelREST.ModelControllers;

import com.example.demo.Model.BuildingType.BuildingType;
import com.example.demo.Model.BuildingType.BuildingTypeRepository;
import com.example.demo.REST.ModelREST.ModelResponses.BuildingTypeResponse;
import com.example.demo.REST.ModelREST.ModelServices.BuildingTypeService;
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
public class BuildingTypeController {

    private BuildingTypeService buildingTypeService;

    @Autowired
    public BuildingTypeController( BuildingTypeService buildingTypeService) {
        this.buildingTypeService = buildingTypeService;
    }

    @GetMapping("/buildingtypes")
    public ResponseEntity<List<BuildingTypeResponse>> getBuildingTypes() {
        List<BuildingType> types = buildingTypeService.retrieveAllBuildingTypes();
        List<BuildingTypeResponse> response = new ArrayList<>();
        for(BuildingType type: types) {
            response.add(new BuildingTypeResponse(type));
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
