package com.junyi.mall.service;

import com.junyi.mall.entity.MallGood;
import com.junyi.mall.util.PageQueryUtil;
import com.junyi.mall.util.PageResult;

import java.util.List;

public interface MallGoodsService {
    /**
     * 后台分页
     *
     * @param pageUtil
     * @return
     */
    PageResult getMallGoodPage(PageQueryUtil pageUtil);

    /**
     * 添加商品
     *
     * @param goods
     * @return
     */
    String saveMallGood(MallGood goods);

    /**
     * 批量新增商品数据
     *
     * @param MallGoodList
     * @return
     */
    void batchSaveMallGood(List<MallGood> MallGoodList);

    /**
     * 修改商品信息
     *
     * @param goods
     * @return
     */
    String updateMallGood(MallGood goods);

    /**
     * 获取商品详情
     *
     * @param id
     * @return
     */
    MallGood getMallGoodById(Long id);

    /**
     * 批量修改销售状态(上架下架)
     *
     * @param ids
     * @return
     */
    Boolean batchUpdateSellStatus(Long[] ids,int sellStatus);

    /**
     * 商品搜索
     *
     * @param pageUtil
     * @return
     */
    PageResult searchMallGood(PageQueryUtil pageUtil);
}
