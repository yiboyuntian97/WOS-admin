package com.wangyb.sell.repository;

import com.wangyb.sell.dataObject.SellerInfo;
import com.wangyb.sell.util.KeyUtil;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class SellerInfoRepositoryTest {

    @Autowired
    private SellerInfoRepository repository;

    @Test
    public void save() throws Exception{
        SellerInfo sellerInfo = new SellerInfo();
        sellerInfo.setId(KeyUtil.genUniqueKey());
        sellerInfo.setUsername("admin");
        sellerInfo.setPassword("admin");
        sellerInfo.setOpenid("abc");
        SellerInfo info = repository.save(sellerInfo);
        Assert.assertNotNull(info);
    }

    @Test
    public void findByOpenid() throws Exception{
        SellerInfo sellerInfo = repository.findByOpenid("abc");
        Assert.assertEquals(sellerInfo.getOpenid(),"abc");
    }

}