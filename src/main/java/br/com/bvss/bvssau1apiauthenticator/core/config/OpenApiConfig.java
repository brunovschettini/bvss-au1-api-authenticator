package br.com.bvss.bvssau1apiauthenticator.core.config;

import br.com.bvss.bvssau1apiauthenticator.core.config.properties.OpenApiProperty;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Autowired
    private OpenApiProperty openApiProperty;

    @Bean
    OpenAPI customOpenAPI() {
        return new OpenAPI()
                .components(new Components())
                .info(new Info()
                        .title(openApiProperty.getInfo().getTitle())
                        .version(openApiProperty.getInfo().getVersion())
                        .description(openApiProperty.getInfo().getDescription())
                        .license(new License()
                                .name(openApiProperty.getInfo().getLicense().getName())
                                .url(openApiProperty.getInfo().getLicense().getUrl()))
                );
    }

}