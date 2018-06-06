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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

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

    @GetMapping("/building/type/{name}")
    public ResponseEntity<BuildingTypeResponse> getBuildingTypes(@PathVariable String name) {
        Optional<BuildingType> type = buildingTypeService.retrieveBuildingTypeByName(name);
        if(!type.isPresent())
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        BuildingTypeResponse response = new BuildingTypeResponse(type.get());

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
