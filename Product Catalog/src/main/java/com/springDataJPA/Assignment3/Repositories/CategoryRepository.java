package com.springDataJPA.Assignment3.Repositories;

import com.springDataJPA.Assignment3.ModelsOrEntities.CategoryEntity;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository("categoryRepo")
public interface CategoryRepository extends JpaRepository<CategoryEntity, Long> {
    boolean existsByCategoryName(String categoryName);
    Optional<CategoryEntity> findByCategoryName(String categoryName);
}
