package com.wangyb.sell.controller;

import com.wangyb.sell.dataObject.ProductCategory;
import com.wangyb.sell.dataObject.ProductInfo;
import com.wangyb.sell.service.CategoryService;
import com.wangyb.sell.service.ProductService;
import com.wangyb.sell.util.ResultVOUtil;
import com.wangyb.sell.vo.ProductInfoVO;
import com.wangyb.sell.vo.ProductVO;
import com.wangyb.sell.vo.ResultVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/buyer/product")
public class BuyerProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;

    @GetMapping("list")
    public ResultVO list() {
        // 1.查询所有上架商品
        List<ProductInfo> productInfoList = productService.findUpAll();
        // 2.查询类目（一次性查询）
//        List<Integer> categoryTypeList = new ArrayList<>();
        List<Integer> categoryTypeList = productInfoList.stream().map(e -> e.getCategoryType()).collect(Collectors.toList());
        List<ProductCategory> productCategoryList = categoryService.findByCategoryTypeIn(categoryTypeList);
        // 3.数据拼装
        List<ProductVO> productVOList = new ArrayList<>();
        for(ProductCategory category : productCategoryList) {
            ProductVO productVO = new ProductVO();
            productVO.setCategoryType(category.getCategoryType());
            productVO.setCategoryName(category.getCategoryName());
            List<ProductInfoVO> productInfoVOList = new ArrayList<>();
            for(ProductInfo productInfo : productInfoList) {
                if(productInfo.getCategoryType().equals(category.getCategoryType())) {
                    ProductInfoVO productInfoVO = new ProductInfoVO();
                    BeanUtils.copyProperties(productInfo, productInfoVO);
                    productInfoVOList.add(productInfoVO);
                }
            }
            productVO.setProductInfoVOList(productInfoVOList);
            productVOList.add(productVO);
        }
        return ResultVOUtil.success(productVOList);
    }
}
