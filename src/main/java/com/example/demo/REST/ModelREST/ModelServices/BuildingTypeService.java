package com.example.demo.REST.ModelREST.ModelServices;

import com.example.demo.Model.BuildingType.BuildingType;
import com.example.demo.Model.BuildingType.BuildingTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
public class BuildingTypeService {

    private BuildingTypeRepository buildingTypeRepository;

    @Autowired
    public BuildingTypeService(
            BuildingTypeRepository buildingTypeRepository
    ) {
        this.buildingTypeRepository = buildingTypeRepository;
    }

    public List<BuildingType> retrieveAllBuildingTypes() {
        return buildingTypeRepository.findAll();
    }

    public Optional<BuildingType> retrieveBuildingTypeByName(String name) {
        return buildingTypeRepository.findByName(name);
    }
}
