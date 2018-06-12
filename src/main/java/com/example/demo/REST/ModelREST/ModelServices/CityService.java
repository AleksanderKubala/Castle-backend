package com.example.demo.REST.ModelREST.ModelServices;

import com.example.demo.Model.City.City;
import com.example.demo.Model.City.CityRepository;
import com.example.demo.Model.CityTile.CityTile;
import com.example.demo.Model.CityTile.CityTileRepository;
import com.example.demo.Model.Player.Player;
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

    public Optional<City> retrieveCityById(Integer id) {
        return cityRepository.findById(id);
    }

    public List<CityTile> retrieveCityTiles(City city) {
        return cityTileRepository.findAllByCity(city);
    }

    public List<City> retrieveCitiesByPlayer(Player player) {
        return cityRepository.findAllByPlayer(player);
    }

    public Optional<CityTile> retrieveTile(City city, Integer row, Integer column) {
        return cityTileRepository.findByCityAndRowNumberAndColumnNumber(city, row, column);
    }

    public List<City> retrieveAllCities() {
        return cityRepository.findAll();
    }

    public void updateTile(CityTile tile) {
        cityTileRepository.save(tile);
    }
}
