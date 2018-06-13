package com.example.demo.Model.Garrison;

import com.example.demo.Model.Abstract.Quantitative;
import com.example.demo.Model.City.City;
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
@AllArgsConstructor
@NoArgsConstructor
public class Garrison implements Quantitative{

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "public")
    @SequenceGenerator(name = "public", sequenceName = "garrison_seq", initialValue = 1, allocationSize = 1)
    private int id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "cityId")
    private City city;

    @ManyToOne(optional = false)
    @JoinColumn(name ="unitId")
    private Unit unit;

    @NotNull
    private Integer quantity;

    public Garrison(City city, Unit unit) {
        this.city = city;
        this.unit = unit;
        this.quantity = 0;
    }
}
