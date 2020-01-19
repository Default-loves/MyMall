package com.junyi.mall.controller.VO;

import com.junyi.mall.entity.GoodsCategory;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class SearchPageCategoryVO implements Serializable {
    private String firstLevelCategoryName;

    private List<GoodsCategory> secondLevelCategoryList;

    private String secondLevelCategoryName;

    private List<GoodsCategory> thirdLevelCategoryList;

    private String currentCategoryName;
}
