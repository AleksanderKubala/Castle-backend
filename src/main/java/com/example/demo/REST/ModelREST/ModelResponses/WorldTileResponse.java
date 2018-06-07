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
    private String structureType;
    private CityResponse city;


    public WorldTileResponse(WorldTile tile) {
        row = tile.getRowNumber();
        column = tile.getColumnNumber();
        terrain = tile.getTerrain().getName();
        if(!(tile.getCity() == null)) {
            structureType = tile.getCity().getType().getName();
            city = new CityResponse(tile.getCity());
        }
    }
}
