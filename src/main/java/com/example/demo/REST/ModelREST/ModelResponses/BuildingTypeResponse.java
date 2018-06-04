package com.example.demo.REST.ModelREST.ModelResponses;

import com.example.demo.Model.BuildingType.BuildingType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class BuildingTypeResponse {

    private String name;
    private String displayName;

    public BuildingTypeResponse(BuildingType buildingType) {
        this.name = buildingType.getName();
        this.displayName = buildingType.getDisplayName();
    }
}
