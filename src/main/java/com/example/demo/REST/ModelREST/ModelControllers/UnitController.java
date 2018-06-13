package com.example.demo.REST.ModelREST.ModelControllers;

import com.example.demo.Model.Production.Production;
import com.example.demo.Model.Requirement.Requirement;
import com.example.demo.Model.Unit.Unit;
import com.example.demo.REST.ModelREST.ModelResponses.UnitResponse;
import com.example.demo.REST.ModelREST.ModelServices.ProductionService;
import com.example.demo.REST.ModelREST.ModelServices.RequirementService;
import com.example.demo.REST.ModelREST.ModelServices.UnitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class UnitController {

    private UnitService unitService;
    private RequirementService requirementService;
    private ProductionService productionService;

    @Autowired
    public UnitController(
            UnitService unitService,
            RequirementService requirementService,
            ProductionService productionService) {
        this.unitService = unitService;
        this.requirementService = requirementService;
        this.productionService = productionService;
    }

    @GetMapping("/unit")
    public ResponseEntity<List<UnitResponse>> getUnits() {
        List<Unit> units = unitService.retrieveAllUnits();
        List<UnitResponse> response = new ArrayList<>();
        for(Unit unit: units) {
            List<Requirement> reqs = requirementService.retrieveUnitRequirements(unit);
            List<Production> prods = productionService.retrieveUnitProduction(unit);
            response.add(new UnitResponse(unit, reqs, prods));
        }

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
