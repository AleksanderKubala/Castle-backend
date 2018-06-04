package com.example.demo.REST.ModelREST.ModelResponses;

import com.example.demo.Model.WorldStructureType.WorldStructureType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class WorldStructureTypeResponse {

    private String name;
    private String displayName;

    public WorldStructureTypeResponse(WorldStructureType structure) {
        this.name = structure.getName();
        this.displayName = structure.getDisplayName();
    }
}
