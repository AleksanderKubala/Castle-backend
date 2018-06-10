package com.example.demo.Test;

import com.example.demo.Model.Building.BuildingRepository;
import com.example.demo.Model.BuildingType.BuildingType;
import com.example.demo.Model.City.City;
import com.example.demo.Model.CityTile.CityTile;
import com.example.demo.Model.Player.Player;
import com.example.demo.Model.Resource.Resource;
import com.example.demo.Model.Resource.ResourceRepository;
import com.example.demo.Model.Storage.Storage;
import com.example.demo.Model.Storage.StorageRepository;
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

import java.util.*;

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
    private ResourceRepository resourceRepository;
    private StorageRepository storageRepository;

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
            WorldStructureRepository structureRepository,
            ResourceRepository resourceRepository,
            StorageRepository storageRepository
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
       this.resourceRepository = resourceRepository;
       this.storageRepository = storageRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        init();
    }

    private void init() {
        initResources();
        initTerrain();
        initWorld();
        initPlayer();
        initBuildingTypes();
        initStructureTypes();
        initWorldTiles();
        initCity();
        initCityTiles();
        initCityStorage();
    }


    private void initTerrain() {
        TerrainType terrain = new TerrainType();
        terrain.setName("grass");
        terrainRepository.save(terrain);
    }

    private void initWorld() {
        World world = new World();
        world.setName("World");
        world.setRows(14);
        world.setColumns(24);
        worldRepository.save(world);
    }

    private void initPlayer() {
        Optional<World> opWorld = worldRepository.findById(1);
        if(!opWorld.isPresent())
            return;

        Player player1 = new Player();
        player1.setEmail("player1@example.com");
        player1.setPassword("example");
        player1.setUsername("Player1");
        player1.setWorld(opWorld.get());

        Player player2 = new Player();
        player2.setEmail("player2@example.com");
        player2.setPassword("example");
        player2.setUsername("Player2");
        player2.setWorld(opWorld.get());

        playerRepository.save(player1);
        playerRepository.save(player2);
    }

    private void initResources() {
        String[] names = new String[] {"wood", "stone", "gold", "food", "capacity", "happiness"};
        Boolean[] plunderableValues = new Boolean[] {true, true, true, true, false, false};
        List<Resource> resources = new ArrayList<>();

        for(int i = 0; i < names.length; i++) {
            Resource resource = new Resource();
            resource.setName(names[i]);
            resource.setPlunderable(plunderableValues[i]);
            resources.add(resource);
        }

        resourceRepository.saveAll(resources);
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
        Optional<WorldTile> opTile1 = worldTileRepository.findById(26);
        Optional<WorldTile> opTile2 = worldTileRepository.findById(30);
        Optional<WorldTile> opTile3 = worldTileRepository.findById(143);
        List<Player> players = playerRepository.findAll();
        Optional<WorldStructureType> opType = structureTypeRepository.findByName("city");
        if((!opTile1.isPresent()) || (!opTile2.isPresent()) || (!opTile3.isPresent()) || (!opType.isPresent()))
            return;

        List<WorldTile> tiles = new ArrayList<>(Arrays.asList(opTile1.get(), opTile2.get(), opTile3.get()));
        WorldStructureType type = opType.get();
        List<City> cities = new ArrayList<>();

        for(int i = 0, l = 0; i < players.size(); i++) {
            Boolean capital = true;
            for(int j = 2 - i, k = 1; j > 0; j--, k++, l++) {
                WorldTile tile = tiles.get(l);
                City city = new City();
                city.setName("ExampleCity" + k + players.get(i).getUsername());
                city.setTile(tile);
                tile.setCity(city);
                city.setPlayer(players.get(i));
                city.setType(type);
                city.setColumns(16);
                city.setRows(9);
                city.setCapital(capital);
                capital = false;
                cities.add(city);
            }
        }

        cityRepository.saveAll(cities);
        worldTileRepository.saveAll(tiles);
    }

    private void initCityStorage() {
        List<City> cities = cityRepository.findAll();
        List<Resource> resources = resourceRepository.findAll();
        List<Storage> storages = new ArrayList<>();

        for(City city: cities) {
            for(Resource resource: resources) {
                Storage storage = new Storage();
                storage.setCity(city);
                storage.setResource(resource);
                storage.setQuantity(100);
                storages.add(storage);
            }
        }

        storageRepository.saveAll(storages);
    }

    private void initCityTiles() {
        List<City> cities = cityRepository.findAll();
        Optional<TerrainType> opTerrain = terrainRepository.findByName("grass");
        if((!opTerrain.isPresent()))
            return;

        TerrainType terrain = opTerrain.get();
        Collection<CityTile> tiles = new ArrayList<>();
        for(City city: cities) {
            for (int i = 0; i < city.getRows(); i++) {
                for (int j = 0; j < city.getColumns(); j++) {
                    CityTile tile = new CityTile();
                    tile.setCity(city);
                    tile.setTerrain(terrain);
                    tile.setRowNumber(i);
                    tile.setColumnNumber(j);
                    tiles.add(tile);
                }
            }
        }

        cityTileRepository.saveAll(tiles);
    }
}
