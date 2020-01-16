package com.junyi.mall.entity;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class AdminUser {
    private Integer adminUserId;

    private String loginUserName;

    private String loginPassword;

    private String nickName;

    private Byte locked;
}
