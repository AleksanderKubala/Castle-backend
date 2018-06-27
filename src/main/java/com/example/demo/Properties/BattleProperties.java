package com.example.demo.Properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@Setter
@ConfigurationProperties(prefix = "battleprops")
public class BattleProperties {

    private Double minimumMultiplierValue;
    private Double multiplierValueRange;

}
