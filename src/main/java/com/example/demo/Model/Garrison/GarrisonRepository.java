package com.example.demo.Model.Garrison;

import com.example.demo.Model.City.City;
import com.example.demo.Model.Unit.Unit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface GarrisonRepository extends JpaRepository<Garrison, Integer>{

    List<Garrison> findAll();

    List<Garrison> findAllByCity(City city);

    Optional<Garrison> findByCityAndUnit(City city, Unit unit);
}
