package com.example.demo.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import com.example.demo.Model.Building.BuildingRepository;
import com.example.demo.Model.City.CityRepository;
import com.example.demo.Model.CityTile.CityTileRepository;
import com.example.demo.Model.User.UserRepository;
import com.example.demo.Model.World.WorldRepository;
import com.example.demo.Model.WorldTile.WorldTileRepository;
import org.springframework.stereotype.Component;

@Component
public class DbTest implements CommandLineRunner{

    private WorldRepository worldRepository;
    private WorldTileRepository worldTileRepository;
    private UserRepository userRepository;
    private CityRepository cityRepository;
    private CityTileRepository cityTileRepository;
    private BuildingRepository buildingRepository;

    @Autowired
    public DbTest(
            WorldRepository worldRepository,
            WorldTileRepository worldTileRepository,
            UserRepository userRepository,
            CityRepository cityRepository,
            CityTileRepository cityTileRepository,
            BuildingRepository buildingRepository
    ) {
       this.buildingRepository = buildingRepository;
       this.cityRepository = cityRepository;
       this.cityTileRepository = cityTileRepository;
       this.userRepository = userRepository;
       this.worldRepository = worldRepository;
       this.worldTileRepository = worldTileRepository;
    }

    public void init() {
        buildingRepository.findAll();
        worldRepository.findAll();
        worldTileRepository.findAll();
        cityRepository.findAll();
        cityTileRepository.findAll();
        userRepository.findAll();
    }

    @Override
    public void run(String... args) throws Exception {
        //init();
    }
}
