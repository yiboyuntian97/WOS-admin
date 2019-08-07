package com.wangyb.sell.repository;

import com.wangyb.sell.dataObject.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductCategoryRepository extends JpaRepository<ProductCategory,Integer> {
}
