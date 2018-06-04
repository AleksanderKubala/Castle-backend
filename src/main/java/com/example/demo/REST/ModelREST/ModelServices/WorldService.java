package com.example.demo.REST.ModelREST.ModelServices;

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

    public World retrieveWorld(Integer worldNumber) {
        Optional<World> opWorld = worldRepository.findById(worldNumber);
        if(opWorld.isPresent())
            return opWorld.get();
        else
            return null;
    }

    public List<WorldTile> retrieveWorldTiles(World world) {
        return worldTileRepository.findAllByWorld(world);
    }
}
