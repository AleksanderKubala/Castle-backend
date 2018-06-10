package com.example.demo.REST.ModelREST.ModelServices;

import com.example.demo.Model.Resource.Resource;
import com.example.demo.Model.Resource.ResourceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ResourceService {

    private ResourceRepository resourceRepository;

    @Autowired
    public ResourceService(ResourceRepository resourceRepository) {
        this.resourceRepository = resourceRepository;
    }

    public List<Resource> retrieveResources() {
        return resourceRepository.findAll();
    }
}
