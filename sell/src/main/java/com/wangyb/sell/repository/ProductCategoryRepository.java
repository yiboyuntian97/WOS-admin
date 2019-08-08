package com.wangyb.sell.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wangyb.sell.dataObject.ProductCategory;

public interface ProductCategoryRepository extends JpaRepository<ProductCategory, Integer> {
	List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypeList);

	ProductCategory findOne(Integer categoryId);

}
