package com.kvinltf.productionfindingbackend.product;

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
 * Integration tests for the {@link ProductRepository}.
 * These tests verify that the repository correctly interacts with the database.
 */
@DataJpaTest
@ActiveProfiles("test")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class ProductRepositoryTest extends TestContainersConfig {

    @Autowired
    private ProductRepository productRepository;

    @Test
    @DisplayName("Should save a product with all fields")
    void shouldSaveAProductWithAllFields() {
        // Given
        Product product = new Product();
        product.setName("Test Product");
        product.setBarcode("123456789012");
        product.setDescription("Test description");
        product.setBrand("Test Brand");
        product.setCategory("Test Category");

        // When
        Product savedProduct = productRepository.save(product);

        // Then
        assertThat(savedProduct.getId()).isNotNull();
        assertThat(savedProduct.getName()).isEqualTo("Test Product");
        assertThat(savedProduct.getBarcode()).isEqualTo("123456789012");
        assertThat(savedProduct.getDescription()).isEqualTo("Test description");
        assertThat(savedProduct.getBrand()).isEqualTo("Test Brand");
        assertThat(savedProduct.getCategory()).isEqualTo("Test Category");
    }

    @Test
    @DisplayName("Should save a product with only required fields")
    void shouldSaveAProductWithOnlyRequiredFields() {
        // Given
        Product product = new Product();
        product.setName("Test Product");
        product.setBarcode("123456789012");

        // When
        Product savedProduct = productRepository.save(product);

        // Then
        assertThat(savedProduct.getId()).isNotNull();
        assertThat(savedProduct.getName()).isEqualTo("Test Product");
        assertThat(savedProduct.getBarcode()).isEqualTo("123456789012");
        assertThat(savedProduct.getDescription()).isNull();
        assertThat(savedProduct.getBrand()).isNull();
        assertThat(savedProduct.getCategory()).isNull();
    }

    @Test
    @DisplayName("Should find a product by id")
    void shouldFindAProductById() {
        // Given
        Product product = new Product();
        product.setName("Test Product");
        product.setBarcode("123456789012");
        Product savedProduct = productRepository.save(product);

        // When
        Product foundProduct = productRepository.findById(savedProduct.getId()).orElse(null);

        // Then
        assertThat(foundProduct).isNotNull();
        assertThat(foundProduct.getId()).isEqualTo(savedProduct.getId());
        assertThat(foundProduct.getName()).isEqualTo("Test Product");
        assertThat(foundProduct.getBarcode()).isEqualTo("123456789012");
    }

    @Test
    @DisplayName("Should update a product")
    void shouldUpdateAProduct() {
        // Given
        Product product = new Product();
        product.setName("Test Product");
        product.setBarcode("123456789012");
        Product savedProduct = productRepository.save(product);

        // When
        savedProduct.setName("Updated Product");
        savedProduct.setDescription("Added description");
        savedProduct.setBrand("Added Brand");
        Product updatedProduct = productRepository.save(savedProduct);

        // Then
        assertThat(updatedProduct.getId()).isEqualTo(savedProduct.getId());
        assertThat(updatedProduct.getName()).isEqualTo("Updated Product");
        assertThat(updatedProduct.getBarcode()).isEqualTo("123456789012");
        assertThat(updatedProduct.getDescription()).isEqualTo("Added description");
        assertThat(updatedProduct.getBrand()).isEqualTo("Added Brand");
    }

    @Test
    @DisplayName("Should delete a product")
    void shouldDeleteAProduct() {
        // Given
        Product product = new Product();
        product.setName("Test Product");
        product.setBarcode("123456789012");
        Product savedProduct = productRepository.save(product);

        // When
        productRepository.delete(savedProduct);

        // Then
        assertThat(productRepository.findById(savedProduct.getId())).isEmpty();
    }

    @Test
    @DisplayName("Should throw exception when saving product with null name")
    void shouldThrowExceptionWhenSavingProductWithNullName() {
        // Given
        Product product = new Product();
        product.setBarcode("123456789012");

        // When/Then
        assertThatThrownBy(() -> productRepository.save(product))
                .isInstanceOf(ConstraintViolationException.class)
                .hasMessageContaining("Product name is required");
    }

    @Test
    @DisplayName("Should throw exception when saving product with null barcode")
    void shouldThrowExceptionWhenSavingProductWithNullBarcode() {
        // Given
        Product product = new Product();
        product.setName("Test Product");

        // When/Then
        assertThatThrownBy(() -> productRepository.save(product))
                .isInstanceOf(ConstraintViolationException.class)
                .hasMessageContaining("Product barcode is required");
    }

    @Test
    @DisplayName("Should find all products")
    void shouldFindAllProducts() {
        // Given
        Product product1 = new Product();
        product1.setName("Test Product 1");
        product1.setBarcode("123456789012");
        productRepository.save(product1);

        Product product2 = new Product();
        product2.setName("Test Product 2");
        product2.setBarcode("987654321098");
        productRepository.save(product2);

        // When
        Iterable<Product> products = productRepository.findAll();

        // Then
        assertThat(products).hasSize(2);
        assertThat(products).extracting(Product::getName).containsExactlyInAnyOrder("Test Product 1", "Test Product 2");
    }

    @Test
    @DisplayName("Should count products")
    void shouldCountProducts() {
        // Given
        Product product1 = new Product();
        product1.setName("Test Product 1");
        product1.setBarcode("123456789012");
        productRepository.save(product1);

        Product product2 = new Product();
        product2.setName("Test Product 2");
        product2.setBarcode("987654321098");
        productRepository.save(product2);

        // When
        long count = productRepository.count();

        // Then
        assertThat(count).isEqualTo(2);
    }

    @Test
    @DisplayName("Should check if product exists by id")
    void shouldCheckIfProductExistsById() {
        // Given
        Product product = new Product();
        product.setName("Test Product");
        product.setBarcode("123456789012");
        Product savedProduct = productRepository.save(product);

        // When
        boolean exists = productRepository.existsById(savedProduct.getId());
        boolean nonExists = productRepository.existsById(999L);

        // Then
        assertThat(exists).isTrue();
        assertThat(nonExists).isFalse();
    }
}