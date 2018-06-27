package com.example.demo.REST.Requests;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AttackRequest {

    private Integer attackerCity;
    private Integer targetCity;
    private List<GarrisonRequest> troops;
    private List<String> plunder;
}
