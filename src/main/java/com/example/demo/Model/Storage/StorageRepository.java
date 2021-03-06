package com.example.demo.Model.Storage;

import com.example.demo.Model.City.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StorageRepository extends JpaRepository<Storage, Integer> {

    List<Storage> findAll();

    List<Storage> findAllByCity(City city);
}
