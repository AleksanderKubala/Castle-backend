package com.example.demo.REST.ModelREST.ModelServices;

import com.example.demo.Model.City.City;
import com.example.demo.Model.Resource.Resource;
import com.example.demo.Model.Storage.Storage;
import com.example.demo.Model.Storage.StorageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class StorageService {

    private StorageRepository storageRepository;
    private ResourceService resourceService;

    @Autowired
    public  StorageService(
            StorageRepository storageRepository,
            ResourceService resourceService
    ) {
        this.storageRepository = storageRepository;
        this.resourceService = resourceService;
    }

    public List<Storage> retrieveCityStorage(City city) {
        return storageRepository.findAllByCity(city);
    }

    public void updateStorages(List<Storage> storage) {
        storageRepository.saveAll(storage);
    }

    public List<Storage> createCityStorage(City city) {
        List<Resource> resources = resourceService.retrieveResources();
        List<Storage> storages = new ArrayList<>();

        for (Resource resource : resources) {
            Storage storage = new Storage();
            storage.setCity(city);
            storage.setResource(resource);
            storage.setQuantity(resource.getStartingVolume());
            storages.add(storage);
        }
        storageRepository.saveAll(storages);

        return storages;
    }
}
