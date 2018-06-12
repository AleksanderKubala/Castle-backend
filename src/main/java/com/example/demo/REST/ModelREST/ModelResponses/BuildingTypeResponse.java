package com.example.demo.REST.ModelREST.ModelResponses;

import com.example.demo.Model.BuildingType.BuildingType;
import com.example.demo.Model.Production.Production;
import com.example.demo.Model.Requirement.Requirement;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class BuildingTypeResponse {

    private String name;
    private String displayName;
    private Boolean onlyOne;
    private Boolean destructible;
    private Boolean mainBuilding;
    private List<ProductionResponse> productions;
    private List<RequirementResponse> requirements;

    public BuildingTypeResponse(
            BuildingType buildingType,
            List<Production> productions,
            List<Requirement> requirements) {
        this.productions = new ArrayList<>();
        this.requirements = new ArrayList<>();
        this.name = buildingType.getName();
        this.displayName = buildingType.getDisplayName();
        this.onlyOne = buildingType.isOnlyOne();
        this.destructible = buildingType.isDestructible();
        this.mainBuilding = buildingType.isMainBuilding();
        for(Production production: productions) {
            this.productions.add(new ProductionResponse(production));
        }
        for(Requirement requirement: requirements) {
            this.requirements.add(new RequirementResponse(requirement));
        }
    }
}
