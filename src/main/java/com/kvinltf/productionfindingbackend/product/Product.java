package com.kvinltf.productionfindingbackend.product;

import com.kvinltf.productionfindingbackend.core.BaseEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Entity representing a product in the system.
 * Each product has a name, barcode, description, brand, category, and other related fields.
 */
@Entity
@Table(name = "products", uniqueConstraints = {
    @UniqueConstraint(name = "uk_product_barcode", columnNames = "barcode")
})
@Getter
@Setter
@ToString
public class Product extends BaseEntity {

    /**
     * The name of the product
     */
    @NotBlank(message = "Product name is required")
    @Size(max = 255, message = "Product name must be less than 255 characters")
    @Column(nullable = false)
    private String name;

    /**
     * The barcode of the product (e.g., UPC, EAN)
     */
    @NotBlank(message = "Product barcode is required")
    @Size(max = 50, message = "Product barcode must be less than 50 characters")
    @Column(nullable = false)
    private String barcode;

    /**
     * The description of the product
     */
    @Size(max = 1000, message = "Product description must be less than 1000 characters")
    @Column(length = 1000)
    private String description;

    /**
     * The brand of the product
     */
    @Size(max = 100, message = "Product brand must be less than 100 characters")
    @Column(length = 100)
    private String brand;

    /**
     * The category of the product
     */
    @Size(max = 100, message = "Product category must be less than 100 characters")
    @Column(length = 100)
    private String category;

    /**
     * The SKU (Stock Keeping Unit) of the product
     */
    @Size(max = 50, message = "Product SKU must be less than 50 characters")
    @Column(length = 50)
    private String sku;

    /**
     * The weight of the product (in grams)
     */
    @Column
    private Double weight;

    /**
     * The dimensions of the product (length x width x height in cm)
     */
    @Size(max = 50, message = "Product dimensions must be less than 50 characters")
    @Column(length = 50)
    private String dimensions;

    /**
     * Whether the product is active
     */
    @Column(nullable = false)
    private Boolean active = true;
}