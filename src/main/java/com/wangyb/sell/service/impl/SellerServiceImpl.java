package com.wangyb.sell.service.impl;

import com.wangyb.sell.dataObject.SellerInfo;
import com.wangyb.sell.repository.SellerInfoRepository;
import com.wangyb.sell.service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SellerServiceImpl implements SellerService {

    @Autowired
    private SellerInfoRepository repository;

    @Override
    public SellerInfo findSellerInfoByOpenid(String openid) {
        return repository.findByOpenid(openid);
    }
}
