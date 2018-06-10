package com.example.demo.REST.ModelREST.ModelResponses;

import com.example.demo.Model.City.City;
import com.example.demo.Model.Production.Production;
import com.example.demo.Model.Storage.Storage;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CityResponse {

    private Integer id;
    private String name;
    private Integer rows;
    private Integer columns;
    private Boolean capital;
    private String owner;
    private List<StorageResponse> storage;
    private List<ProductionResponse> productions;
    private List<CityTileResponse> tiles;

    public CityResponse(City city,
                        List<Storage> storages,
                        List<Production> productions,
                        List<CityTileResponse> tiles) {
        this.storage = new ArrayList<>();
        this.productions = new ArrayList<>();

        this.id = city.getId();
        this.name = city.getName();
        rows = city.getRows();
        columns = city.getColumns();
        capital = city.getCapital();
        owner = city.getPlayer().getUsername();
        this.tiles = tiles;
        for(Storage storage: storages) {
            this.storage.add(new StorageResponse(storage));
        }
        if(productions != null) {
            for(Production production: productions) {
                this.productions.add(new ProductionResponse(production));
            }
        }
    }

    public CityResponse(City city, List<Storage> storages, List<Production> productions) {
        this(city, storages, productions, null);
    }

    public CityResponse(City city, List<Storage> storages) {
        this(city, storages, null, null);
    }
}
