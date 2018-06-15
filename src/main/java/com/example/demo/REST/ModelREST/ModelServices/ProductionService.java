package com.example.demo.REST.ModelREST.ModelServices;

import com.example.demo.Model.BuildingType.BuildingType;
import com.example.demo.Model.City.City;
import com.example.demo.Model.Production.Production;
import com.example.demo.Model.Production.ProductionRepository;
import com.example.demo.Model.Resource.Resource;
import com.example.demo.Model.Unit.Unit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductionService {

    private ProductionRepository productionRepository;
    private ResourceService resourceService;

    @Autowired
    public ProductionService(
            ProductionRepository productionRepository,
            ResourceService resourceService
    ) {
        this.productionRepository = productionRepository;
        this.resourceService = resourceService;
    }

    public List<Production> retrieveBuildingProduction(BuildingType buildingType) {
        return productionRepository.findAllByBuildingType(buildingType);
    }


    public List<Production> retrieveCityProduction(City city) {
        return productionRepository.findAllByCity(city);
    }

    public List<Production> retrieveUnitProduction(Unit unit) {
        return productionRepository.findAllByUnit(unit);
    }

    public void updateProduction(List<Production> productions) {
        productionRepository.saveAll(productions);
    }

    public List<Production> createCityProduction(City city) {
        List<Resource> resources = resourceService.retrieveResources();
        List<Production> productions = new ArrayList<>();

        for(Resource resource: resources) {
            Production production = new Production();
            production.setCity(city);
            production.setResource(resource);
            production.setQuantity(0);
            productions.add(production);
        }
        productionRepository.saveAll(productions);

        return productions;
    }
}
