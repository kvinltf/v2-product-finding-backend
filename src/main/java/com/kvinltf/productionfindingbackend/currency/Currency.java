package com.kvinltf.productionfindingbackend.currency;

import com.kvinltf.productionfindingbackend.core.BaseEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Entity representing a currency in the system.
 * Each currency has a name, code (ISO 4217), symbol, and number of decimal places.
 */
@Entity
@Table(name = "currencies", uniqueConstraints = {
    @UniqueConstraint(name = "uk_currency_code", columnNames = "code")
})
@Getter
@Setter
@ToString
public class Currency extends BaseEntity {

    /**
     * The full name of the currency (e.g., "US Dollar", "Euro")
     */
    @NotBlank(message = "Currency name is required")
    @Size(max = 100, message = "Currency name must be less than 100 characters")
    @Column(nullable = false)
    private String name;

    /**
     * The ISO 4217 currency code (e.g., "USD", "EUR")
     */
    @NotBlank(message = "Currency code is required")
    @Size(min = 3, max = 3, message = "Currency code must be exactly 3 characters")
    @Column(length = 3, nullable = false)
    private String code;

    /**
     * The currency symbol (e.g., "$", "€")
     */
    @Size(max = 5, message = "Currency symbol must be less than 5 characters")
    @Column(length = 5)
    private String symbol;

    /**
     * The number of decimal places used for this currency (e.g., 2 for USD, 0 for JPY)
     */
    @NotNull(message = "Number of decimal places is required")
    @Column(name = "decimal_places", nullable = false)
    private Integer decimalPlaces;
}
