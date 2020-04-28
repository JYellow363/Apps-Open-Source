package com.example.dto.converter;

import javax.annotation.PostConstruct;

import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.dto.CreateProductDto;
import com.example.dto.EditProductDto;
import com.example.dto.ProductDto;
import com.example.model.Product;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class ProductDtoConverter {
	
	@Autowired
    private ModelMapper modelMapper;

    //mapAutomatic
    public ProductDto convertToDto(Product product) {
        return modelMapper.map(product, ProductDto.class);
    }

    public Product convertToEntity(CreateProductDto productDto) {
        return modelMapper.map(productDto, Product.class);

    }

    public Product convertToEntity(EditProductDto productDto) {
        return modelMapper.map(productDto, Product.class);
    }
    
    //mapExplicitly
    @PostConstruct
    public void init(){
        modelMapper.addMappings(new PropertyMap<Product, ProductDto>() {
            @Override
            protected void configure() {
                map().setCategoryName(source.getCategory().getName());
            }
        });
    }

}