package com.codewithzenas.store.controllers;

import com.codewithzenas.store.dtos.ProductDto;
import com.codewithzenas.store.mappers.ProductMapper;
import com.codewithzenas.store.repositories.CategoryRepository;
import com.codewithzenas.store.repositories.ProductRepository;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Objects;
import java.util.Set;

@RestController
@AllArgsConstructor
@RequestMapping("/products")
public class ProductController {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;
    private final CategoryRepository categoryRepository;

    @GetMapping
    public Iterable<ProductDto> getAllProducts(
            @RequestParam(required = false,
            defaultValue = "", name = "sort") String sort,
            @RequestParam(required = false,
                    defaultValue = "", name = "categoryId") Byte categoryId) {
        if (!Set.of("name", "price").contains(sort))
            sort = "name";
        var productStream = productRepository.findAll(Sort.by(sort)).stream()
                .map(productMapper::toProductDto);

        if (categoryId == null)
            return productStream.toList();
        return productStream.filter(productDto ->
                Objects.equals(productDto.getCategoryId(), categoryId)).toList();
    }
    @GetMapping("/{id}")
    public ResponseEntity<ProductDto> getProduct(@PathVariable Long id) {
        var product = productRepository.findById(id).orElse(null);
        if (product == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(productMapper.toProductDto(product));
    }

    @PostMapping
    public ResponseEntity<ProductDto> addProduct(
            @Valid @RequestBody ProductDto productDto,
            UriComponentsBuilder uriBuilder) {
        var product = productMapper.toProduct(productDto);
        var category = categoryRepository.findById(productDto
                .getCategoryId()).orElse(null);
        if (category == null)
            return ResponseEntity.badRequest().build();
        product.setCategory(category);
        productRepository.save(product);
        productDto.setId(product.getId());
        var uri = uriBuilder.path("/products/{id}").buildAndExpand(product.getId()).toUri();
        return ResponseEntity.created(uri).body(productDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductDto> updateUser(@PathVariable Long id,
                    @RequestBody ProductDto productDto) {
        var product = productRepository.findById(id).orElse(null);
        if (product == null) {
            return ResponseEntity.notFound().build();
        }
        if (!Objects.equals(productDto.getCategoryId(), product.getCategory().getId())) {
            var category = categoryRepository.findById(productDto
                    .getCategoryId()).orElse(null);
            if (category == null)
                return ResponseEntity.badRequest().build();
            product.setCategory(category);
        }
        productMapper.updateProduct(productDto, product);
        productRepository.save(product);
        productDto.setId(product.getId());
        return ResponseEntity.ok(productDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        var product = productRepository.findById(id).orElse(null);
        if (product == null)
            return ResponseEntity.notFound().build();
        productRepository.delete(product);
        return ResponseEntity.noContent().build();
    }

}
