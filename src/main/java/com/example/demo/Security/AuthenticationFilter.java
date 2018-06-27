package com.example.demo.Security;

import com.example.demo.Model.Player.Player;
import com.example.demo.Properties.SecurityProperties;
import com.example.demo.REST.ModelREST.ModelResponses.WorldResponse;
import com.example.demo.REST.ModelREST.ModelServices.PlayerService;
import com.example.demo.REST.Responses.LoginResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;

public class AuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private AuthenticationManager authenticationManager;
    private SecurityProperties securityProperties;
    private ObjectMapper mapper;
    private PlayerService playerService;


    public AuthenticationFilter(
            AuthenticationManager authenticationManager,
            SecurityProperties securityProperties,
            PlayerService playerService,
            MappingJackson2HttpMessageConverter messageConverter
    ) {
        this.authenticationManager = authenticationManager;
        this.securityProperties = securityProperties;
        this.playerService = playerService;
        this.mapper = messageConverter.getObjectMapper();
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest req,
                                                HttpServletResponse res) throws AuthenticationException {
        try {
            Player creds = new ObjectMapper()
                    .readValue(req.getInputStream(), Player.class);

            return authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            creds.getUsername(),
                            creds.getPassword(),
                            new ArrayList<>())
            );
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest req,
                                            HttpServletResponse res,
                                            FilterChain chain,
                                            Authentication auth) throws IOException, ServletException {

        String token = Jwts.builder()
                .setSubject(((User) auth.getPrincipal()).getUsername())
                .setExpiration(new Date(System.currentTimeMillis() + securityProperties.getExpirationTime()))
                .signWith(SignatureAlgorithm.HS512, securityProperties.getSecret().getBytes())
                .compact();

        res.setStatus(HttpServletResponse.SC_OK);
        res.addHeader(securityProperties.getHeaderString(), token);
        User user = (User) auth.getPrincipal();
        Optional<Player> player = playerService.retrievePlayerByUsername(user.getUsername());
        LoginResponse loginResponse = new LoginResponse(player.get(), token);

        PrintWriter writer = res.getWriter();
        mapper.writeValue(writer, loginResponse);
        writer.flush();
    }
}
