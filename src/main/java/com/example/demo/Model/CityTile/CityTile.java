package com.example.demo.Model.CityTile;

import com.example.demo.Model.Building.Building;
import com.example.demo.Model.TerrainType.TerrainType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import com.example.demo.Model.City.City;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CityTile {

    @Id
    @GeneratedValue
    private int id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "cityId")
    private City city;

    @ManyToOne(optional = false)
    @JoinColumn(name = "terrainTypeId")
    private TerrainType terrain;

    @ManyToOne
    @JoinColumn(name = "buildingId")
    private Building building;
}
