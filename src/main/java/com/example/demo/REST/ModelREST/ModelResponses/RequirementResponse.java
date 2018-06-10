package com.example.demo.REST.ModelREST.ModelResponses;

import com.example.demo.Model.Requirement.Requirement;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RequirementResponse {

    private String resource;
    private Integer quantity;

    public RequirementResponse(Requirement requirement) {
        this.resource = requirement.getResource().getName();
        this.quantity = requirement.getQuantity();
    }
}
