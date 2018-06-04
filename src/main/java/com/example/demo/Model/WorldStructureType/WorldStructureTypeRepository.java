package com.example.demo.Model.WorldStructureType;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface WorldStructureTypeRepository extends JpaRepository<WorldStructureType, Integer>{

    List<WorldStructureType> findAll();

    Optional<WorldStructureType> findByName(String name);
}
