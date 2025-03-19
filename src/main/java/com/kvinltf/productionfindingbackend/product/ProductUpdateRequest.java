package com.kvinltf.productionfindingbackend.product;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.io.Serializable;

/**
 * DTO for updating an existing {@link Product}
 * Contains the fields that can be updated for an existing product.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public record ProductUpdateRequest(
    @NotBlank(message = "Product name is required")
    @Size(max = 255, message = "Product name must be less than 255 characters")
    String name,

    @NotBlank(message = "Product barcode is required")
    @Size(max = 50, message = "Product barcode must be less than 50 characters")
    String barcode,

    @Size(max = 1000, message = "Product description must be less than 1000 characters")
    String description,

    @Size(max = 100, message = "Product brand must be less than 100 characters")
    String brand,

    @Size(max = 100, message = "Product category must be less than 100 characters")
    String category
) implements Serializable {
}