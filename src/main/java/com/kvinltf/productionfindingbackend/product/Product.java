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
@Table(name = "products")
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
    @Column
    private String barcode;

    /**
     * The description of the product
     */
    @Column(columnDefinition = "text")
    private String description;

    /**
     * The brand of the product
     */
    @Column
    private String brand;

    /**
     * The category of the product
     */
    @Column
    private String category;
}