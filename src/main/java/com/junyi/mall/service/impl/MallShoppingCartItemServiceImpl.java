package com.junyi.mall.service.impl;

import com.junyi.mall.common.Constants;
import com.junyi.mall.common.ServiceResultEnum;
import com.junyi.mall.controller.VO.MallShoppingCartItemVO;
import com.junyi.mall.dao.MallGoodsMapper;
import com.junyi.mall.dao.MallShoppingCartItemMapper;
import com.junyi.mall.entity.MallGood;
import com.junyi.mall.entity.MallShoppingCartItem;
import com.junyi.mall.service.MallShoppingCartItemService;
import com.junyi.mall.util.BeanUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class MallShoppingCartItemServiceImpl implements MallShoppingCartItemService {
    @Autowired
    private MallShoppingCartItemMapper MallShoppingCartItemMapper;

    @Autowired
    private MallGoodsMapper MallGoodsMapper;

    //todo 修改session中购物项数量

    @Override
    public String saveNewBeeMallCartItem(MallShoppingCartItem MallShoppingCartItem) {
        MallShoppingCartItem temp = MallShoppingCartItemMapper.selectByUserIdAndGoodsId(MallShoppingCartItem.getUserId(), MallShoppingCartItem.getGoodsId());
        if (temp != null) {
            //已存在则修改该记录
            //todo count = tempCount + 1
            temp.setGoodsCount(MallShoppingCartItem.getGoodsCount());
            return updateNewBeeMallCartItem(temp);
        }
        MallGood MallGoods = MallGoodsMapper.selectByPrimaryKey(MallShoppingCartItem.getGoodsId());
        //商品为空
        if (MallGoods == null) {
            return ServiceResultEnum.GOODS_NOT_EXIST.getResult();
        }
        int totalItem = MallShoppingCartItemMapper.selectCountByUserId(MallShoppingCartItem.getUserId());
        //超出最大数量
        if (totalItem > Constants.SHOPPING_CART_ITEM_LIMIT_NUMBER) {
            return ServiceResultEnum.SHOPPING_CART_ITEM_LIMIT_NUMBER_ERROR.getResult();
        }
        //保存记录
        if (MallShoppingCartItemMapper.insertSelective(MallShoppingCartItem) > 0) {
            return ServiceResultEnum.SUCCESS.getResult();
        }
        return ServiceResultEnum.DB_ERROR.getResult();
    }

    @Override
    public String updateNewBeeMallCartItem(MallShoppingCartItem MallShoppingCartItem) {
        MallShoppingCartItem MallShoppingCartItemUpdate = MallShoppingCartItemMapper.selectByPrimaryKey(MallShoppingCartItem.getCartItemId());
        if (MallShoppingCartItemUpdate == null) {
            return ServiceResultEnum.DATA_NOT_EXIST.getResult();
        }
        //超出最大数量
        if (MallShoppingCartItem.getGoodsCount() > Constants.SHOPPING_CART_ITEM_LIMIT_NUMBER) {
            return ServiceResultEnum.SHOPPING_CART_ITEM_LIMIT_NUMBER_ERROR.getResult();
        }
        //todo 数量相同不会进行修改
        //todo userId不同不能修改
        MallShoppingCartItemUpdate.setGoodsCount(MallShoppingCartItem.getGoodsCount());
        MallShoppingCartItemUpdate.setUpdateTime(new Date());
        //保存记录
        if (MallShoppingCartItemMapper.updateByPrimaryKeySelective(MallShoppingCartItemUpdate) > 0) {
            return ServiceResultEnum.SUCCESS.getResult();
        }
        return ServiceResultEnum.DB_ERROR.getResult();
    }

    @Override
    public MallShoppingCartItem getNewBeeMallCartItemById(Long MallShoppingCartItemId) {
        return MallShoppingCartItemMapper.selectByPrimaryKey(MallShoppingCartItemId);
    }

    @Override
    public Boolean deleteById(Long MallShoppingCartItemId) {
        //todo userId不同不能删除
        return MallShoppingCartItemMapper.deleteByPrimaryKey(MallShoppingCartItemId) > 0;
    }

    @Override
    public List<MallShoppingCartItemVO> getMyShoppingCartItems(Long MallUserId) {
        List<MallShoppingCartItemVO> MallShoppingCartItemVOS = new ArrayList<>();
        List<MallShoppingCartItem> MallShoppingCartItems = MallShoppingCartItemMapper.selectByUserId(MallUserId, Constants.SHOPPING_CART_ITEM_TOTAL_NUMBER);
        if (!CollectionUtils.isEmpty(MallShoppingCartItems)) {
            //查询商品信息并做数据转换
            List<Long> MallGoodsIds = MallShoppingCartItems.stream().map(MallShoppingCartItem::getGoodsId).collect(Collectors.toList());
            List<MallGood> MallGoods = MallGoodsMapper.selectByPrimaryKeys(MallGoodsIds);
            Map<Long, MallGood> MallGoodsMap = new HashMap<>();
            if (!CollectionUtils.isEmpty(MallGoods)) {
                MallGoodsMap = MallGoods.stream().collect(Collectors.toMap(MallGood::getGoodsId, Function.identity(), (entity1, entity2) -> entity1));
            }
            for (MallShoppingCartItem MallShoppingCartItem : MallShoppingCartItems) {
                MallShoppingCartItemVO MallShoppingCartItemVO = new MallShoppingCartItemVO();
                BeanUtil.copyProperties(MallShoppingCartItem, MallShoppingCartItemVO);
                if (MallGoodsMap.containsKey(MallShoppingCartItem.getGoodsId())) {
                    MallGood MallGoodsTemp = MallGoodsMap.get(MallShoppingCartItem.getGoodsId());
                    MallShoppingCartItemVO.setGoodsCoverImg(MallGoodsTemp.getGoodsCoverImg());
                    String goodsName = MallGoodsTemp.getGoodsName();
                    // 字符串过长导致文字超出的问题
                    if (goodsName.length() > 28) {
                        goodsName = goodsName.substring(0, 28) + "...";
                    }
                    MallShoppingCartItemVO.setGoodsName(goodsName);
                    MallShoppingCartItemVO.setSellingPrice(MallGoodsTemp.getSellingPrice());
                    MallShoppingCartItemVOS.add(MallShoppingCartItemVO);
                }
            }
        }
        return MallShoppingCartItemVOS;
    }
}
