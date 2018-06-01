package com.example.demo.Model.WorldStructure;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WorldStructureRepository extends JpaRepository<WorldStructure, Integer>{

    List<WorldStructure> findAll();
}
