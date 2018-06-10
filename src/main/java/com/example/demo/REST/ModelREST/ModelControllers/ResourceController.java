package com.example.demo.REST.ModelREST.ModelControllers;

import com.example.demo.Model.Resource.Resource;
import com.example.demo.REST.ModelREST.ModelResponses.ResourceResponse;
import com.example.demo.REST.ModelREST.ModelServices.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ResourceController {

    private ResourceService resourceService;

    @Autowired
    public ResourceController(ResourceService resourceService) {
        this.resourceService = resourceService;
    }

    @GetMapping("/resource")
    public ResponseEntity<List<ResourceResponse>> getAllResources() {
        List<Resource> resources = resourceService.retrieveResources();
        List<ResourceResponse> response = new ArrayList<>();

        for(Resource resource: resources) {
            response.add(new ResourceResponse(resource));
        }

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
