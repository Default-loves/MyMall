package com.junyi.mall.controller.VO;

import lombok.Data;

import java.io.Serializable;

@Data
public class MallGoodsDetailVO implements Serializable {
    private Long goodsId;

    private String goodsName;

    private String goodsIntro;

    private String goodsCoverImg;

    private String[] goodsCarouselList;

    private Integer sellingPrice;

    private Integer originalPrice;

    private String goodsDetailContent;
}
