package com.example.demo.REST.Responses;

import com.example.demo.REST.ModelREST.ModelResponses.GarrisonResponse;
import com.example.demo.REST.ModelREST.ModelResponses.WorldTileResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SettlementResponse {

    private WorldTileResponse tile;
    private List<GarrisonResponse> sourceCityGarrison;
}
