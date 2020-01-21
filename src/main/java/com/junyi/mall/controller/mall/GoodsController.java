package com.junyi.mall.controller.mall;

import com.junyi.mall.controller.VO.MallGoodsDetailVO;
import com.junyi.mall.entity.MallGood;
import com.junyi.mall.service.MallCategoryService;
import com.junyi.mall.service.MallGoodsService;
import com.junyi.mall.util.BeanUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpServletRequest;

@Controller
public class GoodsController {
    @Autowired
    private MallGoodsService mallGoodsService;
    @Autowired
    private MallCategoryService mallCategoryService;

    @GetMapping("/goods/detail/{goodsId}")
    public String detailPage(@PathVariable("goodsId") Long goodsId, HttpServletRequest request) {
        if (goodsId < 1) {
            return "error/error_5xx";
        }
        MallGood goods = mallGoodsService.getMallGoodById(goodsId);
        if (goods == null) {
            return "error/error_404";
        }
        MallGoodsDetailVO goodsDetailVO = new MallGoodsDetailVO();
        BeanUtil.copyProperties(goods, goodsDetailVO);
        goodsDetailVO.setGoodsCarouselList(goods.getGoodsCarousel().split(","));
        request.setAttribute("goodsDetail", goodsDetailVO);
        return "mall/detail";
    }
}
