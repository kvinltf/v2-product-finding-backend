package com.kvinltf.productionfindingbackend;

import com.kvinltf.productionfindingbackend.config.TestContainersConfig;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * Integration test for the application using Testcontainers for PostgreSQL.
 * This test verifies that the Spring application context loads successfully.
 * 
 * This test extends TestContainersConfig to use the shared PostgreSQL container.
 */
@SpringBootTest
class ProductionFindingBackendApplicationTests extends TestContainersConfig {

    @Test
    void contextLoads() {
        // This test verifies that the Spring application context loads successfully
        // with the PostgreSQL container provided by TestContainersConfig
        System.out.println("PostgreSQL container is running at: " + postgreSQLContainer.getJdbcUrl());
    }

}
