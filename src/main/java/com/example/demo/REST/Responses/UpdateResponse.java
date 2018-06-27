package com.example.demo.REST.Responses;

import com.example.demo.REST.ModelREST.ModelResponses.CityResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateResponse {

    private List<CityResponse> cities;
}
