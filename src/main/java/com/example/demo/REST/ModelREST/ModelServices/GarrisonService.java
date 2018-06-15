package com.example.demo.REST.ModelREST.ModelServices;

import com.example.demo.Model.City.City;
import com.example.demo.Model.Garrison.Garrison;
import com.example.demo.Model.Garrison.GarrisonRepository;
import com.example.demo.Model.Unit.Unit;
import com.example.demo.REST.ModelREST.ModelResponses.GarrisonResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class GarrisonService {

    private GarrisonRepository garrisonRepository;
    private UnitService unitService;

    @Autowired
    public GarrisonService(
            GarrisonRepository garrisonRepository,
            UnitService unitService
    ) {
        this.garrisonRepository = garrisonRepository;
        this.unitService = unitService;
    }

    public List<Garrison> retrieveCityGarrison(City city) {
        return this.garrisonRepository.findAllByCity(city);
    }

    public List<Garrison> retrieveCityGarrisonWithUnitsMoreThan(City city, Integer quantity) {
        return this.garrisonRepository.findAllByCityAndQuantityGreaterThan(city, quantity);
    }

    public Optional<Garrison> retrieveCityGarrison(City city, Unit unit) {
        return this.garrisonRepository.findByCityAndUnit(city, unit);
    }

    public void updateGarrison(Garrison garrison) {
        this.garrisonRepository.save(garrison);
    }

    public void updateGarrison(List<Garrison> garrison) {
        this.garrisonRepository.saveAll(garrison);
    }

    public List<Garrison> createCityGarrison(City city) {
        List<Garrison> garrison = new ArrayList<>();
        List<Unit> units = unitService.retrieveAllUnits();
        for (Unit unit : units) {
            Garrison troop = new Garrison();
            troop.setCity(city);
            troop.setUnit(unit);
            troop.setQuantity(0);
            troop.setTotalHealth(troop.getQuantity()*unit.getHealth());
            garrison.add(troop);
        }

        garrisonRepository.saveAll(garrison);

        return garrison;
    }

}
