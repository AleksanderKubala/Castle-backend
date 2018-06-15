package com.example.demo.REST.Services;

import com.example.demo.Model.Building.Building;
import com.example.demo.Model.BuildingType.BuildingType;
import com.example.demo.Model.BuildingType.BuildingTypes;
import com.example.demo.Model.City.City;
import com.example.demo.Model.CityTile.CityTile;
import com.example.demo.Model.Production.Production;
import com.example.demo.Model.Requirement.Requirement;
import com.example.demo.Model.Storage.Storage;
import com.example.demo.REST.ModelREST.ModelServices.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BuildingManagementService extends CityManagementService {

    private BuildingTypeService buildingTypeService;
    private BuildingService buildingService;

    @Autowired
    public BuildingManagementService(
            CityService cityService,
            BuildingService buildingService,
            BuildingTypeService buildingTypeService,
            StorageService storageService,
            RequirementService requirementService,
            ProductionService productionService,
            GarrisonService garrisonService
    ) {
        super(
                cityService,
                storageService,
                requirementService,
                productionService,
                garrisonService
        );
        this.buildingService = buildingService;
        this.buildingTypeService = buildingTypeService;
    }

    public CityTile constructBuilding(Integer cityId, Integer row, Integer column, String buildingName) {
        CityTile tile = findTile(cityId, row, column);
        if(tile == null)
            return null;

        Optional<BuildingType> type = buildingTypeService.retrieveBuildingTypeByName(buildingName);
        if(!type.isPresent()) {
            return null;
        }

        Integer buildingCount = buildingService.countBy(tile.getCity(), type.get());
        if(buildingCount + 1 > type.get().getInstancesLimit())
            return null;

        depleteResources(tile.getCity(), type.get(), 1, false);

        Building building = new Building(type.get(), tile);
        tile.setBuilding(building);
        buildingService.store(building);
        cityService.updateTile(tile);

        determineBuildingType(tile.getCity(), type.get(), building);
        recalculateCityProduction(tile.getCity(), type.get(), 1, false);

        return tile;
    }

    public CityTile destroyBuilding(Integer cityId, Integer row, Integer column) {
        CityTile tile = findTile(cityId, row, column);
        if(tile == null)
            return null;

        Building building = tile.getBuilding();
        if(!building.getType().isDestructible())
            return null;
        tile.setBuilding(null);
        buildingService.remove(building);
        cityService.updateTile(tile);

        determineBuildingType(tile.getCity(), building.getType(), null);
        depleteResources(tile.getCity(), building.getType(), 1, true);
        recalculateCityProduction(tile.getCity(), building.getType(), 1, true);

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

    private void determineBuildingType(City city, BuildingType type, Building building) {
        boolean has = true;
        if(building == null)
            has = false;

        if(type.getName().equals(BuildingTypes.CASTLE.name)) {
            city.setHasMainBuilding(has);
        } else if(type.getName().equals(BuildingTypes.ARCHERY.name)) {
            city.setHasArchery(has);
        } else if (type.getName().equals(BuildingTypes.BARRACKS.name)) {
            city.setHasBarracks(has);
        } else if (type.getName().equals(BuildingTypes.STABLES.name)) {
            city.setHasStables(has);
        }
    }
}
