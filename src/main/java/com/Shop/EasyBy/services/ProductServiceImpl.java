package com.Shop.EasyBy.services;

import com.Shop.EasyBy.dto.ProductDto;
import com.Shop.EasyBy.entities.Product;
import com.Shop.EasyBy.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public Product addProduct(ProductDto product){

       return null;
    }

    private Product createProduct(ProductDto productDto){
        Product product = new Product();
        product.setName(productDto.getName());
        product.setDescription(productDto.getDescription());
        product.setBrand(productDto.getBrand());
        product.setNewArrival(productDto.isNewArrival());
        product.setPrice(productDto.getPrice());
        return product;
    }

    @Override
    public List<Product> getAllProducts(){
        List<Product> products = productRepository.findAll();
        return products;
    }
}
