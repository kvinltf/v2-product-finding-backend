package com.kvinltf.productionfindingbackend.currency;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.io.Serializable;

/**
 * DTO for creating a new {@link Currency}
 * Contains the fields required to create a new currency.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public record CurrencyCreateRequest(
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
