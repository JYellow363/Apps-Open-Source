package com.example.service;

import java.util.List;
import java.util.Optional;

import com.example.dto.CreateProductDto;
import com.example.dto.EditProductDto;
import com.example.dto.ProductDto;
import com.example.model.Product;

public interface ProductService  {
    Product save(CreateProductDto productDto) ;
    Optional <ProductDto> findById(Long id) ;
    List<ProductDto> findAll() ;
    Product edit(EditProductDto productDTO, Long id);
    void deleteById(Long id) ;

}