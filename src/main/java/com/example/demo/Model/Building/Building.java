package com.example.demo.Model.Building;

import com.example.demo.Model.BuildingType.BuildingType;
import com.example.demo.Model.CityTile.CityTile;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Building {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "public")
    @SequenceGenerator(name = "public", sequenceName = "building_seq", initialValue = 1, allocationSize = 1)
    private int id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "buildingTypeId")
    private BuildingType type;

    @OneToOne(optional = false)
    @JoinColumn(name = "cityTileId")
    private CityTile tile;
}
