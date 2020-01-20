package com.junyi.mall.service.impl;

import com.junyi.mall.controller.VO.MallIndexConfigGoodsVO;
import com.junyi.mall.dao.IndexConfigMapper;
import com.junyi.mall.dao.MallGoodsMapper;
import com.junyi.mall.entity.IndexConfig;
import com.junyi.mall.entity.MallGood;
import com.junyi.mall.service.MallIndexConfigService;
import com.junyi.mall.util.BeanUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
@Service
public class MallIndexConfigServiceImpl implements MallIndexConfigService {
    @Autowired
    private IndexConfigMapper indexConfigMapper;
    @Autowired
    private MallGoodsMapper goodsMapper;


    @Override
    public List<MallIndexConfigGoodsVO> getConfigGoodsesForIndex(int configType, int number) {
        List<MallIndexConfigGoodsVO> MallIndexConfigGoodsVOS = new ArrayList<>(number);
        List<IndexConfig> indexConfigs = indexConfigMapper.findIndexConfigsByTypeAndNum(configType, number);
        if (!CollectionUtils.isEmpty(indexConfigs)) {
            //取出所有的goodsId
            List<Long> goodsIds = indexConfigs.stream().map(IndexConfig::getGoodsId).collect(Collectors.toList());
            List<MallGood> newBeeMallGoods = goodsMapper.selectByPrimaryKeys(goodsIds);
            MallIndexConfigGoodsVOS = BeanUtil.copyList(newBeeMallGoods, MallIndexConfigGoodsVO.class);
            for (MallIndexConfigGoodsVO newBeeMallIndexConfigGoodsVO : MallIndexConfigGoodsVOS) {
                String goodsName = newBeeMallIndexConfigGoodsVO.getGoodsName();
                String goodsIntro = newBeeMallIndexConfigGoodsVO.getGoodsIntro();
                // 字符串过长导致文字超出的问题
                if (goodsName.length() > 30) {
                    goodsName = goodsName.substring(0, 30) + "...";
                    newBeeMallIndexConfigGoodsVO.setGoodsName(goodsName);
                }
                if (goodsIntro.length() > 22) {
                    goodsIntro = goodsIntro.substring(0, 22) + "...";
                    newBeeMallIndexConfigGoodsVO.setGoodsIntro(goodsIntro);
                }
            }
        }
        return MallIndexConfigGoodsVOS;
    }
}
