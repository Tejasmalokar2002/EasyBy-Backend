package com.Shop.EasyBy.services;

import com.Shop.EasyBy.dto.ProductDto;
import com.Shop.EasyBy.entities.Product;

import java.util.List;

public interface ProductService {

    public Product addProduct(ProductDto product);
    public List<Product> getAllProducts();
}
