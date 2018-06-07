package com.example.demo.REST.Services;

import com.example.demo.Model.Building.Building;
import com.example.demo.Model.BuildingType.BuildingType;
import com.example.demo.Model.City.City;
import com.example.demo.Model.CityTile.CityTile;
import com.example.demo.REST.ModelREST.ModelServices.BuildingService;
import com.example.demo.REST.ModelREST.ModelServices.BuildingTypeService;
import com.example.demo.REST.ModelREST.ModelServices.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CityManagementService {

    private CityService cityService;
    private BuildingTypeService buildingTypeService;
    private BuildingService buildingService;

    @Autowired
    public CityManagementService(
            CityService cityService,
            BuildingService buildingService,
             BuildingTypeService buildingTypeService
    ) {
        this.buildingService = buildingService;
        this.buildingTypeService = buildingTypeService;
        this.cityService = cityService;
    }

    public CityTile constructBuilding(Integer cityId, Integer row, Integer column, String buildingName) {
        CityTile tile = findTile(cityId, row, column);
        if(tile == null)
            return null;

        Optional<BuildingType> type = buildingTypeService.retrieveBuildingTypeByName(buildingName);
        if(!type.isPresent()) {
            return null;
        }

        Building building = new Building(type.get(), tile);
        tile.setBuilding(building);
        buildingService.store(building);
        cityService.updateTile(tile);

        return tile;
    }

    public CityTile destroyBuilding(Integer cityId, Integer row, Integer column) {
        CityTile tile = findTile(cityId, row, column);
        if(tile == null)
            return null;

        Building building = tile.getBuilding();
        tile.setBuilding(null);
        buildingService.remove(building);
        cityService.updateTile(tile);

        return tile;
    }

    private CityTile findTile(Integer cityId, Integer row, Integer column) {
        Optional<City> city = cityService.retrieveCityById(cityId);
        if(!city.isPresent()) {
            return null;
        }

        Optional<CityTile> tile = cityService.retrieveTile(city.get(), row, column);
        if(!tile.isPresent()) {
            return null;
        }

        return tile.get();
    }
}
