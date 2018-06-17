package com.example.demo.Model.WorldTile;

import com.example.demo.Model.World.World;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Repository
public interface WorldTileRepository extends JpaRepository<WorldTile, Integer> {

    List<WorldTile> findAll();

    List<WorldTile> findAllByWorld(World world);

    Optional<WorldTile> findByWorldAndRowNumberAndColumnNumber(World world, Integer rowNumber, Integer colNumber);

    List<WorldTile> findAllByWorldAndCityNullAndStructureNullAndColumnNumberGreaterThanAndColumnNumberLessThanAndRowNumberGreaterThanAndRowNumberLessThan(
            World world,
            Integer minCol,
            Integer maxCol,
            Integer minRow,
            Integer maxRow
    );
}
