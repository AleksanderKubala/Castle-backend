package com.example.demo.REST.Requests;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ConstructionRequest {

    Integer cityId;
    Integer row;
    Integer column;
    String buildingType;

}
