package com.junyi.mall.controller.VO;

import lombok.Data;

/**
 * 搜索列表页商品VO
 */

@Data
public class MallSearchGoodsVO {
    private Long goodsId;

    private String goodsName;

    private String goodsIntro;

    private String goodsCoverImg;

    private Integer sellingPrice;
}
