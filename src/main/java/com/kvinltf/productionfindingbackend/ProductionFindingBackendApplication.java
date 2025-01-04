package com.kvinltf.productionfindingbackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class ProductionFindingBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProductionFindingBackendApplication.class, args);
    }

}
