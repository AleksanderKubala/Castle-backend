package com.example.demo.Model.CityTile;

import com.example.demo.Model.City.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Repository
public interface CityTileRepository extends JpaRepository<CityTile, Integer> {

    List<CityTile> findAll();

    List<CityTile> findAllByCity(City city);

    Optional<CityTile> findByCityAndRowNumberAndColumnNumber(City city, Integer rowNumber, Integer columnNumber);
}
