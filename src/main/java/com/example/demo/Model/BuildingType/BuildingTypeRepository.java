package com.example.demo.Model.BuildingType;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BuildingTypeRepository extends JpaRepository<BuildingType, Integer> {

    List<BuildingType> findAll();

    Optional<BuildingType> findByName(String name);

    List<BuildingType> findAllByMainBuilding(Boolean isMain);
}
