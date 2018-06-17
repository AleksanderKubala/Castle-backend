package com.example.demo.REST.ModelREST.ModelServices;

import com.example.demo.Model.City.City;
import com.example.demo.Model.Player.Player;
import com.example.demo.Model.World.World;
import com.example.demo.Model.World.WorldRepository;
import com.example.demo.Model.WorldTile.WorldTile;
import com.example.demo.Model.WorldTile.WorldTileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class WorldService {

    private WorldRepository worldRepository;
    private WorldTileRepository worldTileRepository;
    private CityService cityService;

    @Autowired
    public WorldService(
            WorldRepository worldRepository,
            WorldTileRepository worldTileRepository,
            CityService cityService
    ) {
        this.worldRepository = worldRepository;
        this.worldTileRepository = worldTileRepository;
        this.cityService = cityService;
    }

    public Optional<World> retrieveWorldById(Integer id) {
        return worldRepository.findById(id);
    }

    public List<WorldTile> retrieveWorldTiles(World world) {
        return worldTileRepository.findAllByWorld(world);
    }

    public Optional<WorldTile> retrieveWorldTile(Integer worldId, Integer row, Integer column) {
        Optional<World> world = worldRepository.findById(worldId);
        if(!world.isPresent())
            return Optional.empty();
        return this.worldTileRepository.findByWorldAndRowNumberAndColumnNumber(world.get(), row, column);
    }

    public WorldTile updateTile(WorldTile tile, City city) {
        tile.setCity(city);
        worldTileRepository.save(tile);
        return tile;
    }

    public WorldTile retrieveNonBoundaryWorldTile(Player player) {
        World world = player.getWorld();
        int boundaryMargin = 2 + 1;
        List<WorldTile> tiles =worldTileRepository.findAllByWorldAndCityNullAndStructureNullAndColumnNumberGreaterThanAndColumnNumberLessThanAndRowNumberGreaterThanAndRowNumberLessThan(
                world,
                boundaryMargin,
                world.getColumns() - boundaryMargin,
                boundaryMargin,
                world.getRows() - boundaryMargin
        );

        Random random = new Random();
        return tiles.get(random.nextInt(tiles.size()));
    }
}
