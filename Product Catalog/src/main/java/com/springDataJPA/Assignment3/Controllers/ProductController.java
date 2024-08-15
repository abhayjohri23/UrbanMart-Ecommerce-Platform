package com.springDataJPA.Assignment3.Controllers;

import com.springDataJPA.Assignment3.DTOs.ProductDTO;
import com.springDataJPA.Assignment3.Services.ProductServiceImpl;
import com.springDataJPA.Assignment3.UserDefinedExceptions.DataPersistenceException;
import com.springDataJPA.Assignment3.UserDefinedExceptions.NoRecordFoundException;
import com.springDataJPA.Assignment3.UserDefinedExceptions.UserDefinedProductExceptions;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
    private ProductServiceImpl productService;
    public ProductController(ProductServiceImpl productService){
        this.productService = productService;
    }

    @GetMapping("/{id}")
    public ProductDTO getProductById(@PathVariable("id") Long productID) throws NoRecordFoundException {
        return this.productService.getProductById(productID);
    }
    @GetMapping("/all")
    public List<ProductDTO> getAllProducts() throws NoRecordFoundException {
        return this.productService.getAllProducts();
    }
    @PostMapping("/add/")
    public ProductDTO addProduct(@RequestBody ProductDTO productDTO) throws DataPersistenceException {
        return this.productService.addProduct(productDTO);
    }

    @PostMapping("/update/{id}")
    public ProductDTO updateProduct(@RequestBody ProductDTO productDTO, @PathVariable("id") Long productID) throws UserDefinedProductExceptions{
        return this.productService.updateProduct(productDTO,productID);
    }

    @DeleteMapping("/delete/{id}")
    public ProductDTO deleteProduct(@PathVariable("id") Long productID) throws NoRecordFoundException {
        return this.productService.deleteProductByID(productID);
    }

    @ExceptionHandler
    private ResponseEntity<String> productExceptionHandler(UserDefinedProductExceptions exception){
        return new ResponseEntity(exception.getMessage(),exception.getStatusCode());
    }

}
