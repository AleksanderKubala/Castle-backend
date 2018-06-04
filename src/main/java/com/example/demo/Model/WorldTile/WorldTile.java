package com.example.demo.Model.WorldTile;

import com.example.demo.Model.City.City;
import com.example.demo.Model.TerrainType.TerrainType;
import com.example.demo.Model.WorldStructure.WorldStructure;
import com.example.demo.Model.WorldStructureType.WorldStructureType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import com.example.demo.Model.World.World;
import org.apache.logging.log4j.util.PropertySource;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Comparator;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class WorldTile implements Comparable<WorldTile>{

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "public")
    @SequenceGenerator(name = "public", sequenceName = "worldtile_seq", initialValue = 1, allocationSize = 1)
    private int id;

    @NotNull
    private Integer rowNumber;

    @NotNull
    private Integer columnNumber;

    @ManyToOne(optional = false)
    @JoinColumn(name = "worldId")
    private World world;

    @ManyToOne(optional = false)
    @JoinColumn(name = "terrainTypeId")
    private TerrainType terrain;

    @OneToOne
    @JoinColumn(name = "worldStructureId")
    private WorldStructure structure;

    @OneToOne
    @JoinColumn(name = "cityId")
    private City city;

    @Override
    public int compareTo(WorldTile o) {
        return Comparator.comparingInt(WorldTile::getRowNumber)
                .thenComparingInt(WorldTile::getColumnNumber)
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

        WorldTile other = (WorldTile)o;

        return (rowNumber.equals(other.rowNumber)
                && columnNumber.equals(other.columnNumber));
    }
}
