package com.example.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.dto.CreateProductDto;
import com.example.dto.EditProductDto;
import com.example.dto.ProductDto;
import com.example.dto.converter.ProductDtoConverter;
import com.example.model.Product;
import com.example.repository.ProductRepository;
import com.example.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductDtoConverter productDtoConverter;

    @Override
    public Product save(CreateProductDto productDTO) {
        Product product = productDtoConverter.convertToEntity(productDTO);
        return productRepository.save(product);
    }

    @Override
    public Optional<ProductDto> findById(Long id) {
        //Luego habrá manejo de exception
        return productRepository.findById(id).map(productDtoConverter::convertToDto);
    }
    
    @Override
    public List<ProductDto> findAll() {
        List<ProductDto> productsDto=productRepository.findAll().stream().map(productDtoConverter::convertToDto)
                .collect(Collectors.toList());
        return productsDto;
    }

    @Override
    public Product edit(EditProductDto productDto, Long id) {
        //Product product=productDTOConverter.convertToEntity(productDTO);
        return productRepository.findById(id).map(p -> {
            p.setName(productDto.getName());
            p.setPrice(productDto.getPrice());
            return productRepository.save(p);
        }).orElse(null);
        //return productRepository.save(product);
    }

    @Override
    public void deleteById(Long id) {
            productRepository.deleteById(id);
    }


}