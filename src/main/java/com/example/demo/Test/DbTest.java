package com.example.demo.Test;

import com.example.demo.Model.Building.BuildingRepository;
import com.example.demo.Model.BuildingType.BuildingType;
import com.example.demo.Model.BuildingType.BuildingTypes;
import com.example.demo.Model.City.City;
import com.example.demo.Model.CityTile.CityTile;
import com.example.demo.Model.Garrison.Garrison;
import com.example.demo.Model.Garrison.GarrisonRepository;
import com.example.demo.Model.Player.Player;
import com.example.demo.Model.Production.Production;
import com.example.demo.Model.Production.ProductionRepository;
import com.example.demo.Model.Requirement.Requirement;
import com.example.demo.Model.Requirement.RequirementRepository;
import com.example.demo.Model.Resource.Resource;
import com.example.demo.Model.Resource.ResourceRepository;
import com.example.demo.Model.Resource.ResourceTypes;
import com.example.demo.Model.Storage.Storage;
import com.example.demo.Model.Storage.StorageRepository;
import com.example.demo.Model.TerrainType.TerrainType;
import com.example.demo.Model.TerrainType.TerrainTypeRepository;
import com.example.demo.Model.TerrainType.TerrainTypes;
import com.example.demo.Model.Unit.Unit;
import com.example.demo.Model.Unit.UnitRepository;
import com.example.demo.Model.Unit.UnitTypes;
import com.example.demo.Model.World.World;
import com.example.demo.Model.WorldStructure.WorldStructureRepository;
import com.example.demo.Model.WorldStructureType.WorldStructureType;
import com.example.demo.Model.WorldStructureType.WorldStructureTypeRepository;
import com.example.demo.Model.WorldStructureType.WorldStructureTypes;
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
    private ProductionRepository productionRepository;
    private RequirementRepository requirementRepository;
    private UnitRepository unitRepository;
    private GarrisonRepository garrisonRepository;

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
            StorageRepository storageRepository,
            ProductionRepository productionRepository,
            RequirementRepository requirementRepository,
            UnitRepository unitRepository,
            GarrisonRepository garrisonRepository
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
       this.productionRepository = productionRepository;
       this.resourceRepository = resourceRepository;
       this.requirementRepository = requirementRepository;
       this.unitRepository = unitRepository;
       this.garrisonRepository = garrisonRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        init();
    }

    private void init() {
        initUnits();
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
        initCityProduction();
        initBuildingProductions();
        initBuildingRequirements();
        initGarrison();
        initUnitRequirements();
        initUnitProduction();
    }


    private void initUnits() {
        String[] names = new String[] {
                UnitTypes.ARCHER.name,
                UnitTypes.PIKEMAN.name,
                UnitTypes.CAVALRY.name,
                UnitTypes.SETTLER.name
        };
        String[] displayNames = new String[] {
                UnitTypes.ARCHER.displayName,
                UnitTypes.PIKEMAN.displayName,
                UnitTypes.CAVALRY.displayName,
                UnitTypes.SETTLER.displayName
        };

        List<Unit> units = new ArrayList<>();
        for(int i = 0 ; i < names.length; i++) {
            Unit unit = new Unit();
            unit.setName(names[i]);
            unit.setDisplayName(displayNames[i]);
            units.add(unit);
            if(unit.getName().equals(UnitTypes.ARCHER.name)) {
                unit.setStrength(13);
                unit.setHealth(25);
                unit.setSpeed(8);
                unit.setCapacity(10);
            }
            if(unit.getName().equals(UnitTypes.PIKEMAN.name)) {
                unit.setStrength(9);
                unit.setHealth(35);
                unit.setSpeed(5);
                unit.setCapacity(40);
            }
            if(unit.getName().equals(UnitTypes.CAVALRY.name)) {
                unit.setStrength(20);
                unit.setHealth(55);
                unit.setSpeed(15);
                unit.setCapacity(20);
            }
            if(unit.getName().equals(UnitTypes.SETTLER.name)) {
                unit.setStrength(1);
                unit.setHealth(1);
                unit.setSpeed(1);
                unit.setCapacity(0);
            }
        }
        unitRepository.saveAll(units);
    }

    private void initTerrain() {
        TerrainType terrain = new TerrainType();
        terrain.setName(TerrainTypes.GRASS.name);
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
        String[] names = new String[] {
                ResourceTypes.WOOD.name,
                ResourceTypes.STONE.name,
                ResourceTypes.GOLD.name,
                ResourceTypes.FOOD.name,
                ResourceTypes.CAPACITY.name,
                ResourceTypes.HAPPINESS.name
        };

        Integer[] startingVolumes = new Integer[] {
                ResourceTypes.WOOD.startingVolume,
                ResourceTypes.STONE.startingVolume,
                ResourceTypes.GOLD.startingVolume,
                ResourceTypes.FOOD.startingVolume,
                ResourceTypes.CAPACITY.startingVolume,
                ResourceTypes.HAPPINESS.startingVolume
        };

        Boolean[] poolResouces = new Boolean[] {
                ResourceTypes.WOOD.poolResource,
                ResourceTypes.STONE.poolResource,
                ResourceTypes.GOLD.poolResource,
                ResourceTypes.FOOD.poolResource,
                ResourceTypes.CAPACITY.poolResource,
                ResourceTypes.HAPPINESS.poolResource
        };
        Boolean[] plunderableValues = new Boolean[] {true, true, true, true, false, false};
        List<Resource> resources = new ArrayList<>();

        for(int i = 0; i < names.length; i++) {
            Resource resource = new Resource();
            resource.setName(names[i]);
            resource.setPlunderable(plunderableValues[i]);
            resource.setStartingVolume(startingVolumes[i]);
            resource.setPoolResource(poolResouces[i]);
            resources.add(resource);
        }

        resourceRepository.saveAll(resources);
    }

    private void initBuildingTypes() {
        String[] names = new String[] {
                BuildingTypes.CASTLE.name,
                BuildingTypes.HOUSE.name,
                BuildingTypes.ARCHERY.name,
                BuildingTypes.BARRACKS.name,
                BuildingTypes.STABLES.name
        };
        String[] display = new String[] {
                BuildingTypes.CASTLE.displayName,
                BuildingTypes.HOUSE.displayName,
                BuildingTypes.ARCHERY.displayName,
                BuildingTypes.BARRACKS.displayName,
                BuildingTypes.STABLES.displayName};
        for (int i = 0; i < names.length; i++) {
            BuildingType buildingType = new BuildingType();
            buildingType.setName(names[i]);
            buildingType.setDisplayName(display[i]);
            if(names[i].equals(BuildingTypes.CASTLE.name)) {
                buildingType.setDestructible(false);
                buildingType.setMainBuilding(true);
            } else {
                buildingType.setDestructible(true);
                buildingType.setMainBuilding(false);
            }
            if(!names[i].equals(BuildingTypes.HOUSE.name)) {
                buildingType.setInstancesLimit(1);
            } else {
                buildingType.setInstancesLimit(8);
            }
            buildingTypeRepository.save(buildingType);
        }
    }

    private void initBuildingProductions() {
        List<BuildingType> types = buildingTypeRepository.findAll();
        List<Production> productions = new ArrayList<>();
        for(BuildingType type: types) {
            Production production = new Production();
            production.setBuildingType(type);
            Optional<Resource> gold = resourceRepository.findByName(ResourceTypes.GOLD.name);
            if(gold.isPresent()) {
                production.setResource(gold.get());
                if (!type.isDestructible()) {
                    production.setQuantity(6);

                } else {
                    production.setQuantity(-1);
                }
            }
            productions.add(production);
        }
        productionRepository.saveAll(productions);
    }

    private void initBuildingRequirements() {
        List<BuildingType> types = buildingTypeRepository.findAll();
        List<Requirement> requirements = new ArrayList<>();
        Optional<Resource> capacity = resourceRepository.findByName(ResourceTypes.CAPACITY.name);
        for(BuildingType type: types) {
            if(type.getName().equals(BuildingTypes.CASTLE.name)) {
                Requirement req = new Requirement();
                req.setBuildingType(type);
                req.setResource(capacity.get());
                req.setQuantity(-20);
                req.setRecoveryCoef(1.0);
                requirements.add(req);
            } else {
                Requirement req = new Requirement();
                req.setBuildingType(type);
                Optional<Resource> wood = resourceRepository.findByName(ResourceTypes.WOOD.name);
                if(wood.isPresent()) {
                    req.setResource(wood.get());
                    req.setQuantity(20);
                    req.setRecoveryCoef(0.5);
                }
                requirements.add(req);
            }
            if(type.getName().equals(BuildingTypes.HOUSE.name)) {
                Requirement req = new Requirement();
                req.setBuildingType(type);
                req.setResource(capacity.get());
                req.setQuantity(-5);
                req.setRecoveryCoef(1.0);
                requirements.add(req);
            }
        }
        requirementRepository.saveAll(requirements);
    }

    private void initStructureTypes() {
        String[] names = new String[] {
                WorldStructureTypes.CITY.name,
                WorldStructureTypes.TREE1.name,
                WorldStructureTypes.TREE2.name,
                WorldStructureTypes.TREE3.name
        };
        String[] display = new String[] {
                WorldStructureTypes.CITY.displayName,
                WorldStructureTypes.TREE1.displayName,
                WorldStructureTypes.TREE2.displayName,
                WorldStructureTypes.TREE3.displayName
        };
        for (int i = 0; i < names.length; i++) {
            WorldStructureType structureType = new WorldStructureType();
            structureType.setName(names[i]);
            structureType.setDisplayName(display[i]);
            structureTypeRepository.save(structureType);
        }
    }

    private void initWorldTiles() {
        Optional<World> opWorld = worldRepository.findById(1);
        Optional<TerrainType> opTerrain = terrainRepository.findByName(TerrainTypes.GRASS.name);
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
        Optional<WorldStructureType> opType = structureTypeRepository.findByName(WorldStructureTypes.CITY.name);
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
                city.setHasArchery(false);
                city.setHasBarracks(false);
                city.setHasMainBuilding(false);
                city.setHasStables(false);
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
                storage.setQuantity(resource.getStartingVolume());
                storages.add(storage);
            }
        }

        storageRepository.saveAll(storages);
    }

    private void initCityProduction() {
        List<City> cities = cityRepository.findAll();
        List<Resource> resources = resourceRepository.findAll();
        List<Production> productions = new ArrayList<>();

        for(City city: cities) {
            for(Resource resource: resources) {
                Production production = new Production();
                production.setCity(city);
                production.setResource(resource);
                production.setQuantity(0);
                productions.add(production);
            }
        }

        productionRepository.saveAll(productions);
    }

    private void initCityTiles() {
        List<City> cities = cityRepository.findAll();
        Optional<TerrainType> opTerrain = terrainRepository.findByName(TerrainTypes.GRASS.name);
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

    private void initGarrison() {
        List<City> cities = cityRepository.findAll();

        List<Garrison> garrison = new ArrayList<>();
        List<Unit> units = unitRepository.findAll();
        for(City city: cities) {
            for (Unit unit : units) {
                Garrison troop = new Garrison();
                troop.setCity(city);
                troop.setUnit(unit);
                troop.setQuantity(0);
                troop.setTotalHealth(troop.getQuantity()*unit.getHealth());
                garrison.add(troop);
            }
        }

        garrisonRepository.saveAll(garrison);
    }

    private void initUnitRequirements() {
        List<Unit> units = unitRepository.findAll();
        List<Requirement> reqs = new ArrayList<>();
        Optional<Resource> gold = resourceRepository.findByName(ResourceTypes.GOLD.name);
        Optional<Resource> capacity = resourceRepository.findByName(ResourceTypes.CAPACITY.name);
        Optional<Resource> wood = resourceRepository.findByName(ResourceTypes.WOOD.name);
        Optional<Resource> food = resourceRepository.findByName(ResourceTypes.FOOD.name);
        Requirement req;

        for(Unit unit: units) {
            if(unit.getName().equals(UnitTypes.ARCHER.name)) {
                req = new Requirement();
                req.setUnit(unit);
                req.setResource(gold.get());
                req.setQuantity(10);
                req.setRecoveryCoef(0.0);
                reqs.add(req);
                req = new Requirement();
                req.setUnit(unit);
                req.setResource(capacity.get());
                req.setQuantity(1);
                req.setRecoveryCoef(1.0);
                reqs.add(req);
                req = new Requirement();
                req.setUnit(unit);
                req.setResource(wood.get());
                req.setQuantity(5);
                req.setRecoveryCoef(0.0);
                reqs.add(req);
            }
            if(unit.getName().equals(UnitTypes.PIKEMAN.name)) {
                req = new Requirement();
                req.setUnit(unit);
                req.setResource(gold.get());
                req.setQuantity(5);
                req.setRecoveryCoef(0.0);
                reqs.add(req);
                req = new Requirement();
                req.setUnit(unit);
                req.setResource(capacity.get());
                req.setRecoveryCoef(1.0);
                req.setQuantity(1);
                reqs.add(req);
            }
            if(unit.getName().equals(UnitTypes.CAVALRY.name)) {
                req = new Requirement();
                req.setUnit(unit);
                req.setResource(gold.get());
                req.setQuantity(20);
                req.setRecoveryCoef(0.0);
                reqs.add(req);
                req = new Requirement();
                req.setUnit(unit);
                req.setResource(capacity.get());
                req.setRecoveryCoef(1.0);
                req.setQuantity(3);
                reqs.add(req);
            }
            if(unit.getName().equals(UnitTypes.SETTLER.name)) {
                req = new Requirement();
                req.setUnit(unit);
                req.setResource(gold.get());
                req.setQuantity(80);
                req.setRecoveryCoef(0.0);
                reqs.add(req);
                req = new Requirement();
                req.setUnit(unit);
                req.setResource(capacity.get());
                req.setRecoveryCoef(1.0);
                req.setQuantity(15);
                reqs.add(req);
                req = new Requirement();
                req.setUnit(unit);
                req.setResource(food.get());
                req.setRecoveryCoef(0.0);
                req.setQuantity(100);
                reqs.add(req);
            }
        }

        requirementRepository.saveAll(reqs);
    }

    private void initUnitProduction() {
        List<Unit> units = unitRepository.findAll();
        List<Production> prods = new ArrayList<>();
        Optional<Resource> food = resourceRepository.findByName(ResourceTypes.FOOD.name);
        Optional<Resource> gold = resourceRepository.findByName(ResourceTypes.GOLD.name);
        Production prod;

        for(Unit unit: units) {
            if(unit.getName().equals(UnitTypes.ARCHER.name)) {
                prod = new Production();
                prod.setUnit(unit);
                prod.setResource(food.get());
                prod.setQuantity(-4);
                prods.add(prod);
                prod = new Production();
                prod.setUnit(unit);
                prod.setResource(gold.get());
                prod.setQuantity(-2);
                prods.add(prod);
            }
            if(unit.getName().equals(UnitTypes.PIKEMAN.name)) {
                prod = new Production();
                prod.setUnit(unit);
                prod.setResource(food.get());
                prod.setQuantity(-4);
                prods.add(prod);
                prod = new Production();
                prod.setUnit(unit);
                prod.setResource(gold.get());
                prod.setQuantity(-1);
                prods.add(prod);
            }
            if(unit.getName().equals(UnitTypes.CAVALRY.name)) {
                prod = new Production();
                prod.setUnit(unit);
                prod.setResource(food.get());
                prod.setQuantity(-12);
                prods.add(prod);
                prod = new Production();
                prod.setUnit(unit);
                prod.setResource(gold.get());
                prod.setQuantity(-4);
                prods.add(prod);
            }
            if(unit.getName().equals(UnitTypes.SETTLER.name)) {
                prod = new Production();
                prod.setUnit(unit);
                prod.setResource(food.get());
                prod.setQuantity(-50);
                prods.add(prod);
                prod = new Production();
                prod.setUnit(unit);
                prod.setResource(gold.get());
                prod.setQuantity(-20);
                prods.add(prod);
            }
        }

        productionRepository.saveAll(prods);
    }
}
