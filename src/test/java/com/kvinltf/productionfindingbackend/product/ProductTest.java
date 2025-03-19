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
        product.setActive(true);

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
        product.setActive(true);

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
        product.setActive(true);

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
        product.setActive(true);

        // When
        Set<ConstraintViolation<Product>> violations = validator.validate(product);

        // Then
        assertThat(violations).hasSize(1);
        assertThat(violations.iterator().next().getMessage()).isEqualTo("Product name must be less than 255 characters");
    }

    @Test
    @DisplayName("Should fail validation when barcode is null")
    void shouldFailValidationWhenBarcodeIsNull() {
        // Given
        Product product = new Product();
        product.setName("Test Product");
        product.setActive(true);

        // When
        Set<ConstraintViolation<Product>> violations = validator.validate(product);

        // Then
        assertThat(violations).hasSize(1);
        assertThat(violations.iterator().next().getMessage()).isEqualTo("Product barcode is required");
    }

    @Test
    @DisplayName("Should fail validation when barcode is empty")
    void shouldFailValidationWhenBarcodeIsEmpty() {
        // Given
        Product product = new Product();
        product.setName("Test Product");
        product.setBarcode("");
        product.setActive(true);

        // When
        Set<ConstraintViolation<Product>> violations = validator.validate(product);

        // Then
        assertThat(violations).hasSize(1);
        assertThat(violations.iterator().next().getMessage()).isEqualTo("Product barcode is required");
    }

    @Test
    @DisplayName("Should fail validation when barcode is too long")
    void shouldFailValidationWhenBarcodeIsTooLong() {
        // Given
        Product product = new Product();
        product.setName("Test Product");
        product.setBarcode("A".repeat(51)); // 51 characters
        product.setActive(true);

        // When
        Set<ConstraintViolation<Product>> violations = validator.validate(product);

        // Then
        assertThat(violations).hasSize(1);
        assertThat(violations.iterator().next().getMessage()).isEqualTo("Product barcode must be less than 50 characters");
    }

    @Test
    @DisplayName("Should allow description to be null")
    void shouldAllowDescriptionToBeNull() {
        // Given
        Product product = new Product();
        product.setName("Test Product");
        product.setBarcode("123456789012");
        product.setActive(true);
        // description is null

        // When
        Set<ConstraintViolation<Product>> violations = validator.validate(product);

        // Then
        assertThat(violations).isEmpty();
    }

    @Test
    @DisplayName("Should fail validation when description is too long")
    void shouldFailValidationWhenDescriptionIsTooLong() {
        // Given
        Product product = new Product();
        product.setName("Test Product");
        product.setBarcode("123456789012");
        product.setDescription("A".repeat(1001)); // 1001 characters
        product.setActive(true);

        // When
        Set<ConstraintViolation<Product>> violations = validator.validate(product);

        // Then
        assertThat(violations).hasSize(1);
        assertThat(violations.iterator().next().getMessage()).isEqualTo("Product description must be less than 1000 characters");
    }

    @Test
    @DisplayName("Should allow brand to be null")
    void shouldAllowBrandToBeNull() {
        // Given
        Product product = new Product();
        product.setName("Test Product");
        product.setBarcode("123456789012");
        product.setActive(true);
        // brand is null

        // When
        Set<ConstraintViolation<Product>> violations = validator.validate(product);

        // Then
        assertThat(violations).isEmpty();
    }

    @Test
    @DisplayName("Should fail validation when brand is too long")
    void shouldFailValidationWhenBrandIsTooLong() {
        // Given
        Product product = new Product();
        product.setName("Test Product");
        product.setBarcode("123456789012");
        product.setBrand("A".repeat(101)); // 101 characters
        product.setActive(true);

        // When
        Set<ConstraintViolation<Product>> violations = validator.validate(product);

        // Then
        assertThat(violations).hasSize(1);
        assertThat(violations.iterator().next().getMessage()).isEqualTo("Product brand must be less than 100 characters");
    }

    @Test
    @DisplayName("Should allow category to be null")
    void shouldAllowCategoryToBeNull() {
        // Given
        Product product = new Product();
        product.setName("Test Product");
        product.setBarcode("123456789012");
        product.setActive(true);
        // category is null

        // When
        Set<ConstraintViolation<Product>> violations = validator.validate(product);

        // Then
        assertThat(violations).isEmpty();
    }

    @Test
    @DisplayName("Should fail validation when category is too long")
    void shouldFailValidationWhenCategoryIsTooLong() {
        // Given
        Product product = new Product();
        product.setName("Test Product");
        product.setBarcode("123456789012");
        product.setCategory("A".repeat(101)); // 101 characters
        product.setActive(true);

        // When
        Set<ConstraintViolation<Product>> violations = validator.validate(product);

        // Then
        assertThat(violations).hasSize(1);
        assertThat(violations.iterator().next().getMessage()).isEqualTo("Product category must be less than 100 characters");
    }
}