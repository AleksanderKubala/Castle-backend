package com.example.demo.Model.World;

import com.example.demo.Model.Player.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface WorldRepository extends JpaRepository<World, Integer> {

    List<World> findAll();

}
