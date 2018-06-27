package com.example.demo.Properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@Setter
@ConfigurationProperties(prefix = "updateprops")
public class StorageUpdateProperties {

    private Integer eventDelayInSeconds;
    private Boolean updateOccurs;
}
