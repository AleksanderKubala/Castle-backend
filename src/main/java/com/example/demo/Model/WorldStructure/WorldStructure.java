package com.example.demo.Model.WorldStructure;

import com.example.demo.Model.WorldStructureType.WorldStructureType;
import com.example.demo.Model.WorldTile.WorldTile;
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
public class WorldStructure {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "public")
    @SequenceGenerator(name = "public", sequenceName = "structure_seq", initialValue = 1, allocationSize = 1)
    private int id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "worldStructureTypeId")
    private WorldStructureType type;

    @OneToOne(optional = false)
    @JoinColumn(name = "worldTileId")
    private WorldTile tile;
}
