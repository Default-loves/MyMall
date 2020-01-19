package com.junyi.mall.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@AllArgsConstructor
public class StockNumDTO {
    private Long goodsId;

    private Integer goodsCount;
}
