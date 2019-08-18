package com.wangyb.sell.service.impl;

import com.wangyb.sell.dto.OrderDTO;
import com.wangyb.sell.enums.ResultEnum;
import com.wangyb.sell.exception.SellException;
import com.wangyb.sell.service.BuyerService;
import com.wangyb.sell.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.criterion.Order;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class BuyerServiceImpl implements BuyerService {
    @Autowired
    private OrderService orderService;

    Logger log = LoggerFactory.getLogger(BuyerServiceImpl.class);

    @Override
    public OrderDTO findOrderOne(String openid, String orderId) {
//        OrderDTO orderDTO = orderService.findOne(orderId);
//        if(orderDTO == null) {
//            return null;
//        }
//        if (!orderDTO.getBuyerOpenid().equalsIgnoreCase(openid)) {
//            log.error("【查询订单】订单的openid不一致,openid={},orderDTO={}", openid, orderDTO);
//            throw new SellException(ResultEnum.ORDER_OWNER_ERROR);
//        }
        return checkOrderOwmer(openid,orderId);
//        return orderDTO;
    }

    @Override
    public OrderDTO cancelOrderOne(String openid, String orderId) {
        OrderDTO orderDTO = checkOrderOwmer(openid,orderId);
        if(orderDTO == null) {
            log.error("【取消订单】查不到该订单,orderId={}",orderId);
            throw new SellException(ResultEnum.ORDER_NOT_EXIST);
        }
        return orderService.cancel(orderDTO);
    }

    public OrderDTO checkOrderOwmer(String openid,String orderId) {
        OrderDTO orderDTO = orderService.findOne(orderId);
        if(orderDTO == null) {
            return null;
        }
        if (!orderDTO.getBuyerOpenid().equalsIgnoreCase(openid)) {
            log.error("【查询订单】订单的openid不一致,openid={},orderDTO={}", openid, orderDTO);
            throw new SellException(ResultEnum.ORDER_OWNER_ERROR);
        }
        return orderDTO;
    }
}
