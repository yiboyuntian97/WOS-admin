package com.wangyb.sell.service;

import com.wangyb.sell.dto.OrderDTO;

public interface BuyerService {

    // 查询一个订单
    OrderDTO findOrderOne(String openid,String orderId);
    // 取消订单
    OrderDTO cancelOrderOne(String openid,String orderId);

}
