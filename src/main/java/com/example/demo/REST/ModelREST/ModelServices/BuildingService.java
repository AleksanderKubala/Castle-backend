package com.example.demo.REST.ModelREST.ModelServices;

import com.example.demo.Model.Building.Building;
import com.example.demo.Model.Building.BuildingRepository;
import com.example.demo.Model.BuildingType.BuildingType;
import com.example.demo.Model.City.City;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BuildingService {

    private BuildingRepository buildingRepository;

    @Autowired
    public BuildingService(BuildingRepository buildingRepository) {
        this.buildingRepository = buildingRepository;
    }

    public void store(Building building) {
        buildingRepository.save(building);
    }

    public void remove(Building building) {
        buildingRepository.delete(building);
    }

    public Integer countBy(City city, BuildingType type) {
        return this.buildingRepository.countAllByCityAndType(city, type);
    }
}
