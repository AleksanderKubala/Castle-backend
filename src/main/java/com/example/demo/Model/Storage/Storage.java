package com.example.demo.Model.Storage;

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
@AllArgsConstructor
@NoArgsConstructor
public class Storage {

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
}
