package com.kvinltf.productionfindingbackend.product;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.io.Serializable;

/**
 * DTO for creating a new {@link Product}
 * Contains the fields required to create a new product.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public record ProductCreateRequest(
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
    String category,

    @Size(max = 50, message = "Product SKU must be less than 50 characters")
    String sku,

    Double weight,

    @Size(max = 50, message = "Product dimensions must be less than 50 characters")
    String dimensions,

    Boolean active
) implements Serializable {
}