package com.example.demo.REST.ModelREST.ModelServices;

import com.example.demo.Model.City.City;
import com.example.demo.Model.City.CityRepository;
import com.example.demo.Model.CityTile.CityTile;
import com.example.demo.Model.CityTile.CityTileRepository;
import com.example.demo.Model.Player.Player;
import com.example.demo.Model.TerrainType.TerrainType;
import com.example.demo.Model.TerrainType.TerrainTypes;
import com.example.demo.Model.WorldStructureType.WorldStructureType;
import com.example.demo.Model.WorldStructureType.WorldStructureTypes;
import com.example.demo.Model.WorldTile.WorldTile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CityService {

    private CityRepository cityRepository;
    private CityTileRepository cityTileRepository;
    private WorldStructureTypeService worldStructureTypeService;
    private TerrainTypeService terrainService;
    private String defaultNewCityName;

    @Autowired
    public CityService(
            CityRepository cityRepository,
            CityTileRepository cityTileRepository,
            WorldStructureTypeService worldStructureTypeService,
            TerrainTypeService terrainService
    ) {
        this.cityRepository = cityRepository;
        this.cityTileRepository = cityTileRepository;
        this.worldStructureTypeService = worldStructureTypeService;
        this.terrainService = terrainService;
        this.defaultNewCityName = "NewCity";
    }

    public Optional<City> retrieveCityById(Integer id) {
        return cityRepository.findById(id);
    }

    public List<CityTile> retrieveCityTiles(City city) {
        return cityTileRepository.findAllByCity(city);
    }

    public List<City> retrieveCitiesByPlayer(Player player) {
        return cityRepository.findAllByPlayer(player);
    }

    public Optional<CityTile> retrieveTile(City city, Integer row, Integer column) {
        return cityTileRepository.findByCityAndRowNumberAndColumnNumber(city, row, column);
    }

    public List<City> retrieveAllCities() {
        return cityRepository.findAll();
    }

    public void updateTile(CityTile tile) {
        cityTileRepository.save(tile);
    }

    public City buildNewCity(Player player, WorldTile tile, String name, boolean capital) {
        Optional<WorldStructureType> type = worldStructureTypeService.retrieveStructureTypeByName(WorldStructureTypes.CITY.name);
        if (!type.isPresent())
            return null;

        City city = new City();
        city.setName(name);
        city.setTile(tile);
        tile.setCity(city);
        city.setPlayer(player);
        city.setType(type.get());
        city.setColumns(16);
        city.setRows(9);
        city.setCapital(capital);
        city.setHasArchery(false);
        city.setHasBarracks(false);
        city.setHasMainBuilding(false);
        city.setHasStables(false);
        cityRepository.save(city);

        return city;
    }

    public City buildNewCity(Player player, WorldTile tile, boolean capital) {
        Integer cityCount = cityRepository.countAllByPlayer(player);
        String name = this.defaultNewCityName + (cityCount + 1);
        return buildNewCity(player, tile, name, capital);
    }

    public List<CityTile> createCityTiles(City city) {
        Optional<TerrainType> opTerrain = terrainService.retrieveTerrainTypeByName(TerrainTypes.GRASS.name);
        if ((!opTerrain.isPresent()))
            return null;

        TerrainType terrain = opTerrain.get();
        List<CityTile> tiles = new ArrayList<>();
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
        cityTileRepository.saveAll(tiles);

        return tiles;
    }
}
