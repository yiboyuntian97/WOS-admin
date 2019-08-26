package com.wangyb.sell.service.impl;

import com.wangyb.sell.dataObject.SellerInfo;
import com.wangyb.sell.service.SellerService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class SellerServiceImplTest {

    @Autowired
    private SellerServiceImpl sellerService;

    private static final String openid="abc";

    @Test
    public void findSellerInfoByOpenid() throws Exception{

        SellerInfo result = sellerService.findSellerInfoByOpenid(openid);
        Assert.assertEquals(openid,result.getOpenid());
    }
}