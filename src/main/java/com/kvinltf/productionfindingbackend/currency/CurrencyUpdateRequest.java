package com.kvinltf.productionfindingbackend.currency;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.io.Serializable;

/**
 * DTO for updating an existing {@link Currency}
 * Contains the fields that can be updated for an existing currency.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public record CurrencyUpdateRequest(
    @NotBlank(message = "Currency name is required")
    @Size(max = 100, message = "Currency name must be less than 100 characters")
    String name,

    @NotBlank(message = "Currency code is required")
    @Size(min = 3, max = 3, message = "Currency code must be exactly 3 characters")
    String code,

    String symbol,

    @NotNull(message = "Number of decimal places is required")
    Integer decimalPlaces
) implements Serializable {
}
