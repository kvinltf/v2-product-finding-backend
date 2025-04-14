package com.kvinltf.productionfindingbackend.product;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Unit tests for the {@link Product} entity.
 */
class ProductTest {

    private Validator validator;

    @BeforeEach
    void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    @DisplayName("Should validate when all required fields are provided")
    void shouldValidateWhenAllRequiredFieldsAreProvided() {
        // Given
        Product product = new Product();
        product.setName("Test Product");
        product.setBarcode("123456789012");

        // When
        Set<ConstraintViolation<Product>> violations = validator.validate(product);

        // Then
        assertThat(violations).isEmpty();
    }

    @Test
    @DisplayName("Should fail validation when name is null")
    void shouldFailValidationWhenNameIsNull() {
        // Given
        Product product = new Product();
        product.setBarcode("123456789012");

        // When
        Set<ConstraintViolation<Product>> violations = validator.validate(product);

        // Then
        assertThat(violations).hasSize(1);
        assertThat(violations.iterator().next().getMessage()).isEqualTo("Product name is required");
    }

    @Test
    @DisplayName("Should fail validation when name is empty")
    void shouldFailValidationWhenNameIsEmpty() {
        // Given
        Product product = new Product();
        product.setName("");
        product.setBarcode("123456789012");

        // When
        Set<ConstraintViolation<Product>> violations = validator.validate(product);

        // Then
        assertThat(violations).hasSize(1);
        assertThat(violations.iterator().next().getMessage()).isEqualTo("Product name is required");
    }

    @Test
    @DisplayName("Should fail validation when name is too long")
    void shouldFailValidationWhenNameIsTooLong() {
        // Given
        Product product = new Product();
        product.setName("A".repeat(256)); // 256 characters
        product.setBarcode("123456789012");

        // When
        Set<ConstraintViolation<Product>> violations = validator.validate(product);

        // Then
        assertThat(violations).hasSize(1);
        assertThat(violations.iterator().next().getMessage()).isEqualTo("Product name must be less than 255 characters");
    }

    @Test
    @DisplayName("Should allow description to be null")
    void shouldAllowDescriptionToBeNull() {
        // Given
        Product product = new Product();
        product.setName("Test Product");
        product.setBarcode("123456789012");
        // description is null

        // When
        Set<ConstraintViolation<Product>> violations = validator.validate(product);

        // Then
        assertThat(violations).isEmpty();
    }


    @Test
    @DisplayName("Should allow brand to be null")
    void shouldAllowBrandToBeNull() {
        // Given
        Product product = new Product();
        product.setName("Test Product");
        product.setBarcode("123456789012");
        // brand is null

        // When
        Set<ConstraintViolation<Product>> violations = validator.validate(product);

        // Then
        assertThat(violations).isEmpty();
    }


    @Test
    @DisplayName("Should allow category to be null")
    void shouldAllowCategoryToBeNull() {
        // Given
        Product product = new Product();
        product.setName("Test Product");
        product.setBarcode("123456789012");
        // category is null

        // When
        Set<ConstraintViolation<Product>> violations = validator.validate(product);

        // Then
        assertThat(violations).isEmpty();
    }


}