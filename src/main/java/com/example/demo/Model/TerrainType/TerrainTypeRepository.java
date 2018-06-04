package com.example.demo.Model.TerrainType;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TerrainTypeRepository extends JpaRepository<TerrainType, Integer> {

    List<TerrainType> findAll();

    Optional<TerrainType> findByName(String name);
}
