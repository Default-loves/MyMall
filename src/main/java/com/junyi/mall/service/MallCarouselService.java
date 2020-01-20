package com.junyi.mall.service;

import com.junyi.mall.controller.VO.MallIndexCarouselVO;

import java.util.List;

public interface MallCarouselService {
    List<MallIndexCarouselVO> getCarouselsForIndex(int number);

}
