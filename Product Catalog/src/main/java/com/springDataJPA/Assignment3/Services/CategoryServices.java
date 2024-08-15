package com.springDataJPA.Assignment3.Services;

import com.springDataJPA.Assignment3.DTOs.CategoryDTO;
import com.springDataJPA.Assignment3.ModelsOrEntities.CategoryEntity;
import com.springDataJPA.Assignment3.Repositories.CategoryRepository;
import com.springDataJPA.Assignment3.UserDefinedExceptions.DataPersistenceException;
import com.springDataJPA.Assignment3.UserDefinedExceptions.NoRecordFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service("categoryServices")
public class CategoryServices {
    private final CategoryRepository categoryRepository;
    public CategoryServices(CategoryRepository categoryRepository){
        this.categoryRepository = categoryRepository;
    }
    public CategoryDTO getCategoryByID(Long categoryID) throws NoRecordFoundException {
        if(!this.categoryRepository.existsById(categoryID))  throw new NoRecordFoundException("No Record found with the given id: "+categoryID);
        Optional<CategoryEntity> optionalCategoryEntity = this.categoryRepository.findById(categoryID);

        if(optionalCategoryEntity.isEmpty())    throw new NoRecordFoundException("No category found in the database!");
        CategoryEntity categoryEntity = optionalCategoryEntity.get();

        return this.getCategoryDTOFromCategoryEntity(categoryEntity);
    }

    public List<CategoryDTO> getAllCategories(){
        List<CategoryDTO> listOfCategoryDTOs = new ArrayList<>();
        List<CategoryEntity> categoryEntityList = this.categoryRepository.findAll();

        for(CategoryEntity categoryEntity:categoryEntityList){
            listOfCategoryDTOs.add(this.getCategoryDTOFromCategoryEntity(categoryEntity));
        }

        return listOfCategoryDTOs;
    }

    public CategoryDTO addCategory(CategoryDTO categoryDTO) throws DataPersistenceException {
        if(categoryDTO == null)     throw new DataPersistenceException("Data persistence issues with category", HttpStatus.BAD_REQUEST);
        this.categoryRepository.save(this.getCategoryEntityFromCategoryDTO(categoryDTO));
        return categoryDTO;
    }

    private CategoryEntity getCategoryEntityFromCategoryDTO(CategoryDTO categoryDTO,Long categoryID){
        CategoryEntity category = CategoryEntity.builder()
                .categoryName(categoryDTO.getCategoryName())
                .listOfProducts(null)
                .build();
        category.setId(categoryID);
        return category;
    }

    private CategoryEntity getCategoryEntityFromCategoryDTO(CategoryDTO categoryDTO){
        return CategoryEntity.builder()
                .categoryName(categoryDTO.getCategoryName())
                .listOfProducts(null)
                .build();
    }

    private CategoryDTO getCategoryDTOFromCategoryEntity(CategoryEntity categoryEntity){
        return CategoryDTO.builder()
                .categoryName(categoryEntity.getCategoryName())
                .build();
    }
}
