package com.example.demo.REST.ModelREST.ModelResponses;

import com.example.demo.Model.Garrison.Garrison;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GarrisonResponse {

    private String unit;
    private Integer quantity;

    public GarrisonResponse(Garrison garrison) {
        this.unit = garrison.getUnit().getName();
        this.quantity = garrison.getQuantity();
    }
}
