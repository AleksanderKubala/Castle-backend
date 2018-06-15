package com.example.demo.Model.Unit;

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
@NoArgsConstructor
@AllArgsConstructor
public class Unit implements Comparable<Unit>{

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "public")
    @SequenceGenerator(name = "public", sequenceName = "unit_seq", initialValue = 1, allocationSize = 1)
    private Integer id;

    @NotNull
    private String name;

    @NotNull
    private String displayName;

    @NotNull
    private Integer strength;

    @NotNull
    private Integer speed;

    @NotNull
    private Integer health;

    @NotNull
    private Integer capacity;

    @Override
    public int compareTo(Unit o) {
        return Comparator.comparingInt(Unit::getSpeed).compare(this, o);
    }
}

