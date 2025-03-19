package com.kvinltf.productionfindingbackend.currency;

import com.kvinltf.productionfindingbackend.config.TestContainersConfig;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.ActiveProfiles;

import jakarta.validation.ConstraintViolationException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

/**
 * Integration tests for the {@link CurrencyRepository}.
 * These tests verify that the repository correctly interacts with the database.
 */
@DataJpaTest
@ActiveProfiles("test")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class CurrencyRepositoryTest extends TestContainersConfig {

    @Autowired
    private CurrencyRepository currencyRepository;

    @Test
    @DisplayName("Should save a currency with all fields")
    void shouldSaveACurrencyWithAllFields() {
        // Given
        Currency currency = new Currency();
        currency.setName("US Dollar");
        currency.setCode("USD");
        currency.setSymbol("$");
        currency.setDecimalPlaces(2);

        // When
        Currency savedCurrency = currencyRepository.save(currency);

        // Then
        assertThat(savedCurrency.getId()).isNotNull();
        assertThat(savedCurrency.getName()).isEqualTo("US Dollar");
        assertThat(savedCurrency.getCode()).isEqualTo("USD");
        assertThat(savedCurrency.getSymbol()).isEqualTo("$");
        assertThat(savedCurrency.getDecimalPlaces()).isEqualTo(2);
    }

    @Test
    @DisplayName("Should save a currency without symbol")
    void shouldSaveACurrencyWithoutSymbol() {
        // Given
        Currency currency = new Currency();
        currency.setName("Japanese Yen");
        currency.setCode("JPY");
        currency.setDecimalPlaces(0);

        // When
        Currency savedCurrency = currencyRepository.save(currency);

        // Then
        assertThat(savedCurrency.getId()).isNotNull();
        assertThat(savedCurrency.getName()).isEqualTo("Japanese Yen");
        assertThat(savedCurrency.getCode()).isEqualTo("JPY");
        assertThat(savedCurrency.getSymbol()).isNull();
        assertThat(savedCurrency.getDecimalPlaces()).isEqualTo(0);
    }

    @Test
    @DisplayName("Should find a currency by id")
    void shouldFindACurrencyById() {
        // Given
        Currency currency = new Currency();
        currency.setName("Euro");
        currency.setCode("EUR");
        currency.setSymbol("€");
        currency.setDecimalPlaces(2);
        Currency savedCurrency = currencyRepository.save(currency);

        // When
        Currency foundCurrency = currencyRepository.findById(savedCurrency.getId()).orElse(null);

        // Then
        assertThat(foundCurrency).isNotNull();
        assertThat(foundCurrency.getId()).isEqualTo(savedCurrency.getId());
        assertThat(foundCurrency.getName()).isEqualTo("Euro");
        assertThat(foundCurrency.getCode()).isEqualTo("EUR");
        assertThat(foundCurrency.getSymbol()).isEqualTo("€");
        assertThat(foundCurrency.getDecimalPlaces()).isEqualTo(2);
    }

    @Test
    @DisplayName("Should update a currency")
    void shouldUpdateACurrency() {
        // Given
        Currency currency = new Currency();
        currency.setName("British Pound");
        currency.setCode("GBP");
        currency.setSymbol("£");
        currency.setDecimalPlaces(2);
        Currency savedCurrency = currencyRepository.save(currency);

        // When
        savedCurrency.setName("British Pound Sterling");
        savedCurrency.setSymbol("GBP");
        Currency updatedCurrency = currencyRepository.save(savedCurrency);

        // Then
        assertThat(updatedCurrency.getId()).isEqualTo(savedCurrency.getId());
        assertThat(updatedCurrency.getName()).isEqualTo("British Pound Sterling");
        assertThat(updatedCurrency.getCode()).isEqualTo("GBP");
        assertThat(updatedCurrency.getSymbol()).isEqualTo("GBP");
        assertThat(updatedCurrency.getDecimalPlaces()).isEqualTo(2);
    }

    @Test
    @DisplayName("Should delete a currency")
    void shouldDeleteACurrency() {
        // Given
        Currency currency = new Currency();
        currency.setName("Swiss Franc");
        currency.setCode("CHF");
        currency.setSymbol("Fr");
        currency.setDecimalPlaces(2);
        Currency savedCurrency = currencyRepository.save(currency);

        // When
        currencyRepository.delete(savedCurrency);

        // Then
        assertThat(currencyRepository.findById(savedCurrency.getId())).isEmpty();
    }

    @Test
    @DisplayName("Should throw exception when saving currencies with duplicate code")
    void shouldThrowExceptionWhenSavingCurrenciesWithDuplicateCode() {
        // Given
        Currency currency1 = new Currency();
        currency1.setName("US Dollar");
        currency1.setCode("USD");
        currency1.setSymbol("$");
        currency1.setDecimalPlaces(2);
        currencyRepository.save(currency1);

        Currency currency2 = new Currency();
        currency2.setName("United States Dollar");
        currency2.setCode("USD"); // Same code as currency1
        currency2.setSymbol("USD");
        currency2.setDecimalPlaces(2);

        // When/Then
        assertThatThrownBy(() -> currencyRepository.save(currency2))
                .isInstanceOf(DataIntegrityViolationException.class);
    }

    @Test
    @DisplayName("Should throw exception when saving currency with null name")
    void shouldThrowExceptionWhenSavingCurrencyWithNullName() {
        // Given
        Currency currency = new Currency();
        currency.setCode("USD");
        currency.setDecimalPlaces(2);

        // When/Then
        assertThatThrownBy(() -> currencyRepository.save(currency))
                .isInstanceOf(ConstraintViolationException.class)
                .hasMessageContaining("Currency name is required");
    }

    @Test
    @DisplayName("Should throw exception when saving currency with null code")
    void shouldThrowExceptionWhenSavingCurrencyWithNullCode() {
        // Given
        Currency currency = new Currency();
        currency.setName("US Dollar");
        currency.setDecimalPlaces(2);

        // When/Then
        assertThatThrownBy(() -> currencyRepository.save(currency))
                .isInstanceOf(ConstraintViolationException.class)
                .hasMessageContaining("Currency code is required");
    }

    @Test
    @DisplayName("Should throw exception when saving currency with null decimalPlaces")
    void shouldThrowExceptionWhenSavingCurrencyWithNullDecimalPlaces() {
        // Given
        Currency currency = new Currency();
        currency.setName("US Dollar");
        currency.setCode("USD");

        // When/Then
        assertThatThrownBy(() -> currencyRepository.save(currency))
                .isInstanceOf(ConstraintViolationException.class)
                .hasMessageContaining("Number of decimal places is required");
    }
}
