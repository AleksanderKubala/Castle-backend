package com.example.demo.REST.ModelREST.ModelServices;

import com.example.demo.Model.City.City;
import com.example.demo.Model.World.World;
import com.example.demo.Model.World.WorldRepository;
import com.example.demo.Model.WorldTile.WorldTile;
import com.example.demo.Model.WorldTile.WorldTileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
public class WorldService {

    private WorldRepository worldRepository;
    private WorldTileRepository worldTileRepository;

    @Autowired
    public WorldService(
            WorldRepository worldRepository,
            WorldTileRepository worldTileRepository
    ) {
        this.worldRepository = worldRepository;
        this.worldTileRepository = worldTileRepository;
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
}
