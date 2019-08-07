package com.wangyb.sell.service;

import com.wangyb.sell.dataObject.ProductCategory;

import java.util.List;

/**
 * 商品类目服务层接口
 */
public interface CategoryService {

    ProductCategory findOne(Integer categoryId);

    List<ProductCategory> findAll();

    List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypeList);

    ProductCategory save(ProductCategory productCategory);
}
