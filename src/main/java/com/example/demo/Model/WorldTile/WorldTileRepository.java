package com.example.demo.Model.WorldTile;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WorldTileRepository extends JpaRepository<WorldTile, Integer> {

    List<WorldTile> findAll();
}
