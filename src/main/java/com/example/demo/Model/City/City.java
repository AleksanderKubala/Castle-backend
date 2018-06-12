package com.example.demo.Model.City;

import com.example.demo.Model.Production.Production;
import com.example.demo.Model.Storage.Storage;
import com.example.demo.Model.WorldStructureType.WorldStructureType;
import com.example.demo.Model.WorldTile.WorldTile;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import com.example.demo.Model.Player.Player;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class City {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "public")
    @SequenceGenerator(name = "public", sequenceName = "city_seq", initialValue = 1, allocationSize = 1)
    private Integer id;

    @NotNull
    private String name;

    @NotNull
    private Integer rows;

    @NotNull
    private Integer columns;

    @NotNull
    private Boolean capital;

    @NotNull
    private boolean hasMainBuilding;

    @NotNull
    private boolean hasArchery;

    @NotNull
    private boolean hasBarracks;

    @NotNull
    private boolean hasStables;

    @ManyToOne(optional = false)
    @JoinColumn(name = "userId")
    private Player player;

    @ManyToOne(optional = false)
    @JoinColumn(name = "worldStructureTypeId")
    private WorldStructureType type;

    @OneToOne(optional = false)
    @JoinColumn(name = "worldTileId")
    private WorldTile tile;

}
