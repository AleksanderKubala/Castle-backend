package com.example.demo.REST.ModelREST.ModelResponses;

import com.example.demo.Model.WorldStructure.WorldStructure;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class WorldStructureResponse {

    private String name;
    private String displayName;

    public WorldStructureResponse(WorldStructure structure) {
        this.name = structure.getName();
        this.displayName = structure.getDisplayName();
    }
}
