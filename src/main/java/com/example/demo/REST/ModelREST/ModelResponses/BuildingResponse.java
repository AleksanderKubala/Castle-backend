package com.example.demo.REST.ModelREST.ModelResponses;

import com.example.demo.Model.Building.Building;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class BuildingResponse {

    private String name;
    private String displayName;

    public BuildingResponse(Building building) {
        this.name = building.getName();
        this.displayName = building.getDisplayName();
    }
}
