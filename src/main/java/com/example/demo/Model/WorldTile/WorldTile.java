package com.example.demo.Model.WorldTile;

import com.example.demo.Model.TerrainType.TerrainType;
import com.example.demo.Model.WorldStructure.WorldStructure;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import com.example.demo.Model.World.World;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class WorldTile {

    @Id
    @GeneratedValue
    private int id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "worldId")
    private World world;

    @ManyToOne(optional = false)
    @JoinColumn(name = "terrainTypeId")
    private TerrainType terrain;

    @ManyToOne
    @JoinColumn(name = "worldStructureId")
    private WorldStructure structure;
}
