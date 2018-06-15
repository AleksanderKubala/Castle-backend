package com.example.demo.REST.Services;

import com.example.demo.Model.Abstract.Quantitative;
import com.example.demo.Model.Abstract.Requisition;
import com.example.demo.Model.Building.Building;
import com.example.demo.Model.BuildingType.BuildingType;
import com.example.demo.Model.City.City;
import com.example.demo.Model.Garrison.Garrison;
import com.example.demo.Model.Production.Production;
import com.example.demo.Model.Requirement.Requirement;
import com.example.demo.Model.Storage.Storage;
import com.example.demo.Model.Unit.Unit;
import com.example.demo.REST.ModelREST.ModelServices.*;

import java.util.ArrayList;
import java.util.List;

public abstract class CityManagementService {

    protected CityService cityService;
    protected StorageService storageService;
    protected RequirementService requirementService;
    protected ProductionService productionService;
    protected GarrisonService garrisonService;

    protected CityManagementService(
            CityService cityService,
            StorageService storageService,
            RequirementService requirementService,
            ProductionService productionService,
            GarrisonService garrisonService
    ) {
        this.cityService = cityService;
        this.storageService = storageService;
        this.requirementService = requirementService;
        this.productionService = productionService;
        this.garrisonService = garrisonService;
    }

    public List<Production> retrieveCityProduction(City city) {
        return this.productionService.retrieveCityProduction(city);
    }

    public List<Storage> retrieveCityStorage(City city) {
        return this.storageService.retrieveCityStorage(city);
    }

    public List<Garrison> retrieveCityGarrison(City city) {
        return this.garrisonService.retrieveCityGarrison(city);
    }

    protected void recalculateRequisition(
            List<? extends Requisition> source,
            List<? extends Requisition> leech,
            Integer quantity,
            Boolean recover
    ) {
        for (Requisition leechReq : leech) {
            for (Requisition sourceReq : source) {
                if (sourceReq.getResource().equals(leechReq.getResource())) {
                    if (recover) {
                        sourceReq.setQuantity(sourceReq.getQuantity() - (leechReq.getQuantity() * quantity));
                    } else {
                        sourceReq.setQuantity(sourceReq.getQuantity() + (leechReq.getQuantity() * quantity));
                    }
                }
            }
        }
    }

    protected void depleteRequisition (
            List<? extends Requisition> source,
            List<? extends Requisition> leech,
            Integer quantity,
            Boolean recover
    ) {
        for(Requisition leechReq: leech) {
            for(Requisition sourceReq: source) {
                if(leechReq.getResource().equals(sourceReq.getResource())) {
                    if(recover) {
                        sourceReq.setQuantity(sourceReq.getQuantity() + ((int)(leechReq.getQuantity() * quantity * leechReq.getRecoveryCoef())));
                    } else {
                        if(!sourceReq.getResource().getPoolResource())
                            sourceReq.setQuantity(sourceReq.getQuantity() - (leechReq.getQuantity() * quantity));
                        else
                            sourceReq.setQuantity(sourceReq.getQuantity() - ((int)(leechReq.getQuantity() * quantity * leechReq.getRecoveryCoef())));
                    }
                }
            }
        }
    }


    protected void recalculateCityProduction(City city, BuildingType type, Integer quantity, Boolean recover) {
        List<Production> cityProductions = productionService.retrieveCityProduction(city);
        List<Production> buildingProductions = productionService.retrieveBuildingProduction(type);
        recalculateRequisition(cityProductions, buildingProductions, quantity, recover);
        /*

        for(Production buildingProduction: buildingProductions) {
            for(Production cityProduction : cityProductions) {
                if(cityProduction.getResource().equals(buildingProduction.getResource())) {
                    if(building != null) {
                        cityProduction.setQuantity(cityProduction.getQuantity() + buildingProduction.getQuantity());
                    } else {
                        cityProduction.setQuantity(cityProduction.getQuantity() - buildingProduction.getQuantity());
                    }
                }
            }
        }*/

        productionService.updateProduction(cityProductions);
    }

    protected void recalculateCityProduction(City city, Unit unit, Integer quantity, Boolean recover) {
        List<Production> cityProductions = productionService.retrieveCityProduction(city);
        List<Production> unitProductions = productionService.retrieveUnitProduction(unit);
        recalculateRequisition(cityProductions, unitProductions, quantity, recover);
        productionService.updateProduction(cityProductions);
    }

    protected void depleteResources(City city, BuildingType type, Integer quantity, Boolean recover) {
        List<Requirement> reqs = requirementService.retrieveBuildingRequirements(type);
        List<Storage> storages = storageService.retrieveCityStorage(city);
        depleteRequisition(storages, reqs, quantity, recover);
        // List<Storage> neededStorages = new ArrayList<>();

        /*
        for(Requirement req: reqs) {
            for(Storage storage: storages) {
                if(req.getResource().equals(storage.getResource())) {
                    storage.setQuantity(storage.getQuantity() - req.getQuantity());
                    neededStorages.add(storage);
                }
            }
        }
        */
        storageService.updateStorages(storages);
    }

    protected void depleteResources(City city, Unit unit, Integer quantity, Boolean recover) {
        List<Requirement> reqs = requirementService.retrieveUnitRequirements(unit);
        List<Storage> storages = storageService.retrieveCityStorage(city);
        depleteRequisition(storages, reqs, quantity, recover);
        storageService.updateStorages(storages);
    }
}
