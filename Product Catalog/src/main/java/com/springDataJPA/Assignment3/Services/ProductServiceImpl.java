package com.springDataJPA.Assignment3.Services;

import com.springDataJPA.Assignment3.DTOs.ProductDTO;
import com.springDataJPA.Assignment3.ModelsOrEntities.CategoryEntity;
import com.springDataJPA.Assignment3.ModelsOrEntities.ProductEntity;
import com.springDataJPA.Assignment3.Repositories.CategoryRepository;
import com.springDataJPA.Assignment3.Repositories.ProductRepository;
import com.springDataJPA.Assignment3.UserDefinedExceptions.DataPersistenceException;
import com.springDataJPA.Assignment3.UserDefinedExceptions.NoRecordFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service("productService")
public class ProductServiceImpl implements BaseServiceImpl {
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    public ProductServiceImpl(ProductRepository productRepository,CategoryRepository categoryRepository){
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }
    @Override
    public ProductDTO getProductById(Long productID) throws NoRecordFoundException {
        Optional<ProductEntity> optionalProduct = this.productRepository.findById(productID);

        if(optionalProduct.isEmpty())       throw new NoRecordFoundException("Record not found!");

        ProductEntity product = optionalProduct.get();
        return getProductDTOFromProductEntity(product);
    }

    @Override
    public List<ProductDTO> getAllProducts() throws NoRecordFoundException{
        List<ProductEntity> productList = this.productRepository.findAll();
        if(productList == null || productList.isEmpty())     throw new NoRecordFoundException("No Records found!");

        List<ProductDTO> productDTOS = new ArrayList<>();
        for(ProductEntity product:productList){
          productDTOS.add(getProductDTOFromProductEntity(product));
        }
        return productDTOS;
    }

    @Override
    public ProductDTO addProduct(ProductDTO productDTO) throws DataPersistenceException {
        if(productDTO == null)                      throw new DataPersistenceException("No product selected",HttpStatus.BAD_REQUEST);

        if(!this.categoryRepository.existsByCategoryName(productDTO.getCategory()))
            this.categoryRepository.save(new CategoryEntity(productDTO.getCategory(),null));

        Optional<CategoryEntity> optionalCategoryEntity = this.categoryRepository.findByCategoryName(productDTO.getCategory());

        if(optionalCategoryEntity.isEmpty())        throw new DataPersistenceException("Category issues!",HttpStatus.BAD_REQUEST);

        CategoryEntity categoryEntity = optionalCategoryEntity.get();
        ProductEntity product = getProductEntityFromProductDTO(productDTO,categoryEntity);
        ProductEntity insertedProduct = this.productRepository.save(product);
        return getProductDTOFromProductEntity(insertedProduct);
    }

    @Override
    public ProductDTO updateProduct(ProductDTO productDTO,Long productID) throws DataPersistenceException{
        if(productDTO == null)
            throw new DataPersistenceException("No product selected",HttpStatus.BAD_REQUEST);
        if(!this.productRepository.existsById(productID))
            throw new DataPersistenceException("No product exist with given ID",HttpStatus.BAD_REQUEST);


        if(!this.categoryRepository.existsByCategoryName(productDTO.getCategory()))
            this.categoryRepository.save(new CategoryEntity(productDTO.getCategory(),null));

        Optional<CategoryEntity> optionalCategoryEntity = this.categoryRepository.findByCategoryName(productDTO.getCategory());
        if(optionalCategoryEntity.isEmpty())        throw new DataPersistenceException("Category issues!",HttpStatus.BAD_REQUEST);

        CategoryEntity categoryEntity = optionalCategoryEntity.get();
        ProductEntity product = getProductEntityFromProductDTO(productDTO,categoryEntity);
        product.setId(productID);

        ProductEntity updatedProduct = this.productRepository.save(product);
        return getProductDTOFromProductEntity(updatedProduct);
    }

    @Override
    public ProductDTO deleteProductByID(Long productID) throws NoRecordFoundException{
        if(!this.productRepository.existsById(productID))
            throw new NoRecordFoundException("No product exist with given ID");
        Optional<ProductEntity> optionalProduct = this.productRepository.findById(productID);

        if(optionalProduct.isEmpty())       throw new NoRecordFoundException("No product exist with this ID");
        this.productRepository.deleteById(productID);
        return this.getProductDTOFromProductEntity(optionalProduct.get());
    }

    private ProductEntity getProductEntityFromProductDTO(ProductDTO productDTO, CategoryEntity category){
        return ProductEntity.builder()
                .title(productDTO.getTitle())
                .description(productDTO.getDescription())
                .Price(productDTO.getPrice())
                .imageURL(productDTO.getImageURL())
                .category(category)
                .build();
    }

    private ProductDTO getProductDTOFromProductEntity(ProductEntity product){
        return ProductDTO.builder()
                .title(product.getTitle())
                .category(product.getCategory().getCategoryName())
                .description(product.getDescription())
                .imageURL(product.getImageURL())
                .price(product.getPrice())
                .build();
    }
}
