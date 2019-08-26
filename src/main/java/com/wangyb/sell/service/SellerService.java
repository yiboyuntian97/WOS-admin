package com.wangyb.sell.service;

import com.wangyb.sell.dataObject.SellerInfo;

public interface SellerService {

    /**
     * 通过卖家openid查询买家端信息
     * @param openid
     * @return
     */
    SellerInfo findSellerInfoByOpenid(String openid);
}
