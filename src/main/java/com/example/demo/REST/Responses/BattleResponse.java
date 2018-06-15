package com.example.demo.REST.Responses;

import com.example.demo.Model.City.City;
import com.example.demo.Model.Garrison.Garrison;
import com.example.demo.REST.ModelREST.ModelResponses.GarrisonResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;


@Getter
@Setter
@NoArgsConstructor
public class BattleResponse {

    private String attackerCity;
    private String attacker;
    private String targetCity;
    private String target;
    private List<GarrisonResponse> attackerLosses;
    private List<GarrisonResponse> targetLosses;
    private Boolean attackerWon;

    public BattleResponse(City attackerCity, City targetCity, List<Garrison> attackerLosses, List<Garrison> targetLosses, Boolean attackerWon) {
        this.attackerLosses = new ArrayList<>();
        this.targetLosses = new ArrayList<>();
        this.attackerCity = attackerCity.getName();
        this.attacker = attackerCity.getPlayer().getUsername();
        this.targetCity = targetCity.getName();
        this.target = targetCity.getPlayer().getUsername();
        this.attackerWon = attackerWon;
        if(attackerLosses != null) {
            for(Garrison attackerLoss: attackerLosses) {
                this.attackerLosses.add(new GarrisonResponse(attackerLoss));
            }
        }
        if(targetLosses != null) {
            for(Garrison targetLoss: targetLosses) {
                this.targetLosses.add(new GarrisonResponse(targetLoss));
            }
        }
    }

}
