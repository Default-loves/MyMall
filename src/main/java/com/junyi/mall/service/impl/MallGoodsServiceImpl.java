package com.junyi.mall.service.impl;

import com.junyi.mall.common.ServiceResultEnum;
import com.junyi.mall.controller.VO.MallSearchGoodsVO;
import com.junyi.mall.dao.MallGoodsMapper;
import com.junyi.mall.entity.MallGood;
import com.junyi.mall.service.MallGoodsService;
import com.junyi.mall.util.BeanUtil;
import com.junyi.mall.util.PageQueryUtil;
import com.junyi.mall.util.PageResult;
import org.graalvm.util.CollectionsUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service

public class MallGoodsServiceImpl implements MallGoodsService {
    @Autowired
    private MallGoodsMapper mallGoodsMapper;

    @Override
    public PageResult getMallGoodPage(PageQueryUtil pageUtil) {
        int totalMallGood = mallGoodsMapper.getTotalMallGood(pageUtil);
        List<MallGood> mallGoodList = mallGoodsMapper.findMallGoodList(pageUtil);
        return new PageResult(mallGoodList, totalMallGood, pageUtil.getLimit(), pageUtil.getPage());
    }

    @Override
    public String saveMallGood(MallGood goods) {
        if (mallGoodsMapper.insertSelective(goods) > 0)
            return ServiceResultEnum.SUCCESS.getResult();
        else
            return ServiceResultEnum.DB_ERROR.getResult();
    }

    @Override
    public void batchSaveMallGood(List<MallGood> MallGoodList) {
        if (!CollectionUtils.isEmpty(MallGoodList))
            mallGoodsMapper.batchInsert(MallGoodList);
    }

    @Override
    public String updateMallGood(MallGood goods) {
        MallGood tmp = mallGoodsMapper.selectByPrimaryKey(goods.getGoodsId());
        if (tmp == null) {
            return ServiceResultEnum.DATA_NOT_EXIST.getResult();
        }
        goods.setUpdateTime(new Date());
        if (mallGoodsMapper.updateByPrimaryKeySelective(goods) > 0){
            return ServiceResultEnum.SUCCESS.getResult();
        }
        return ServiceResultEnum.DB_ERROR.getResult();
    }

    @Override
    public MallGood getMallGoodById(Long id) {
        return mallGoodsMapper.selectByPrimaryKey(id);
    }

    @Override
    public Boolean batchUpdateSellStatus(Long[] ids, int sellStatus) {
        return mallGoodsMapper.batchUpdateSellStatus(ids, sellStatus) > 0;
    }

    @Override
    public PageResult searchMallGood(PageQueryUtil pageUtil) {
        List<MallGood> goodsLMist = mallGoodsMapper.findMallGoodListBySearch(pageUtil);
        int total = mallGoodsMapper.getTotalMallGoodBySearch(pageUtil);
        List<MallSearchGoodsVO> newBeeMallSearchGoodsVOS = new ArrayList<>();
        if (!CollectionUtils.isEmpty(goodsLMist)) {
            newBeeMallSearchGoodsVOS = BeanUtil.copyList(goodsLMist, MallSearchGoodsVO.class);
            for (MallSearchGoodsVO newBeeMallSearchGoodsVO : newBeeMallSearchGoodsVOS) {
                String goodsName = newBeeMallSearchGoodsVO.getGoodsName();
                String goodsIntro = newBeeMallSearchGoodsVO.getGoodsIntro();
                // 字符串过长导致文字超出的问题
                if (goodsName.length() > 28) {
                    goodsName = goodsName.substring(0, 28) + "...";
                    newBeeMallSearchGoodsVO.setGoodsName(goodsName);
                }
                if (goodsIntro.length() > 30) {
                    goodsIntro = goodsIntro.substring(0, 30) + "...";
                    newBeeMallSearchGoodsVO.setGoodsIntro(goodsIntro);
                }
            }
        }
        PageResult pageResult = new PageResult(newBeeMallSearchGoodsVOS, total, pageUtil.getLimit(), pageUtil.getPage());
        return pageResult;
    }
}
