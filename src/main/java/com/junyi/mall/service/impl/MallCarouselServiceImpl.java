package com.junyi.mall.service.impl;

import com.junyi.mall.controller.VO.MallIndexCarouselVO;
import com.junyi.mall.dao.CarouselMapper;
import com.junyi.mall.entity.Carousel;
import com.junyi.mall.service.MallCarouselService;
import com.junyi.mall.util.BeanUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
@Service
public class MallCarouselServiceImpl implements MallCarouselService {
    @Autowired
    private CarouselMapper carouselMapper;
    @Override
    public List<MallIndexCarouselVO> getCarouselsForIndex(int number) {
        List<MallIndexCarouselVO> newBeeMallIndexCarouselVOS = new ArrayList<>(number);
        List<Carousel> carousels = carouselMapper.findCarouselsByNum(number);
        if (!CollectionUtils.isEmpty(carousels)) {
            newBeeMallIndexCarouselVOS = BeanUtil.copyList(carousels, MallIndexCarouselVO.class);
        }
        return newBeeMallIndexCarouselVOS;
    }
}
