package com.junyi.mall.entity;

import lombok.Data;
import lombok.ToString;

import java.util.Date;

@Data
@ToString
public class MallOrderItem {
    private Long orderItemId;

    private Long orderId;

    private Long goodsId;

    private String goodsName;

    private String goodsCoverImg;

    private Integer sellingPrice;

    private Integer goodsCount;

    private Date createTime;
}
