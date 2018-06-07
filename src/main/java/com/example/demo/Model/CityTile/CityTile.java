package com.example.demo.Model.CityTile;

import com.example.demo.Model.Building.Building;
import com.example.demo.Model.BuildingType.BuildingType;
import com.example.demo.Model.TerrainType.TerrainType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import com.example.demo.Model.City.City;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Comparator;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CityTile implements Comparable<CityTile>{

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "public")
    @SequenceGenerator(name = "public", sequenceName = "citytile_seq", initialValue = 1, allocationSize = 1)
    private int id;

    @NotNull
    private Integer rowNumber;

    @NotNull
    private Integer columnNumber;

    @ManyToOne(optional = false)
    @JoinColumn(name = "cityId")
    private City city;

    @ManyToOne(optional = false)
    @JoinColumn(name = "terrainTypeId")
    private TerrainType terrain;

    @OneToOne
    @JoinColumn(name = "buildingId")
    private Building building;

    @Override
    public int compareTo(CityTile o) {
        return Comparator.comparingInt(CityTile::getRowNumber)
                .thenComparingInt(CityTile::getColumnNumber)
                .compare(this, o);
    }

    @Override
    public boolean equals(Object o) {
        if(o == null) {
            return false;
        }
        if(getClass() != o.getClass()) {
            return false;
        }

        CityTile other = (CityTile)o;

        return (rowNumber.equals(other.rowNumber)
                && columnNumber.equals(other.columnNumber));
    }
}
