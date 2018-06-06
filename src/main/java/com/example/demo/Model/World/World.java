package com.example.demo.Model.World;

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
public class World {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "public")
    @SequenceGenerator(name = "public", sequenceName = "world_seq", initialValue = 1, allocationSize = 1)
    private int id;

    @NotNull
    private String name;

    @NotNull
    private Integer rows;

    @NotNull
    private Integer columns;
}
