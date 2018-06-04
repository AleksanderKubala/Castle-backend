package com.example.demo.Test;

import com.example.demo.Model.Building.BuildingRepository;
import com.example.demo.Model.BuildingType.BuildingType;
import com.example.demo.Model.City.City;
import com.example.demo.Model.CityTile.CityTile;
import com.example.demo.Model.Player.Player;
import com.example.demo.Model.TerrainType.TerrainType;
import com.example.demo.Model.TerrainType.TerrainTypeRepository;
import com.example.demo.Model.World.World;
import com.example.demo.Model.WorldStructure.WorldStructureRepository;
import com.example.demo.Model.WorldStructureType.WorldStructureType;
import com.example.demo.Model.WorldStructureType.WorldStructureTypeRepository;
import com.example.demo.Model.WorldTile.WorldTile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import com.example.demo.Model.BuildingType.BuildingTypeRepository;
import com.example.demo.Model.City.CityRepository;
import com.example.demo.Model.CityTile.CityTileRepository;
import com.example.demo.Model.Player.PlayerRepository;
import com.example.demo.Model.World.WorldRepository;
import com.example.demo.Model.WorldTile.WorldTileRepository;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

@Component
public class DbTest implements CommandLineRunner{

    private WorldRepository worldRepository;
    private WorldTileRepository worldTileRepository;
    private PlayerRepository playerRepository;
    private CityRepository cityRepository;
    private CityTileRepository cityTileRepository;
    private BuildingTypeRepository buildingTypeRepository;
    private TerrainTypeRepository terrainRepository;
    private WorldStructureTypeRepository structureTypeRepository;
    private BuildingRepository buildingRepository;
    private WorldStructureRepository structureRepository;

    @Autowired
    public DbTest(
            WorldRepository worldRepository,
            WorldTileRepository worldTileRepository,
            PlayerRepository playerRepository,
            CityRepository cityRepository,
            CityTileRepository cityTileRepository,
            BuildingTypeRepository buildingTypeRepository,
            TerrainTypeRepository terrainRepository,
            WorldStructureTypeRepository structureTypeRepository,
            BuildingRepository buildingRepository,
            WorldStructureRepository structureRepository
    ) {
       this.buildingTypeRepository = buildingTypeRepository;
       this.cityRepository = cityRepository;
       this.cityTileRepository = cityTileRepository;
       this.playerRepository = playerRepository;
       this.worldRepository = worldRepository;
       this.worldTileRepository = worldTileRepository;
       this.terrainRepository = terrainRepository;
       this.structureTypeRepository = structureTypeRepository;
       this.buildingRepository = buildingRepository;
       this.structureRepository = structureRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        init();
    }

    private void init() {
        initTerrain();
        initWorld();
        initPlayer();
        initBuildingTypes();
        initStructureTypes();
        initWorldTiles();
        initCity();
        initCityTiles();
    }


    private void initTerrain() {
        TerrainType terrain = new TerrainType();
        terrain.setName("grass");
        terrainRepository.save(terrain);
    }

    private void initWorld() {
        World world = new World();
        world.setRows(14);
        world.setColumns(24);
        worldRepository.save(world);
    }

    private void initPlayer() {
        Optional<World> opWorld = worldRepository.findById(1);
        if(!opWorld.isPresent())
            return;

        Player player = new Player();
        player.setEmail("player@example.com");
        player.setPassword("example");
        player.setUsername("example");
        player.setWorld(opWorld.get());

        playerRepository.save(player);
    }

    private void initBuildingTypes() {
        String[] names = new String[] {"castle", "house"};
        String[] display = new String[] {"Castle", "House"};
        for (int i = 0; i < names.length; i++) {
            BuildingType buildingType = new BuildingType();
            buildingType.setName(names[i]);
            buildingType.setDisplayName(display[i]);
            buildingTypeRepository.save(buildingType);
        }
    }

    private void initStructureTypes() {
        String[] names = new String[] {"city"};
        String[] display = new String[] {"City"};
        for (int i = 0; i < names.length; i++) {
            WorldStructureType structureType = new WorldStructureType();
            structureType.setName(names[i]);
            structureType.setDisplayName(display[i]);
            structureTypeRepository.save(structureType);
        }
    }

    private void initWorldTiles() {
        Optional<World> opWorld = worldRepository.findById(1);
        Optional<TerrainType> opTerrain = terrainRepository.findByName("grass");
        if((!opWorld.isPresent()) || (!opTerrain.isPresent()))
            return;

        Collection<WorldTile> tiles = new ArrayList<>();
        World world = opWorld.get();
        TerrainType terrain = opTerrain.get();
        for(int i = 0; i < world.getRows(); i++) {
            for(int j = 0; j < world.getColumns(); j++) {
                WorldTile tile = new WorldTile();
                tile.setWorld(world);
                tile.setTerrain(terrain);
                tile.setRowNumber(i);
                tile.setColumnNumber(j);
                tiles.add(tile);
            }
        }
        worldTileRepository.saveAll(tiles);
    }

    private void initCity() {
        Optional<WorldTile> opTile = worldTileRepository.findById(25);
        Optional<Player> opPlayer = playerRepository.findById(1);
        Optional<WorldStructureType> opType = structureTypeRepository.findByName("city");
        if((!opTile.isPresent()) || (!opPlayer.isPresent()) || (!opType.isPresent()))
            return;

        WorldTile tile = opTile.get();
        Player player = opPlayer.get();
        WorldStructureType type = opType.get();

        City city = new City();
        city.setName("ExampleCity");
        city.setTile(tile);
        city.setPlayer(player);
        city.setType(type);
        city.setColumns(16);
        city.setRows(9);

        cityRepository.save(city);

        tile.setCity(city);
        worldTileRepository.save(tile);
    }

    private void initCityTiles() {
        Optional<City> opCity = cityRepository.findById(1);
        Optional<TerrainType> opTerrain = terrainRepository.findByName("grass");
        if((!opCity.isPresent()) || (!opTerrain.isPresent()))
            return;

        TerrainType terrain = opTerrain.get();
        City city = opCity.get();
        Collection<CityTile> tiles = new ArrayList<>();
        for(int i = 0; i < city.getRows(); i++) {
            for(int j = 0 ; j < city.getColumns(); j++) {
                CityTile tile = new CityTile();
                tile.setCity(city);
                tile.setTerrain(terrain);
                tile.setRowNumber(i);
                tile.setColumnNumber(j);
                tiles.add(tile);
            }
        }

        cityTileRepository.saveAll(tiles);
    }
}
