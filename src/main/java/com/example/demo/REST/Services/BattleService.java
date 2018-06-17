package com.example.demo.REST.Services;

import com.example.demo.Model.City.City;
import com.example.demo.Model.Garrison.Garrison;
import com.example.demo.Model.Unit.Unit;
import com.example.demo.REST.ModelREST.ModelServices.*;
import com.example.demo.REST.Requests.GarrisonRequest;
import com.example.demo.REST.Responses.BattleResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class BattleService extends CityManagementService {

    private UnitService unitService;

    @Autowired
    protected BattleService(
            CityService cityService,
            StorageService storageService,
            RequirementService requirementService,
            ProductionService productionService,
            GarrisonService garrisonService,
            UnitService unitService
    ) {
        super(cityService, storageService, requirementService, productionService, garrisonService);
        this.unitService = unitService;
    }

    public BattleResponse resolveBattle(Integer attackerCity, Integer targetCity, List<GarrisonRequest> troops) {
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
        applyLosses(attacker.get(), attackLosses);
        applyLosses(target.get(), targetLosses);

        for(Garrison troop: attackLosses) {
            depleteResources(attacker.get(), troop.getUnit(), Math.abs(troop.getQuantity()), true);
            recalculateCityProduction(attacker.get(), troop.getUnit(), Math.abs(troop.getQuantity()), true);
        }
        for(Garrison troop: targetLosses) {
            depleteResources(target.get(), troop.getUnit(), Math.abs(troop.getQuantity()), true);
            recalculateCityProduction(target.get(), troop.getUnit(), Math.abs(troop.getQuantity()), true);
        }

        return new BattleResponse(attacker.get(), target.get(), attackLosses, targetLosses, attackerWon);
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
        double luck = 0.75 + random.nextDouble()*0.5;
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

    private void applyLosses(City city, List<Garrison> losses) {
        List<Garrison> cityGarrison = garrisonService.retrieveCityGarrison(city);
        for(Garrison loss: losses) {
            Garrison cityTroop = cityGarrison.get(cityGarrison.indexOf(loss));
            cityTroop.setQuantity(cityTroop.getQuantity() + loss.getQuantity());
            cityTroop.setTotalHealth(cityTroop.getQuantity()*cityTroop.getUnit().getHealth());
        }
        garrisonService.updateGarrison(cityGarrison);
    }
}
