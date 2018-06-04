package com.example.demo.REST.ModelREST.ModelServices;

import com.example.demo.Model.City.City;
import com.example.demo.Model.City.CityRepository;
import com.example.demo.Model.CityTile.CityTile;
import com.example.demo.Model.CityTile.CityTileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
public class CityService {

    private CityRepository cityRepository;
    private CityTileRepository cityTileRepository;

    @Autowired
    public CityService(
            CityRepository cityRepository,
            CityTileRepository cityTileRepository
    ) {
        this.cityRepository = cityRepository;
        this.cityTileRepository = cityTileRepository;
    }

    public City retrieveCity(Integer cityNumber) {
        Optional<City> opCity = cityRepository.findById(cityNumber);
        if(opCity.isPresent())
            return opCity.get();
        else
            return null;
    }

    public List<CityTile> retrieveCityTiles(City city) {
        return cityTileRepository.findAllByCity(city);
    }
}
