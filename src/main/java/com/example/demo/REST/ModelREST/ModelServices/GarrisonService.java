package com.example.demo.REST.ModelREST.ModelServices;

import com.example.demo.Model.City.City;
import com.example.demo.Model.Garrison.Garrison;
import com.example.demo.Model.Garrison.GarrisonRepository;
import com.example.demo.REST.ModelREST.ModelResponses.GarrisonResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GarrisonService {

    private GarrisonRepository garrisonRepository;

    @Autowired
    public GarrisonService(GarrisonRepository garrisonRepository) {
        this.garrisonRepository = garrisonRepository;
    }

    public List<Garrison> retrieveCityGarrison(City city) {
        return this.garrisonRepository.findAllByCity(city);
    }

}
