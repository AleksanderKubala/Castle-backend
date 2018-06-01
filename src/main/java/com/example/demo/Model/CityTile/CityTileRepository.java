package com.example.demo.Model.CityTile;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CityTileRepository extends JpaRepository<CityTile, Integer> {

    List<CityTile> findAll();
}
