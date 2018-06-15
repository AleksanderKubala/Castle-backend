package com.example.demo.REST.Requests;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SettlementRequest {

    private Integer world;
    private String player;
    private Integer city;
    private Integer tileRow;
    private Integer tileColumn;
}
