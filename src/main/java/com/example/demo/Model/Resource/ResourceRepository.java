package com.example.demo.Model.Resource;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ResourceRepository extends JpaRepository<Resource, Integer> {

    List<Resource> findAll();

    Optional<Resource> findByName(String name);

    List<Resource> findAllByNameIn(List<String> name);
}
