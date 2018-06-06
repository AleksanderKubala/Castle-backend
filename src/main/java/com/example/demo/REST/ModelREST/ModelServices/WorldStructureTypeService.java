package com.example.demo.REST.ModelREST.ModelServices;

import com.example.demo.Model.WorldStructureType.WorldStructureType;
import com.example.demo.Model.WorldStructureType.WorldStructureTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
public class WorldStructureTypeService {

    private WorldStructureTypeRepository structureTypeRepository;

    @Autowired
    public WorldStructureTypeService(
            WorldStructureTypeRepository structureTypeRepository
    ) {
        this.structureTypeRepository = structureTypeRepository;
    }

    public List<WorldStructureType> retrieveAllStructureTypes() {
        return structureTypeRepository.findAll();
    }

    public Optional<WorldStructureType> retrieveStructureTypeByName(String name) {
        return structureTypeRepository.findByName(name);
    }
}
