package com.example.demo.REST.ModelREST.ModelResponses;

import com.example.demo.Model.City.City;
import com.example.demo.Model.Garrison.Garrison;
import com.example.demo.Model.Production.Production;
import com.example.demo.Model.Storage.Storage;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CityResponse {

    public static CityResponse createResponse(City city,
                                              List<Storage> storages,
                                              List<Production> productions,
                                              List<Garrison> garrison,
                                              List<CityTileResponse> tiles) {
        return new CityResponse(city, storages, productions, garrison, tiles);
    }

    public static CityResponse createResponse(City city,
                                              List<Storage> storages,
                                              List<Production> productions,
                                              List<Garrison> garrison,
                                              CityTileResponse tile) {
        return new CityResponse(city, storages, productions, garrison, Collections.singletonList(tile));
    }

    public static CityResponse createResponse(City city,
                                              List<Storage> storages,
                                              List<Production> productions,
                                              List<Garrison> garrison) {
        return new CityResponse(city, storages, productions, garrison,  null);
    }

    public static CityResponse createResponse(City city,
                                              List<Storage> storages,
                                              List<Production> productions) {
        return new CityResponse(city, storages, productions, null, null);
    }

    public static CityResponse createResponse(City city,
                                          List<Storage> storages) {
        return new CityResponse(city, storages, null, null, null);
    }

    private Integer id;
    private String name;
    private Integer rows;
    private Integer columns;
    private Boolean capital;
    private Boolean hasMainBuilding;
    private Boolean hasArchery;
    private Boolean hasBarracks;
    private Boolean hasStables;
    private String owner;
    private List<StorageResponse> storage;
    private List<ProductionResponse> productions;
    private List<GarrisonResponse> garrison;
    private List<CityTileResponse> tiles;

    public CityResponse(City city,
                        List<Storage> storages,
                        List<Production> productions,
                        List<Garrison> garrison,
                        List<CityTileResponse> tiles) {
        this.storage = new ArrayList<>();
        this.productions = new ArrayList<>();
        this.garrison = new ArrayList<>();
        this.tiles = new ArrayList<>();

        this.id = city.getId();
        this.name = city.getName();
        rows = city.getRows();
        columns = city.getColumns();
        capital = city.getCapital();
        owner = city.getPlayer().getUsername();
        hasMainBuilding = city.isHasMainBuilding();
        hasArchery = city.isHasArchery();
        hasBarracks = city.isHasBarracks();
        hasStables = city.isHasStables();
        if(tiles != null)
            this.tiles = tiles;
        if(storages != null) {
            for (Storage storage : storages) {
                this.storage.add(new StorageResponse(storage));
            }
        }
        if(garrison != null) {
            for(Garrison troop: garrison) {
                this.garrison.add(new GarrisonResponse(troop));
            }
        }
        if(productions != null) {
            for(Production production: productions) {
                this.productions.add(new ProductionResponse(production));
            }
        }
    }
}
