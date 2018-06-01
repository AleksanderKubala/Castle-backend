package com.example.demo.REST.ModelREST.ModelResponses;

import com.example.demo.Model.TerrainType.TerrainType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class TerrainTypeResponse {

    private String name;

    public TerrainTypeResponse(TerrainType terrain) {
        this.name = terrain.getName();
    }
}
