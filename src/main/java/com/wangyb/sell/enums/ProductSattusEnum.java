package com.wangyb.sell.enums;

import lombok.Data;
import lombok.Getter;

import javax.persistence.criteria.CriteriaBuilder;

/**
 * 商品状态枚举
 */
@Getter
public enum ProductSattusEnum implements CodeEnum {
    UP(0,"上架"),
    DOWN(1,"下架")
    ;
    private Integer code;
    private String message;
    ProductSattusEnum(Integer code,String message){
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
