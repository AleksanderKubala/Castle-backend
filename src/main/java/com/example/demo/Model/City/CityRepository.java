package com.example.demo.Model.City;

import com.example.demo.Model.Player.Player;
import com.example.demo.Model.World.World;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CityRepository extends JpaRepository<City, Integer> {

    List<City> findAll();

    Optional<City> findByName(String name);

    List<City> findAllByPlayer(Player player);

    Integer countAllByPlayer(Player player);
}
