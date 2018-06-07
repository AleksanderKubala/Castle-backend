package com.example.demo.REST.ModelREST.ModelResponses;

import com.example.demo.Model.CityTile.CityTile;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CityTileResponse {

    private Integer cityId;
    private Integer row;
    private Integer column;
    private String terrain;
    private String building;

    public CityTileResponse(CityTile tile) {
        cityId = tile.getCity().getId();
        row = tile.getRowNumber();
        column = tile.getColumnNumber();
        terrain = tile.getTerrain().getName();
        if(!(tile.getBuilding() == null)) {
            building = tile.getBuilding().getType().getName();
        }
    }
}
