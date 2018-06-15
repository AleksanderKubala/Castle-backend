package com.example.demo.Model.Building;

import com.example.demo.Model.BuildingType.BuildingType;
import com.example.demo.Model.City.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BuildingRepository extends JpaRepository<Building, Integer>{

    List<Building> findAll();

    List<Building> findAllByCityAndType(City city, BuildingType type);

    Integer countAllByCityAndType(City city, BuildingType type);
}
