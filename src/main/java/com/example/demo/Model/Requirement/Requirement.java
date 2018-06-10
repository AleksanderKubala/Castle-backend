package com.example.demo.Model.Requirement;

import com.example.demo.Model.BuildingType.BuildingType;
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
public class Requirement {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "public")
    @SequenceGenerator(name = "public", sequenceName = "requirement_seq", initialValue = 1, allocationSize = 1)
    private int id;

    @ManyToOne
    @JoinColumn(name = "buildingTypeId")
    private BuildingType buildingType;

    @ManyToOne(optional = false)
    @JoinColumn(name = "resourceId")
    private Resource resource;

    @NotNull
    private Integer quantity;
}
