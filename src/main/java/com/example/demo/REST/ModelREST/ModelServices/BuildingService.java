package com.example.demo.REST.ModelREST.ModelServices;

import com.example.demo.Model.Building.Building;
import com.example.demo.Model.Building.BuildingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class BuildingService {

    private BuildingRepository buildingRepository;

    @Autowired
    public BuildingService(
          BuildingRepository buildingRepository
    ) {
        this.buildingRepository = buildingRepository;
    }

    public Collection<Building> retrieveAllBuildings() {
        return buildingRepository.findAll();
    }
}
