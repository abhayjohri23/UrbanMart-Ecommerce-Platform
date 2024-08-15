package com.springDataJPA.Assignment3.Repositories;

import com.springDataJPA.Assignment3.ModelsOrEntities.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository("productRepo")
public interface ProductRepository extends JpaRepository<ProductEntity, Long> {
    Optional<ProductEntity> findById(Long productID);

}
