package com.example.demo.Model.Production;

import com.example.demo.Model.Building.Building;
import com.example.demo.Model.BuildingType.BuildingType;
import com.example.demo.Model.City.City;
import com.example.demo.Model.Resource.Resource;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Production {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "public")
    @SequenceGenerator(name = "public", sequenceName = "production_seq", initialValue = 1, allocationSize = 1)
    private int id;

    @OneToOne
    @JoinColumn(name = "cityId")
    private City city;

    @ManyToOne
    @JoinColumn(name = "buildingTypeId")
    private BuildingType buildingType;

    @ManyToOne
    @JoinColumn(name = "resourceId")
    private Resource resource;

    @NotNull
    private Integer quantity;

}
