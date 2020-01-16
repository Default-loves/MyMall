package com.junyi.mall.entity;

import lombok.Data;
import lombok.ToString;

import java.util.Date;

@Data
@ToString
public class MallShoppingCartItem {
    private Long cartItemId;

    private Long userId;

    private Long goodsId;

    private Integer goodsCount;

    private Byte isDeleted;

    private Date createTime;

    private Date updateTime;
}
