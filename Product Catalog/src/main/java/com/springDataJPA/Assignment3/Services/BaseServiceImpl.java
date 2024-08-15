package com.springDataJPA.Assignment3.Services;

import com.springDataJPA.Assignment3.DTOs.ProductDTO;
import com.springDataJPA.Assignment3.UserDefinedExceptions.DataPersistenceException;
import com.springDataJPA.Assignment3.UserDefinedExceptions.NoRecordFoundException;

import java.util.List;

public interface BaseServiceImpl {
    ProductDTO getProductById(Long productID) throws NoRecordFoundException;
    List<ProductDTO> getAllProducts() throws NoRecordFoundException;
    ProductDTO addProduct(ProductDTO productDTO) throws DataPersistenceException;
    ProductDTO updateProduct(ProductDTO productDTO,Long productID) throws DataPersistenceException;
    ProductDTO deleteProductByID(Long productId) throws NoRecordFoundException;
}
