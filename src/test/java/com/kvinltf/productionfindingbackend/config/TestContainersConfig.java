package com.kvinltf.productionfindingbackend.config;

import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;

/**
 * Base configuration for tests that require a PostgreSQL database.
 * This class sets up a PostgreSQL container using Testcontainers.
 * 
 * This configuration uses a static PostgreSQL container that's started
 * before the Spring context is initialized, which avoids issues with
 * the Testcontainers JUnit Jupiter extension and Spring Boot integration.
 */
public abstract class TestContainersConfig {

    /**
     * Static PostgreSQL container that's shared among all test classes that extend this class.
     * The container is started once before any tests run and is stopped when the JVM exits.
     */
    protected static final PostgreSQLContainer<?> postgreSQLContainer;

    /**
     * Static initialization block to start the container.
     * This ensures the container is started before any tests run.
     */
    static {
        postgreSQLContainer = new PostgreSQLContainer<>("postgres:13")
                .withDatabaseName("testdb")
                .withUsername("test")
                .withPassword("test");
        postgreSQLContainer.start();
        System.out.println("PostgreSQL container started at: " + postgreSQLContainer.getJdbcUrl());
    }

    /**
     * Dynamically sets the database connection properties to use the PostgreSQL container.
     * This method is called by Spring to configure the application context for tests.
     *
     * @param registry the property registry to update with container connection details
     */
    @DynamicPropertySource
    static void registerPostgresqlProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", postgreSQLContainer::getJdbcUrl);
        registry.add("spring.datasource.username", postgreSQLContainer::getUsername);
        registry.add("spring.datasource.password", postgreSQLContainer::getPassword);
    }
}
