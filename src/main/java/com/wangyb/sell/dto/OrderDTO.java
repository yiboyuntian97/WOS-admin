package com.wangyb.sell.dto;

import com.wangyb.sell.dataObject.OrderDetail;
import com.wangyb.sell.enums.OrderStatusEnum;
import com.wangyb.sell.enums.PayStatusEnum;

import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class OrderDTO {

    /**
     * 订单id
     */
    @Id
    private String orderId;

    /**
     * 买家名字
     */
    private String buyerName;

    /**
     * 买家手机号
     */
    private String buyerPhone;

    /**
     * 买家地址
     */
    private String buyerAddress;

    /**
     * 买家微信openid
     */
    private String buyerOpenid;

    /**
     * 订单总金额
     */
    private BigDecimal orderAmount;

    /**
     * 订单状态，默认0新订单
     */
    private Integer orderStatus;

    /**
     * 支付状态，默认0未支付
     */
    private Integer payStatus;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 订单详情列表
     */
    List<OrderDetail> orderDetailList;
}
