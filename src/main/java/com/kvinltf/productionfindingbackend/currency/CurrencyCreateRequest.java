package com.kvinltf.productionfindingbackend.currency;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;

/**
 * DTO for {@link Currency}
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public record CurrencyCreateRequest(String name, int decimal) implements Serializable {
}