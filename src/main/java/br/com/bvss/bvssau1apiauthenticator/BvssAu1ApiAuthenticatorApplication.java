package br.com.bvss.bvssau1apiauthenticator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableDiscoveryClient
@EnableConfigurationProperties
@SpringBootApplication
@EnableJpaAuditing(auditorAwareRef = "auditorAware")
public class BvssAu1ApiAuthenticatorApplication {

    public static void main(String[] args) {
        SpringApplication.run(BvssAu1ApiAuthenticatorApplication.class, args);
    }

}
