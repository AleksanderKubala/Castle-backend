package com.example.demo.REST.ModelREST.ModelResponses;

import com.example.demo.Model.Resource.Resource;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ResourceResponse {

    private String name;
    private Boolean plunderable;

    public ResourceResponse(Resource resource) {
        this.name = resource.getName();
        this.plunderable = resource.getPlunderable();
    }
}
