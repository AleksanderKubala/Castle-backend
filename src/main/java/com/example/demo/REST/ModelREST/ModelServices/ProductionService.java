package com.example.demo.REST.ModelREST.ModelServices;

import com.example.demo.Model.BuildingType.BuildingType;
import com.example.demo.Model.City.City;
import com.example.demo.Model.Production.Production;
import com.example.demo.Model.Production.ProductionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductionService {

    private ProductionRepository productionRepository;

    @Autowired
    public ProductionService(ProductionRepository productionRepository) {
        this.productionRepository = productionRepository;
    }

    public List<Production> retrieveBuildingProduction(BuildingType buildingType) {
        return productionRepository.findAllByBuildingType(buildingType);
    }


    public List<Production> retrieveCityProduction(City city) {
        return productionRepository.findAllByCity(city);
    }

    public void updateProduction(List<Production> productions) {
        productionRepository.saveAll(productions);
    }
}
