package com.example.demo.REST.ModelREST.ModelResponses;

import com.example.demo.Model.WorldTile.WorldTile;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class WorldTileResponse {

    private Integer row;
    private Integer column;
    private String terrain;
    private String structure;

    public WorldTileResponse(WorldTile tile) {
        row = tile.getRowNumber();
        column = tile.getColumnNumber();
        terrain = tile.getTerrain().getName();
        if(!(tile.getStructure() == null)) {
            structure = tile.getStructure().getType().getName();
        } else if(!(tile.getCity() == null)) {
            structure = tile.getCity().getType().getName();
        }
    }
}
