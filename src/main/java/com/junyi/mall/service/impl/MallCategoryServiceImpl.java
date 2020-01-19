package com.junyi.mall.service.impl;

import com.junyi.mall.controller.VO.MallIndexCategoryVO;
import com.junyi.mall.controller.VO.SearchPageCategoryVO;
import com.junyi.mall.dao.GoodsCategoryMapper;
import com.junyi.mall.entity.GoodsCategory;
import com.junyi.mall.service.MallCategoryService;
import com.junyi.mall.util.PageQueryUtil;
import com.junyi.mall.util.PageResult;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class MallCategoryServiceImpl implements MallCategoryService {

    @Autowired
    private GoodsCategoryMapper goodsCategoryMapper;

    @Override
    public PageResult getCategorisPage(PageQueryUtil pageUtil) {
        return null;
    }

    @Override
    public String saveCategory(GoodsCategory goodsCategory) {
        return null;
    }

    @Override
    public String updateGoodsCategory(GoodsCategory goodsCategory) {
        return null;
    }

    @Override
    public GoodsCategory getGoodsCategoryById(Long id) {
        return null;
    }

    @Override
    public Boolean deleteBatch(Integer[] ids) {
        return null;
    }

    @Override
    public List<MallIndexCategoryVO> getCategoriesForIndex() {
        return null;
    }

    @Override
    public SearchPageCategoryVO getCategoriesForSearch(Long categoryId) {
        return null;
    }

    @Override
    public List<GoodsCategory> selectByLevelAndParentIdsAndNumber(List<Long> parentIds, int categoryLevel) {
        return null;
    }
}
