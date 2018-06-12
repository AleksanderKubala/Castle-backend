package com.example.demo.REST.ModelREST.ModelServices;

import com.example.demo.Model.City.City;
import com.example.demo.Model.Storage.Storage;
import com.example.demo.Model.Storage.StorageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StorageService {

    private StorageRepository storageRepository;

    @Autowired
    public  StorageService(StorageRepository storageRepository) {
        this.storageRepository = storageRepository;
    }

    public List<Storage> retrieveCityStorage(City city) {
        return storageRepository.findAllByCity(city);
    }

    public void updateStorages(List<Storage> storage) {
        storageRepository.saveAll(storage);
    }
}
