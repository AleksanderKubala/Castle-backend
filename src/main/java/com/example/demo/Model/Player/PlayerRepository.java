package com.example.demo.Model.Player;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PlayerRepository extends JpaRepository<Player, Integer> {
    List<Player> findAll();

    Optional<Player> findByEmail(String email);

    Optional<Player> findByUsername(String username);
}
