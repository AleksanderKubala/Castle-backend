package com.example.demo.REST.ModelREST.ModelServices;

import com.example.demo.Model.BuildingType.BuildingType;
import com.example.demo.Model.Requirement.Requirement;
import com.example.demo.Model.Requirement.RequirementRepository;
import com.example.demo.Model.Unit.Unit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RequirementService {

    private RequirementRepository requirementRepository;

    @Autowired
    public RequirementService(RequirementRepository requirementRepository) {
        this.requirementRepository = requirementRepository;
    }

    public List<Requirement> retrieveBuildingRequirements(BuildingType buildingType) {
        return requirementRepository.findAllByBuildingType(buildingType);
    }

    public List<Requirement> retrieveUnitRequirements(Unit unit) {
        return requirementRepository.findAllByUnit(unit);
    }

}
