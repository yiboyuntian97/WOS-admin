package com.wangyb.sell.repository;

import com.wangyb.sell.dataObject.ProductInfo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductInfoRepositoryTest {
    @Autowired
    private ProductInfoRepository repository;

    @Test
    public void save() {
        ProductInfo productInfo = new ProductInfo();
        productInfo.setProductId("12345678");
        productInfo.setProductName("鲤鱼");
        productInfo.setCategoryType(3);
        productInfo.setProductStock(200);
        productInfo.setProductDescription("海鲜类");
        productInfo.setProductIcon("http://www.yyy.com");
        productInfo.setProductStatus(0);
        productInfo.setProductPrice(new BigDecimal(105));
        repository.save(productInfo);
    }
}