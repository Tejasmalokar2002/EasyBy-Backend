package com.Shop.EasyBy.services;

import com.Shop.EasyBy.dto.CategoryDto;
import com.Shop.EasyBy.dto.CategoryTypeDto;
import com.Shop.EasyBy.entities.Category;
import com.Shop.EasyBy.entities.CategoryType;
import com.Shop.EasyBy.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public Category getCategory(UUID categoryId){
        Optional<Category> category = categoryRepository.findById(categoryId);
        return category.orElse(null);
    }

    public Category createCategory(CategoryDto categoryDto){

    }

    private Category mapToEntity(CategoryDto categoryDto){
        Category category = Category.builder().code(categoryDto.getCode())
                .name(categoryDto.getName()).description(categoryDto.getDescription()).categoryTypes(mapToCategoryTypes(categoryDto.getCategoryTypeList())).build();
    }
    private List<CategoryType> mapToCategoryTypes(List<CategoryTypeDto> categoryTypeList){
        return categoryTypeList.stream().map(categoryTypeDto -> mapToCategoryType(categoryTypeDto).collect(Collectors.toList()));
    }
}
