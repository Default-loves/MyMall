package com.junyi.mall.controller.VO;

import lombok.Data;

import java.io.Serializable;


@Data
public class MallIndexConfigGoodsVO implements Serializable {
    private Long goodsId;

    private String goodsName;

    private String goodsIntro;

    private String goodsCoverImg;

    private Integer sellingPrice;

    private String tag;
}
