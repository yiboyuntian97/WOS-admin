package com.wangyb.sell.repository;

import com.wangyb.sell.dataObject.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductCategoryRepository extends JpaRepository<ProductCategory,Integer> {
    List<ProductCategory> findByCaAndCategoryTypeIn(List<Integer> categoryTypeList);
    ProductCategory findOne(Integer categoryId);

}
