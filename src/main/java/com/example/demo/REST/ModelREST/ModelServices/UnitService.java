package com.example.demo.REST.ModelREST.ModelServices;

import com.example.demo.Model.Unit.Unit;
import com.example.demo.Model.Unit.UnitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UnitService {

    private UnitRepository unitRepository;

    @Autowired
    public UnitService(UnitRepository unitRepository) {
        this.unitRepository = unitRepository;
    }

    public List<Unit> retrieveAllUnits() {
        return this.unitRepository.findAll();
    }
}
