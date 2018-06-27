package com.example.demo.Model.Storage;

import com.example.demo.Model.Abstract.Quantitative;
import com.example.demo.Model.Abstract.Requisition;
import com.example.demo.Model.City.City;
import com.example.demo.Model.Resource.Resource;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Comparator;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Storage implements Requisition, Comparable<Storage> {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "public")
    @SequenceGenerator(name = "public", sequenceName = "storage_seq", initialValue = 1, allocationSize = 1)
    private int id;

    @ManyToOne
    @JoinColumn(name = "cityId")
    private City city;

    @ManyToOne
    @JoinColumn(name = "resourceId")
    private Resource resource;

    @NotNull
    private Integer quantity;

    @Override
    public Double getRecoveryCoef() {
        return 0.0;
    }

    @Override
    public int compareTo(Storage o) {
        return Comparator.comparingInt(Storage::getQuantity)
                .compare(this, o);
    }

    @Override
    public boolean equals(Object o) {
        if(o == null)
            return false;

        if(this.getClass() != o.getClass())
            return false;

        Storage other = (Storage)o;

        return this.getResource().equals(other.getResource());
    }
}
