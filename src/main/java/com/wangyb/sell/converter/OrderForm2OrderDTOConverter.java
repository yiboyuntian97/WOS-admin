package com.wangyb.sell.converter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.wangyb.sell.dataObject.OrderDetail;
import com.wangyb.sell.dto.OrderDTO;
import com.wangyb.sell.enums.ResultEnum;
import com.wangyb.sell.exception.SellException;
import com.wangyb.sell.form.OrderForm;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class OrderForm2OrderDTOConverter {

    private static Logger log = LoggerFactory.getLogger(OrderForm2OrderDTOConverter.class);


    public static OrderDTO convert(OrderForm orderForm) {
        Gson gson = new Gson();

        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setBuyerName(orderForm.getName());
        orderDTO.setBuyerPhone(orderForm.getPhone());
        orderDTO.setBuyerAddress(orderForm.getAddress());
        orderDTO.setBuyerOpenid(orderForm.getOpenid());

        List<OrderDetail> orderDetailList = new ArrayList<>();
        try {
            orderDetailList = gson.fromJson(orderForm.getItems(), new TypeToken<List<OrderDetail>>() {
            }.getType());
        }catch (Exception e) {
            log.error("【对象转换错误】,string={}",orderForm.getItems());
            throw new SellException(ResultEnum.PARAM_ERROR);
        }
        orderDTO.setOrderDetailList(orderDetailList);
        return orderDTO;
    }
}
