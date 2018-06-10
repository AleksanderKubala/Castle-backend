package com.example.demo.REST.ModelREST.ModelResponses;

import com.example.demo.Model.City.City;
import com.example.demo.Model.Storage.Storage;
import com.example.demo.Model.WorldTile.WorldTile;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

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
        this(tile, null, null);
    }

    public WorldTileResponse(WorldTile tile, City city, List<Storage> storage) {
        row = tile.getRowNumber();
        column = tile.getColumnNumber();
        terrain = tile.getTerrain().getName();
        if((city != null) && (storage != null)) {
            structureType = tile.getCity().getType().getName();
            this.city = new CityResponse(tile.getCity(), storage);
        }

    }
}
