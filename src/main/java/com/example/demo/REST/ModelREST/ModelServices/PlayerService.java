package com.example.demo.REST.ModelREST.ModelServices;

import com.example.demo.Model.Player.Player;
import com.example.demo.Model.Player.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PlayerService {

    private PlayerRepository playerRepository;

    @Autowired
    public PlayerService(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    public Optional<Player> retrievePlayerByEmail(String email) {
        return playerRepository.findByEmail(email);
    }

    public Optional<Player> retrievePlayerByUsername(String username) {
        return playerRepository.findByUsername(username);
    }
}
