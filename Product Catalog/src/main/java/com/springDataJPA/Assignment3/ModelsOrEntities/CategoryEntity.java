package com.springDataJPA.Assignment3.ModelsOrEntities;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CategoryEntity extends BaseEntity {
    private String categoryName;
    @OneToMany(mappedBy = "category")
    private List<ProductEntity> listOfProducts;
}
