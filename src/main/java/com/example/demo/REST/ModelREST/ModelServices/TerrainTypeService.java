package com.example.demo.REST.ModelREST.ModelServices;

import com.example.demo.Model.TerrainType.TerrainType;
import com.example.demo.Model.TerrainType.TerrainTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
public class TerrainTypeService {

    private TerrainTypeRepository terrainRepository;

    @Autowired
    public TerrainTypeService (
            TerrainTypeRepository terrainRepository
    ) {
        this.terrainRepository = terrainRepository;
    }

    public List<TerrainType> retrieveAllTerrain() {
        return terrainRepository.findAll();
    }
}
