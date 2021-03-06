package com.example.demo.REST.Services;

import com.example.demo.Model.City.City;
import com.example.demo.Model.Garrison.Garrison;
import com.example.demo.Model.Resource.Resource;
import com.example.demo.Model.Storage.Storage;
import com.example.demo.Model.Unit.Unit;
import com.example.demo.Properties.BattleProperties;
import com.example.demo.REST.ModelREST.ModelServices.*;
import com.example.demo.REST.Requests.GarrisonRequest;
import com.example.demo.REST.Responses.BattleResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class BattleService extends CityManagementService {

    private UnitService unitService;
    private BattleProperties battleProperties;

    @Autowired
    protected BattleService(
            CityService cityService,
            StorageService storageService,
            RequirementService requirementService,
            ProductionService productionService,
            GarrisonService garrisonService,
            UnitService unitService,
            BattleProperties battleProperties
    ) {
        super(cityService, storageService, requirementService, productionService, garrisonService);
        this.unitService = unitService;
        this.battleProperties = battleProperties;
    }

    public BattleResponse resolveBattle(
            Integer attackerCity,
            Integer targetCity,
            List<GarrisonRequest> troops,
            List<Resource> toPlunder
    ) {
        Optional<City> attacker = cityService.retrieveCityById(attackerCity);
        Optional<City> target = cityService.retrieveCityById(targetCity);
        if((!attacker.isPresent()) || (!target.isPresent()))
            return null;

        Boolean attackerWon = false;
        Boolean end = false;
        Integer attackerIndex = 0;
        List<Garrison> targetTroops = garrisonService.retrieveCityGarrisonWithUnitsMoreThan(target.get(), 0);
        List<Garrison> targetLosses = new ArrayList<>();
        for(Garrison garrison: targetTroops) {
            targetLosses.add(new Garrison(garrison));
        }
        List<Garrison> attackTroops = createAttackGroup(attacker.get(), troops);
        List<Garrison> attackLosses = new ArrayList<>();
        for(Garrison garrison: attackTroops) {
            attackLosses.add(new Garrison(garrison));
        }
        List<Garrison> attackQueue = new ArrayList<>(targetTroops);
        attackQueue.addAll(attackTroops);
        attackQueue.sort(Collections.reverseOrder());
        while(!end) {
            Garrison wounded = attack(attackQueue.get(attackerIndex), attackTroops, targetTroops);
            if(wounded == null) {
                end = true;
                for(Garrison garrison: attackTroops) {
                    if(garrison.getQuantity() > 0)
                        attackerWon = true;
                }
            }
            else {
                if(wounded.getQuantity() <= 0)
                    attackQueue.remove(wounded);
                attackerIndex = (attackerIndex + 1) % attackQueue.size();
            }
        }
        attackLosses = countCasualties(attackTroops, attackLosses);
        targetLosses = countCasualties(targetTroops, targetLosses);
        healTroops(attackTroops);
        healTroops(targetTroops);
        garrisonService.updateGarrison(attackTroops);
        garrisonService.updateGarrison(targetTroops);
        //applyLosses(attacker.get(), attackLosses);
        //applyLosses(target.get(), targetLosses);

        for(Garrison troop: attackLosses) {
            depleteResources(attacker.get(), troop.getUnit(), Math.abs(troop.getQuantity()), true);
            recalculateCityProduction(attacker.get(), troop.getUnit(), Math.abs(troop.getQuantity()), true);
        }
        for(Garrison troop: targetLosses) {
            depleteResources(target.get(), troop.getUnit(), Math.abs(troop.getQuantity()), true);
            recalculateCityProduction(target.get(), troop.getUnit(), Math.abs(troop.getQuantity()), true);
        }

        BattleResponse response = new BattleResponse(attacker.get(), target.get(), attackLosses, targetLosses, attackerWon);

        if(attackerWon){
            List<Storage> plunder = plunder(target.get(), attackTroops, toPlunder);
            List<Storage> attackerStorage = storageService.retrieveCityStorage(attacker.get());
            for(Storage resourcePlunder: plunder) {
                for(Storage resourceStorage: attackerStorage) {
                    if(resourcePlunder.equals(resourceStorage))
                        resourceStorage.setQuantity(resourceStorage.getQuantity() + resourcePlunder.getQuantity());
                }
            }
            storageService.updateStorages(attackerStorage);
            response.setPlunder(plunder);
        }

        return response;
    }

    private List<Garrison> createAttackGroup(City city, List<GarrisonRequest> troops) {
        List<Garrison> attackTroops = new ArrayList<>();
        for(GarrisonRequest troop: troops) {
            Optional<Unit> unit = unitService.retrieveUnitByName(troop.getUnitName());
            if(unit.isPresent()) {
                Optional<Garrison> cityGarrison =  garrisonService.retrieveCityGarrison(city, unit.get());
                if(cityGarrison.isPresent())
                    attackTroops.add(new Garrison(cityGarrison.get(), troop.getQuantity()));
            }
        }
        return attackTroops;
    }

    private Garrison attack(Garrison attacker, List<Garrison> attackTroops, List<Garrison> targetTroops) {
        List<Garrison> attacked = attackTroops.contains(attacker) ? targetTroops : attackTroops;
        List<Integer> troopIndex = new ArrayList<>();
        for(int i = 0; i < attacked.size(); i++) {
            if(attacked.get(i).getQuantity() > 0) {
                troopIndex.add(i);
            }
        }
        if(troopIndex.size() <= 0)
            return null;

        Random random = new Random();
        Garrison receiver = attacked.get(random.nextInt(troopIndex.size()));
        double luck = battleProperties.getMinimumMultiplierValue() + random.nextDouble()*battleProperties.getMultiplierValueRange();
        int damage = ((int)(attacker.getQuantity()*attacker.getUnit().getStrength()*luck));
        int leftHealth = receiver.getTotalHealth() - damage;
        if(leftHealth < 0)
            leftHealth = 0;
        int remaining = (int)Math.ceil((double)leftHealth/(double)receiver.getUnit().getHealth());
        if(remaining < 0)
            remaining = 0;
        receiver.setQuantity(remaining);
        receiver.setTotalHealth(leftHealth);

        return receiver;
    }

    private List<Garrison> countCasualties(List<Garrison> results, List<Garrison> original) {
        for(Garrison garrison: original) {
            Garrison result = results.get(results.indexOf(garrison));
            garrison.setQuantity(result.getQuantity() - garrison.getQuantity());
        }
        return original;
    }


    private void healTroops(List<Garrison> remains) {
        for(Garrison remain: remains) {
            remain.setTotalHealth(remain.getQuantity()*remain.getUnit().getHealth());
        }
    }

    private List<Storage> plunder(City target, List<Garrison> attackTroops, List<Resource> toPlunder) {
        Integer capacitySum = 0;
        List<Storage> plunder = new ArrayList<>();
        for(Garrison troop: attackTroops) {
            capacitySum += (troop.getQuantity()*troop.getUnit().getCapacity());
        }

        Integer singleResourceVolume = capacitySum / toPlunder.size();
        List<Storage> targetStorage = storageService.retrieveCityStorage(target);
        Collections.sort(targetStorage);
        for(Resource resource: toPlunder) {
            for (Storage storage : targetStorage) {
                if(storage.getResource().equals(resource)) {
                    Storage plunderResource = plunderResource(storage, capacitySum, singleResourceVolume);
                    capacitySum -= plunderResource.getQuantity();
                    plunder.add(plunderResource);
                }
            }
        }
        storageService.updateStorages(targetStorage);
        return plunder;
    }

    private Storage plunderResource(Storage storage, Integer totalCapacityLeft, Integer singleResourceVolume) {
        Storage plunder = new Storage();
        plunder.setResource(storage.getResource());
        int singleResourceMaxPlunder = totalCapacityLeft >= singleResourceVolume ? singleResourceVolume : totalCapacityLeft - singleResourceVolume;
        if ( storage.getQuantity() <= 0)
            plunder.setQuantity(0);
        else {
            int volumeDifference = storage.getQuantity() - singleResourceMaxPlunder;
            if(volumeDifference >= 0) {
                storage.setQuantity(storage.getQuantity() - singleResourceMaxPlunder);
                plunder.setQuantity(singleResourceMaxPlunder);
            }
            else {
                storage.setQuantity(0);
                plunder.setQuantity(singleResourceMaxPlunder + volumeDifference);
            }
        }
        return plunder;
    }
}
