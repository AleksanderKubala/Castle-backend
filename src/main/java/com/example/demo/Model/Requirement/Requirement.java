package com.example.demo.Model.Requirement;

import com.example.demo.Model.Abstract.Quantitative;
import com.example.demo.Model.Abstract.Requisition;
import com.example.demo.Model.BuildingType.BuildingType;
import com.example.demo.Model.Resource.Resource;
import com.example.demo.Model.Unit.Unit;
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
public class Requirement implements Requisition {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "public")
    @SequenceGenerator(name = "public", sequenceName = "requirement_seq", initialValue = 1, allocationSize = 1)
    private int id;

    @ManyToOne
    @JoinColumn(name = "buildingTypeId")
    private BuildingType buildingType;

    @ManyToOne
    @JoinColumn(name = "unitId")
    private Unit unit;

    @ManyToOne(optional = false)
    @JoinColumn(name = "resourceId")
    private Resource resource;

    @NotNull
    private Double recoveryCoef;

    @NotNull
    private Integer quantity;

}
