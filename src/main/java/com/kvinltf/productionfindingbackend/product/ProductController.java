package com.kvinltf.productionfindingbackend.product;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.PagedModel;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

/**
 * REST controller for managing products
 */
@RestController
@RequestMapping("/products")
@AllArgsConstructor
public class ProductController {
    private final ProductRepository productRepository;

    /**
     * Creates a new product
     * 
     * @param request The request containing the product details
     * @return The created product
     */
    @PostMapping
    public Product createProduct(@Valid @RequestBody ProductCreateRequest request) {
        Product product = new Product();
        product.setName(request.name());
        product.setBarcode(request.barcode());
        product.setDescription(request.description());
        product.setBrand(request.brand());
        product.setCategory(request.category());
        product.setSku(request.sku());
        product.setWeight(request.weight());
        product.setDimensions(request.dimensions());
        product.setActive(request.active() != null ? request.active() : true);
        return productRepository.save(product);
    }

    /**
     * Retrieves all products with pagination
     * 
     * @param pageable Pagination information
     * @return A page of products
     */
    @GetMapping
    public PagedModel<Product> getAllProducts(
            @PageableDefault(size = 20, direction = Sort.Direction.DESC)
            Pageable pageable
    ) {
        Page<Product> all = productRepository.findAll(pageable);
        return new PagedModel<>(all);
    }

    /**
     * Retrieves a product by its ID
     * 
     * @param id The ID of the product
     * @return The product, if found
     */
    @GetMapping("/{id}")
    public Optional<Product> getProductById(@PathVariable Long id) {
        return productRepository.findById(id);
    }

    /**
     * Updates an existing product
     * 
     * @param id The ID of the product to update
     * @param request The request containing the updated product details
     * @return The updated product
     */
    @PutMapping("/{id}")
    public Product updateProduct(@PathVariable Long id, @Valid @RequestBody ProductUpdateRequest request) {
        Product product = productRepository.findById(id).orElseThrow();
        product.setName(request.name());
        product.setBarcode(request.barcode());
        product.setDescription(request.description());
        product.setBrand(request.brand());
        product.setCategory(request.category());
        product.setSku(request.sku());
        product.setWeight(request.weight());
        product.setDimensions(request.dimensions());
        product.setActive(request.active());
        return productRepository.save(product);
    }

    /**
     * Deletes a product
     * 
     * @param id The ID of the product to delete
     */
    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable Long id) {
        productRepository.deleteById(id);
    }
}