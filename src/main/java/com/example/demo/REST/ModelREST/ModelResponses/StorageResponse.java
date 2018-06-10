package com.example.demo.REST.ModelREST.ModelResponses;

import com.example.demo.Model.Storage.Storage;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StorageResponse {

    private String resource;
    private Integer quantity;

    public StorageResponse(Storage storage) {
        this.quantity = storage.getQuantity();
        this.resource = storage.getResource().getName();
    }
}
