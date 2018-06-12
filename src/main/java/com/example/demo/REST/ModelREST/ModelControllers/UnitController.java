package com.example.demo.REST.ModelREST.ModelControllers;

import com.example.demo.Model.Unit.Unit;
import com.example.demo.REST.ModelREST.ModelResponses.UnitResponse;
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

    @Autowired
    public UnitController(UnitService unitService) {
        this.unitService = unitService;
    }

    @GetMapping("/unit")
    public ResponseEntity<List<UnitResponse>> getUnits() {
        List<Unit> units = unitService.retrieveAllUnits();
        List<UnitResponse> response = new ArrayList<>();
        for(Unit unit: units) {
            response.add(new UnitResponse(unit));
        }

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
