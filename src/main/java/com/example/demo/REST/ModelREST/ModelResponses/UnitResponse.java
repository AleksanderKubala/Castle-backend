package com.example.demo.REST.ModelREST.ModelResponses;

import com.example.demo.Model.Production.Production;
import com.example.demo.Model.Requirement.Requirement;
import com.example.demo.Model.Unit.Unit;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UnitResponse {

    private String name;
    private String displayName;
    private Integer strength;
    private Integer speed;
    private Integer health;
    private Integer capacity;
    private List<RequirementResponse> requirements;
    private List<ProductionResponse> productions;

    public UnitResponse(Unit unit, List<Requirement> requirements, List<Production> productions) {
        this.name = unit.getName();
        this.displayName = unit.getDisplayName();
        this.strength = unit.getStrength();
        this.speed = unit.getSpeed();
        this.health = unit.getHealth();
        this.capacity = unit.getCapacity();
        this.requirements = new ArrayList<>();
        this.productions = new ArrayList<>();
        if(requirements != null) {
            for(Requirement req: requirements)
            this.requirements.add(new RequirementResponse(req));
        }
        if(productions != null) {
            for(Production prod: productions) {
                this.productions.add(new ProductionResponse(prod));
            }
        }
    }
}
