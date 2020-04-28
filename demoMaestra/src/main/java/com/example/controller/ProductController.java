package com.example.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.dto.CreateProductDto;
import com.example.dto.EditProductDto;
import com.example.dto.ProductDto;
import com.example.service.ProductService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class ProductController {

  @Autowired
  private ProductService productService;

    @GetMapping("/products")
    public ResponseEntity<?> getAll() {
        List<ProductDto> products = productService.findAll();

        if (products.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(products);
        }
    }

    @GetMapping("/products/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        Optional<ProductDto> result = productService.findById(id);
        if (!result.isPresent()){
            return ResponseEntity.notFound().build();
        }
        else {
            return ResponseEntity.ok(result);
        }
    }


    @PostMapping("/products")
    public ResponseEntity<?> newProduct (@RequestBody CreateProductDto productDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(productService.save(productDto));
    }

    @PutMapping("/products/{id}")
    public ResponseEntity<?> updateProduct(@RequestBody EditProductDto productDto, @PathVariable Long id) {

        return ResponseEntity.status(HttpStatus.OK).body(productService.edit(productDto,id));
    }

   @DeleteMapping("/products/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable Long id) {
        productService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}










