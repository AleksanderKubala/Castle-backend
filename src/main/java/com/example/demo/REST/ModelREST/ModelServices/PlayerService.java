package com.example.demo.REST.ModelREST.ModelServices;

import com.example.demo.Model.Player.Player;
import com.example.demo.Model.Player.PlayerRepository;
import com.example.demo.Model.World.World;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PlayerService {

    private PlayerRepository playerRepository;
    private WorldService worldService;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public PlayerService(
            PlayerRepository playerRepository,
            WorldService worldService,
            BCryptPasswordEncoder bCryptPasswordEncoder
    ) {
        this.playerRepository = playerRepository;
        this.worldService = worldService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public Optional<Player> retrievePlayerByEmail(String email) {
        return playerRepository.findByEmail(email);
    }

    public Optional<Player> retrievePlayerByUsername(String username) {
        return playerRepository.findByUsername(username);
    }

    public Player registerNewPlayer(String username, String email, String password) {
        Optional<World> world = worldService.retrieveWorldById(1);
        if(!world.isPresent())
            return null;

        Player newPlayer = new Player();
        newPlayer.setUsername(username);
        newPlayer.setPassword(bCryptPasswordEncoder.encode(password));
        newPlayer.setEmail(email);
        newPlayer.setWorld(world.get());

        playerRepository.save(newPlayer);

        return newPlayer;
    }
}
