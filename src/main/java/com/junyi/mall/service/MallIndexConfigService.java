package com.junyi.mall.service;

import com.junyi.mall.controller.VO.MallIndexConfigGoodsVO;

import java.util.List;

public interface MallIndexConfigService {
    /**
     * 返回固定数量的首页配置商品对象(首页调用)
     *
     * @param number
     * @return
     */
    List<MallIndexConfigGoodsVO> getConfigGoodsesForIndex(int configType, int number);
}
