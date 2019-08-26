package com.wangyb.sell.dataObject;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
public class SellerInfo {

    @Id
    private String id;

    private String username;

    private String password;

    private String openid;
}
