package com.example.demo.Properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

@ConfigurationProperties(prefix="securityprops")
@Getter
@Setter
public class SecurityProperties {

    private String secret;
    private long expirationTime;
    private String headerString;
    private String signupUrl;
    private List<String> unsecuredEndpoints;
}
