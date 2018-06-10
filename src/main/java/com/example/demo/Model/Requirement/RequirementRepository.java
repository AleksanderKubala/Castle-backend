package com.example.demo.Model.Requirement;

import com.example.demo.Model.BuildingType.BuildingType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RequirementRepository extends JpaRepository<Requirement, Integer> {

    List<Requirement> findAll();

    List<Requirement> findAllByBuildingType(BuildingType buildingType);

}
