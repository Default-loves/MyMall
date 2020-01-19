package com.junyi.mall.dao;

import com.junyi.mall.entity.MallGood;
import com.junyi.mall.entity.StockNumDTO;
import com.junyi.mall.util.PageQueryUtil;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MallGoodsMapper {
    int deleteByPrimaryKey(Long goodsId);

    int insert(MallGood record);

    int insertSelective(MallGood record);

    MallGood selectByPrimaryKey(Long goodsId);

    int updateByPrimaryKeySelective(MallGood record);

    int updateByPrimaryKeyWithBLOBs(MallGood record);

    int updateByPrimaryKey(MallGood record);

    List<MallGood> findMallGoodList(PageQueryUtil pageUtil);

    int getTotalMallGood(PageQueryUtil pageUtil);

    List<MallGood> selectByPrimaryKeys(List<Long> goodsIds);

    List<MallGood> findMallGoodListBySearch(PageQueryUtil pageUtil);

    int getTotalMallGoodBySearch(PageQueryUtil pageUtil);

    int batchInsert(@Param("MallGoodList") List<MallGood> MallGoodList);

    int updateStockNum(@Param("stockNumDTOS") List<StockNumDTO> stockNumDTOS);

    int batchUpdateSellStatus(@Param("orderIds")Long[] orderIds, @Param("sellStatus") int sellStatus);

}
