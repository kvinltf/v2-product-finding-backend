package com.kvinltf.productionfindingbackend.currency;

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
 * Unit tests for the {@link Currency} entity.
 */
class CurrencyTest {

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
        Currency currency = new Currency();
        currency.setName("US Dollar");
        currency.setCode("USD");
        currency.setDecimalPlaces(2);

        // When
        Set<ConstraintViolation<Currency>> violations = validator.validate(currency);

        // Then
        assertThat(violations).isEmpty();
    }

    @Test
    @DisplayName("Should fail validation when name is null")
    void shouldFailValidationWhenNameIsNull() {
        // Given
        Currency currency = new Currency();
        currency.setCode("USD");
        currency.setDecimalPlaces(2);

        // When
        Set<ConstraintViolation<Currency>> violations = validator.validate(currency);

        // Then
        assertThat(violations).hasSize(1);
        assertThat(violations.iterator().next().getMessage()).isEqualTo("Currency name is required");
    }

    @Test
    @DisplayName("Should fail validation when name is empty")
    void shouldFailValidationWhenNameIsEmpty() {
        // Given
        Currency currency = new Currency();
        currency.setName("");
        currency.setCode("USD");
        currency.setDecimalPlaces(2);

        // When
        Set<ConstraintViolation<Currency>> violations = validator.validate(currency);

        // Then
        assertThat(violations).hasSize(1);
        assertThat(violations.iterator().next().getMessage()).isEqualTo("Currency name is required");
    }

    @Test
    @DisplayName("Should fail validation when name is too long")
    void shouldFailValidationWhenNameIsTooLong() {
        // Given
        Currency currency = new Currency();
        currency.setName("A".repeat(101)); // 101 characters
        currency.setCode("USD");
        currency.setDecimalPlaces(2);

        // When
        Set<ConstraintViolation<Currency>> violations = validator.validate(currency);

        // Then
        assertThat(violations).hasSize(1);
        assertThat(violations.iterator().next().getMessage()).isEqualTo("Currency name must be less than 100 characters");
    }

    @Test
    @DisplayName("Should fail validation when code is null")
    void shouldFailValidationWhenCodeIsNull() {
        // Given
        Currency currency = new Currency();
        currency.setName("US Dollar");
        currency.setDecimalPlaces(2);

        // When
        Set<ConstraintViolation<Currency>> violations = validator.validate(currency);

        // Then
        assertThat(violations).hasSize(1);
        assertThat(violations.iterator().next().getMessage()).isEqualTo("Currency code is required");
    }

    @Test
    @DisplayName("Should fail validation when code is empty")
    void shouldFailValidationWhenCodeIsEmpty() {
        // Given
        Currency currency = new Currency();
        currency.setName("US Dollar");
        currency.setCode("");
        currency.setDecimalPlaces(2);

        // When
        Set<ConstraintViolation<Currency>> violations = validator.validate(currency);

        // Then
        assertThat(violations).hasSize(2);
        assertThat(violations).extracting("message")
                .containsExactlyInAnyOrder(
                        "Currency code is required",
                        "Currency code must be exactly 3 characters"
                );
    }

    @Test
    @DisplayName("Should fail validation when code is not exactly 3 characters")
    void shouldFailValidationWhenCodeIsNotExactly3Characters() {
        // Given
        Currency currency = new Currency();
        currency.setName("US Dollar");
        currency.setCode("USDD"); // 4 characters
        currency.setDecimalPlaces(2);

        // When
        Set<ConstraintViolation<Currency>> violations = validator.validate(currency);

        // Then
        assertThat(violations).hasSize(1);
        assertThat(violations.iterator().next().getMessage()).isEqualTo("Currency code must be exactly 3 characters");
    }

    @Test
    @DisplayName("Should fail validation when decimalPlaces is null")
    void shouldFailValidationWhenDecimalPlacesIsNull() {
        // Given
        Currency currency = new Currency();
        currency.setName("US Dollar");
        currency.setCode("USD");

        // When
        Set<ConstraintViolation<Currency>> violations = validator.validate(currency);

        // Then
        assertThat(violations).hasSize(1);
        assertThat(violations.iterator().next().getMessage()).isEqualTo("Number of decimal places is required");
    }

    @Test
    @DisplayName("Should allow symbol to be null")
    void shouldAllowSymbolToBeNull() {
        // Given
        Currency currency = new Currency();
        currency.setName("US Dollar");
        currency.setCode("USD");
        currency.setDecimalPlaces(2);
        // symbol is null

        // When
        Set<ConstraintViolation<Currency>> violations = validator.validate(currency);

        // Then
        assertThat(violations).isEmpty();
    }

    @Test
    @DisplayName("Should fail validation when symbol is too long")
    void shouldFailValidationWhenSymbolIsTooLong() {
        // Given
        Currency currency = new Currency();
        currency.setName("US Dollar");
        currency.setCode("USD");
        currency.setDecimalPlaces(2);
        currency.setSymbol("$$$$$$$"); // 7 characters

        // When
        Set<ConstraintViolation<Currency>> violations = validator.validate(currency);

        // Then
        assertThat(violations).hasSize(1);
        assertThat(violations.iterator().next().getMessage()).isEqualTo("Currency symbol must be less than 5 characters");
    }
}
