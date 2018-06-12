package com.example.demo.REST.ModelREST.ModelResponses;

import com.example.demo.Model.Unit.Unit;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UnitResponse {

    private String name;
    private String displayName;

    public UnitResponse(Unit unit) {
        this.name = unit.getName();
        this.displayName = unit.getDisplayName();
    }
}
