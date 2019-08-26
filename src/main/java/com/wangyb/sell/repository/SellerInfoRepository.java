package com.wangyb.sell.repository;

import com.wangyb.sell.dataObject.SellerInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SellerInfoRepository extends JpaRepository<SellerInfo,String> {

    SellerInfo findByOpenid(String openid);
}
