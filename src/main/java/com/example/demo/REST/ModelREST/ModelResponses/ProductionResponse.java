package com.example.demo.REST.ModelREST.ModelResponses;

import com.example.demo.Model.Production.Production;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductionResponse {

    private String resource;
    private Integer quantity;

    public ProductionResponse(Production production) {
        this.resource = production.getResource().getName();
        this.quantity = production.getQuantity();
    }
}
