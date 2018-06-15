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
public class Garrison implements Quantitative, Comparable<Garrison>{

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

    @NotNull
    private Integer totalHealth;

    public Garrison(City city, Unit unit) {
        this(city, unit, 0);
    }

    public Garrison(City city, Unit unit, Integer quantity) {
        this.city = city;
        this.unit = unit;
        this.quantity = quantity;
        this.totalHealth = this.quantity*this.unit.getHealth();
    }

    public Garrison(Garrison garrison) {
        this();
        this.setId(garrison.getId());
        this.setUnit(garrison.getUnit());
        this.setCity(garrison.getCity());
        this.setQuantity(garrison.getQuantity());
        this.totalHealth = garrison.getTotalHealth();
    }

    public Garrison(Garrison garrison, Integer quantity) {
        this();
        this.setId(garrison.getId());
        this.setUnit(garrison.getUnit());
        this.setCity(garrison.getCity());
        this.setQuantity(quantity);
        this.totalHealth = this.unit.getHealth()*this.quantity;
    }

    @Override
    public int compareTo(Garrison o) {
        return this.getUnit().compareTo(o.getUnit());
    }

    @Override
    public boolean equals(Object o) {
        if(o == null)
            return false;

        if(this.getClass() != o.getClass())
            return false;

        Garrison other = (Garrison)o;

        return (this.getId() == other.getId());
    }
}
