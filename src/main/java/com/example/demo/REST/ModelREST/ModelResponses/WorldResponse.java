package com.example.demo.REST.ModelREST.ModelResponses;

import com.example.demo.Model.World.World;
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
public class WorldResponse {

    private Integer id;
    private String name;
    private Integer rows;
    private Integer columns;
    private List<WorldTileResponse> tiles;

    public WorldResponse(World world, List<WorldTileResponse> tiles) {
        this.id = world.getId();
        this.name = world.getName();
        rows = world.getRows();
        columns = world.getColumns();
        this.tiles = tiles;
    }

    public WorldResponse(World world) {
        this(world, null);
    }

}
