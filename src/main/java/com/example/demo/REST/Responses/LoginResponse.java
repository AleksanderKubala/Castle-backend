package com.example.demo.REST.Responses;

import com.example.demo.Model.Player.Player;
import com.example.demo.REST.ModelREST.ModelResponses.WorldResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LoginResponse {

    private String username;
    private WorldResponse world;

    public LoginResponse(Player player) {
        this.username = player.getUsername();
        this.world = new WorldResponse(player.getWorld());
    }
}
