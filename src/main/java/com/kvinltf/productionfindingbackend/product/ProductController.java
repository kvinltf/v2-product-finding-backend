package com.kvinltf.productionfindingbackend.product;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.PagedModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

/**
 * REST controller for managing products
 */
@RestController
@RequestMapping("/products")
@AllArgsConstructor
public class ProductController {
    private final ProductRepository productRepository;

    @PostMapping
    public ResponseEntity<Product> createProduct(@Valid @RequestBody ProductCreateRequest request) {
        Product product = new Product();
        product.setName(request.name());
        product.setBarcode(request.barcode());
        product.setDescription(request.description());
        product.setBrand(request.brand());
        product.setCategory(request.category());
        Product save = productRepository.save(product);
//        ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(save.getId()).toUri();
        return ResponseEntity.created(
                URI.create("/products/%s".formatted(save.getId()))
        ).body(save);
    }

    @GetMapping
    public PagedModel<Product> getAllProducts(
            @PageableDefault(size = 20, direction = Sort.Direction.DESC)
            Pageable pageable
    ) {
        Page<Product> all = productRepository.findAll(pageable);
        return new PagedModel<>(all);
    }


    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id) {
        return productRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }


    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long id, @Valid @RequestBody ProductUpdateRequest request) {
        Product product = productRepository.findById(id).orElseThrow();
        product.setName(request.name());
        product.setBarcode(request.barcode());
        product.setDescription(request.description());
        product.setBrand(request.brand());
        product.setCategory(request.category());
        Product save = productRepository.save(product);
        return ResponseEntity.ok(save);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        productRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}