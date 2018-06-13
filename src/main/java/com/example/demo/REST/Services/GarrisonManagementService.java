package com.example.demo.REST.Services;

import com.example.demo.Model.City.City;
import com.example.demo.Model.Garrison.Garrison;
import com.example.demo.Model.Unit.Unit;
import com.example.demo.REST.ModelREST.ModelServices.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class GarrisonManagementService extends CityManagementService {

    private UnitService unitService;
    private GarrisonService garrisonService;

    @Autowired
    public GarrisonManagementService (
            CityService cityService,
            StorageService storageService,
            RequirementService requirementService,
            ProductionService productionService,
            UnitService unitService,
            GarrisonService garrisonService
    ) {
        super(
                cityService,
                storageService,
                requirementService,
                productionService,
                garrisonService
        );
        this.garrisonService = garrisonService;
        this.cityService = cityService;
        this.unitService = unitService;
    }

    public Garrison recruit(Integer cityId, String unitName, Integer quantity) {
        Garrison garrison = determineGarrison(cityId, unitName);
        garrison.setQuantity(garrison.getQuantity() + quantity);

        depleteResources(garrison.getCity(),garrison.getUnit(), quantity, false);
        recalculateCityProduction(garrison.getCity(),garrison.getUnit(), quantity, false);
        garrisonService.updateGarrison(garrison);

        return garrison;
    }

    public Garrison dismiss(Integer cityId, String unitName, Integer quantity) {
        Garrison garrison = determineGarrison(cityId, unitName);
        garrison.setQuantity(garrison.getQuantity() - quantity);
        if(garrison.getQuantity() < 0)
            return null;

        depleteResources(garrison.getCity(),garrison.getUnit(), quantity, true);
        recalculateCityProduction(garrison.getCity(),garrison.getUnit(), quantity, true);
        garrisonService.updateGarrison(garrison);

        return garrison;
    }

    private Garrison determineGarrison(Integer cityId, String unitName) {
        Optional<City> city = cityService.retrieveCityById(cityId);
        if(!city.isPresent())
            return null;

        Optional<Unit> unit = unitService.retrieveUnitByName(unitName);
        if(!unit.isPresent()) {
            return null;
        }

        Optional<Garrison> opGarrison = garrisonService.retrieveCityGarrison(city.get(), unit.get());
        Garrison garrison;
        garrison = opGarrison.isPresent() ? opGarrison.get() : new Garrison(city.get(), unit.get());

        return garrison;
    }

}
