package com.example.demo.REST.ModelREST.ModelServices;

import com.example.demo.Model.WorldStructure.WorldStructure;
import com.example.demo.Model.WorldStructure.WorldStructureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class WorldStructureService {

    private WorldStructureRepository structureRepository;

    @Autowired
    public WorldStructureService(
        WorldStructureRepository structureRepository
    ) {
        this.structureRepository = structureRepository;
    }

    public Collection<WorldStructure> retrieveAllStructures() {
        return structureRepository.findAll();
    }
}
