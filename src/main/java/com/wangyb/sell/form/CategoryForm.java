package com.wangyb.sell.form;

import lombok.Data;

@Data
public class CategoryForm {

    private Integer categoryId;

    /**
     * 类目名称
     */
    private String categoryName;

    /**
     * 类目编号
     */
    private Integer categoryType;

}
