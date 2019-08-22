package com.wangyb.sell.service;

import com.wangyb.sell.dataObject.ProductInfo;
import com.wangyb.sell.enums.ProductSattusEnum;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class ProductServiceTest {

    @Autowired
    private ProductService productService;

    @Test
    public void onSale() {
        ProductInfo productInfo = productService.onSale("1234567");
//        Assert.assertEquals(ProductSattusEnum.UP.getCode(),productInfo.getProductStatus());
    }

    @Test
    public void offSale() {
        ProductInfo result = productService.offSale("1234567");
        Assert.assertEquals(ProductSattusEnum.DOWN.getCode(),result.getProductStatus());
    }
}