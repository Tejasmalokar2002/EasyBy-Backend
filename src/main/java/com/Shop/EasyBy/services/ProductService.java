package com.Shop.EasyBy.services;

import com.Shop.EasyBy.dto.ProductDto;
import com.Shop.EasyBy.entities.Product;

import java.util.List;
import java.util.UUID;

public interface ProductService {

    public Product addProduct(ProductDto product);
    public List<Product> getAllProducts();


    List<ProductDto> getAllProducts(UUID categoryId, UUID typeId);

    ProductDto getProductBySlug(String slug);

    ProductDto getProductById(UUID id);

    Product updateProduct(ProductDto productDto, UUID id);

    Product fetchProductById(UUID uuid) throws Exception;
}
