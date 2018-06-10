package com.example.demo.Model.Production;

import com.example.demo.Model.BuildingType.BuildingType;
import com.example.demo.Model.City.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductionRepository extends JpaRepository<Production, Integer> {

    List<Production> findAll();

    List<Production> findAllByCity(City city);

    List<Production> findAllByBuildingType(BuildingType buildingType);
}
