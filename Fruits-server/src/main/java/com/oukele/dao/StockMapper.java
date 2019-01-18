package com.oukele.dao;

import com.oukele.model.Stock;
import java.util.List;

public interface StockMapper {

    int deleteByPrimaryKey(Integer stockId);

    int insert(Stock record);

    Stock selectByPrimaryKey(Integer stockId);

    List<Stock> selectAll();

    int updateByPrimaryKey(Stock record);

    /*
    *  库存数据条数
    * */
    int getTotal();

    /*
    * 添加商品时，库存商品库存  -  添加的商品数量 ( 根据 库存编号 )
    * */
    int updateStockCommodityCount(Stock record);
    /*
    * 查询某个商品的库存
    * */
    Stock selectCommodityCount(Integer stockId);

    /*
    * 模糊搜索
    * */
    List<Stock> selectSearch(String searchText);

}