package com.junyi.mall.service;

import com.junyi.mall.controller.VO.MallShoppingCartItemVO;
import com.junyi.mall.entity.MallShoppingCartItem;

import java.util.List;

public interface MallShoppingCartItemService {
    /**
     * 保存商品至购物车中
     *
     * @param MallShoppingCartItem
     * @return
     */
    String saveNewBeeMallCartItem(MallShoppingCartItem MallShoppingCartItem);

    /**
     * 修改购物车中的属性
     *
     * @param MallShoppingCartItem
     * @return
     */
    String updateNewBeeMallCartItem(MallShoppingCartItem MallShoppingCartItem);

    /**
     * 获取购物项详情
     *
     * @param MallShoppingCartItemId
     * @return
     */
        MallShoppingCartItem getNewBeeMallCartItemById(Long MallShoppingCartItemId);

    /**
     * 删除购物车中的商品
     *
     * @param MallShoppingCartItemId
     * @return
     */
    Boolean deleteById(Long MallShoppingCartItemId);

    /**
     * 获取我的购物车中的列表数据
     *
     * @param newBeeMallUserId
     * @return
     */
    List<MallShoppingCartItemVO> getMyShoppingCartItems(Long newBeeMallUserId);
}
