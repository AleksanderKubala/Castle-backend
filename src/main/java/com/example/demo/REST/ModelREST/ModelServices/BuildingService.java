package com.example.demo.REST.ModelREST.ModelServices;

import com.example.demo.Model.Building.Building;
import com.example.demo.Model.Building.BuildingRepository;
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
}
