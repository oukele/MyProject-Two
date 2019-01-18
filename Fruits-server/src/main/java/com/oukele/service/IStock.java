package com.oukele.service;

import com.oukele.model.Stock;
import com.oukele.util.DBException;
import com.oukele.util.DataException;

import java.util.List;

public interface IStock {

    /**
     * 库存全部信息
     * */
    List<Stock> list(int page ,int total);

    /*
    *  库存数据总条数
    * */
    int getTotal();

    /**
     * 根据ID 获取数据
     * */
    Stock selectByPrimaryKey(int stockId);

    /*
    * 修改库存信息
    * */
    void updateStock(Stock stock) throws DataException, DBException;
    /*
    *  模糊搜索
    * */
    List<Stock> selectSearch(String searchText);

    void addStock(Stock stock) throws DBException;

}
