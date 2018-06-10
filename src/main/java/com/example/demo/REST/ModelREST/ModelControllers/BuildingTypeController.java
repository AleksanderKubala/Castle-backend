package com.example.demo.REST.ModelREST.ModelControllers;

import com.example.demo.Model.BuildingType.BuildingType;
import com.example.demo.Model.Production.Production;
import com.example.demo.Model.Requirement.Requirement;
import com.example.demo.REST.ModelREST.ModelResponses.BuildingTypeResponse;
import com.example.demo.REST.ModelREST.ModelResponses.ProductionResponse;
import com.example.demo.REST.ModelREST.ModelResponses.RequirementResponse;
import com.example.demo.REST.ModelREST.ModelServices.BuildingTypeService;
import com.example.demo.REST.ModelREST.ModelServices.ProductionService;
import com.example.demo.REST.ModelREST.ModelServices.RequirementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:4200/")
@RestController
public class BuildingTypeController {

    private BuildingTypeService buildingTypeService;
    private RequirementService requirementService;
    private ProductionService productionService;

    @Autowired
    public BuildingTypeController(
            BuildingTypeService buildingTypeService,
            RequirementService requirementService,
            ProductionService productionService) {
        this.buildingTypeService = buildingTypeService;
        this.requirementService = requirementService;
        this.productionService = productionService;
    }

    @GetMapping("/buildingtypes")
    public ResponseEntity<List<BuildingTypeResponse>> getBuildingTypes() {
        List<BuildingType> types = buildingTypeService.retrieveAllBuildingTypes();
        List<BuildingTypeResponse> response = new ArrayList<>();
        for(BuildingType type: types) {
            List<Production> productions = productionService.retrieveBuildingProduction(type);
            List<Requirement> requirements = requirementService.retrieveBuildingRequirements(type);
            response.add(new BuildingTypeResponse(type, productions, requirements));
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /*
    @GetMapping("/building/type/{name}")
    public ResponseEntity<BuildingTypeResponse> getBuildingTypes(@PathVariable String name) {
        Optional<BuildingType> type = buildingTypeService.retrieveBuildingTypeByName(name);
        if(!type.isPresent())
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        BuildingTypeResponse response = new BuildingTypeResponse(type.get());

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    */
    /*
    @GetMapping("/building/requirement")
    public ResponseEntity<List<RequirementResponse>> getBuildingRequirements() {
        List<Requirement> reqs = requirementService.retrieveBuildingRequirements();
        List<RequirementResponse> response = new ArrayList<>();
        for(Requirement req: reqs) {
            response.add(new RequirementResponse(req));
        }

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/building/production")
    public ResponseEntity<List<ProductionResponse>> getBuildingProductions() {
        List<Production> prods = productionService.retrieveBuildingProductions();
        List<ProductionResponse> response = new ArrayList<>();
        for(Production prod: prods) {
            response.add(new ProductionResponse(prod));
        }

        return new ResponseEntity<>(response, HttpStatus.OK);
    }*/
}
