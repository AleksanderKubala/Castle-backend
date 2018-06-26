package com.example.demo.Security;

import com.example.demo.Model.Player.Player;
import com.example.demo.REST.ModelREST.ModelServices.PlayerService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import static java.util.Collections.emptyList;

import java.util.Optional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private PlayerService playerService;

    public UserDetailsServiceImpl(
            PlayerService playerService
    ) {
        this.playerService = playerService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Player> player = playerService.retrievePlayerByUsername(username);
        if (!player.isPresent()) {
            throw new UsernameNotFoundException(username);
        }
        return new User(player.get().getUsername(), player.get().getPassword(), emptyList());
    }
}
