package br.com.bvss.bvssau1apiauthenticator.core.config.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties("openapi")
public class OpenApiProperty {
    private OpenApiInfoProperty info;
}
