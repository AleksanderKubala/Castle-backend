package com.example.demo.REST.Controllers;

import com.example.demo.Model.Player.Player;
import com.example.demo.Model.Player.PlayerRepository;
import com.example.demo.REST.Requests.LoginRequest;
import com.example.demo.REST.Responses.LoginResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController("/login")
@CrossOrigin(origins = "http://localhost:4200")
public class LoginController {

    private PlayerRepository playerRepository;

    @Autowired
    public LoginController(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    @PostMapping()
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request) {
        Optional<Player> player = playerRepository.findByUsername(request.getUsername());
        if(!player.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        if(player.get().getPassword().equals(request.getPassword())) {
            LoginResponse response = new LoginResponse(player.get());
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }
}
