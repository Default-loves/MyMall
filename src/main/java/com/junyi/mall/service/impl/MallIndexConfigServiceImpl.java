package com.junyi.mall.service.impl;

import com.junyi.mall.controller.VO.MallIndexConfigGoodsVO;
import com.junyi.mall.dao.IndexConfigMapper;
import com.junyi.mall.service.MallIndexConfigService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class MallIndexConfigServiceImpl implements MallIndexConfigService {
    @Autowired
    private IndexConfigMapper indexConfigMapper;


    @Override
    public List<MallIndexConfigGoodsVO> getConfigGoodsesForIndex(int configType, int number) {
        return null;
    }
}
