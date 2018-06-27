package com.example.demo.Config;

import com.example.demo.REST.ModelREST.ModelServices.PlayerService;
import com.example.demo.Security.AuthenticationFilter;
import com.example.demo.Properties.SecurityProperties;
import com.example.demo.Security.AuthorizationFilter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
@EnableWebSecurity
public class WebSecurity extends WebSecurityConfigurerAdapter {

    private UserDetailsService userDetailsService;
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    private SecurityProperties securityProperties;
    private PlayerService playerService;
    private MappingJackson2HttpMessageConverter messageConverter;

    public WebSecurity(
            @Qualifier("userDetailsServiceImpl") UserDetailsService userDetailsService,
            BCryptPasswordEncoder bCryptPasswordEncoder,
            SecurityProperties securityProperties,
            PlayerService playerService,
            MappingJackson2HttpMessageConverter messageConverter
    ) {
        this.userDetailsService = userDetailsService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.securityProperties = securityProperties;
        this.playerService = playerService;
        this.messageConverter = messageConverter;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        String[] matchers = new String[securityProperties.getUnsecuredEndpoints().size()];
        AuthenticationFilter filter = new AuthenticationFilter(
                authenticationManager(),
                securityProperties,
                playerService,
                messageConverter
        );
        http.cors().and().csrf().disable().authorizeRequests()
                .antMatchers(HttpMethod.POST, securityProperties.getSignupUrl()).permitAll()
                .antMatchers(HttpMethod.GET, securityProperties.getUnsecuredEndpoints().toArray(matchers)).permitAll()
                .anyRequest().authenticated()
                .and()
                .addFilter(filter)
                .addFilter(new AuthorizationFilter(authenticationManager(), securityProperties))
                // this disables session creation on Spring Security
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder);
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", new CorsConfiguration().applyPermitDefaultValues());
        return source;
    }
}
