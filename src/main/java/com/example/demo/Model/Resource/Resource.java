package com.example.demo.Model.Resource;

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
public class Resource {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "public")
    @SequenceGenerator(name = "public", sequenceName = "resource_seq", initialValue = 1, allocationSize = 1)
    private int id;

    @NotNull
    private String name;

    @NotNull
    private Boolean plunderable;

    @NotNull
    private Integer startingVolume;

    @NotNull
    private Boolean poolResource;

    @Override
    public boolean equals(Object o) {
        if(o == null)
            return false;

        if(o.getClass() != this.getClass()) {
            return false;
        }

        Resource res = (Resource)o;

        return this.getName().equals(res.getName());
    }
}
