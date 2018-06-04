package com.example.demo.REST.ModelREST.ModelResponses;

import com.example.demo.Model.City.City;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Collection;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CityResponse {

    private Integer rows;
    private Integer columns;
    private List<CityTileResponse> tiles;

    public CityResponse(City city, List<CityTileResponse> tiles) {
        rows = city.getRows();
        columns = city.getColumns();
        this.tiles = tiles;
    }
}
