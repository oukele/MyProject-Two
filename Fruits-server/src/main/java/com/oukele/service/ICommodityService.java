package com.oukele.service;

import com.oukele.model.Commodity;
import com.oukele.util.DBException;
import com.oukele.util.DataException;
import org.springframework.validation.BindingResult;

import java.util.List;

public interface ICommodityService {

    /**
     * 数据的总条数
     * */
    int getTotal();

    /**
     * 分页获取商品的全部信息
    * */
    List<Commodity> list(int page,int total);
    /**
     * 添加商品 ,验证器
     * */
    void addCommodity(Commodity commodity , BindingResult result) throws DBException, DataException;
    /**
     * 删除商品
     * */
    void deleteCommodity(Integer commodityId) throws DBException;
    /**
     *修改商品
     * */
    void updateCommodity(Commodity commodity ,BindingResult result) throws DataException, DBException;
    /**
     *下架商品
     * */
    void updateCommodityStatus(Integer commodityId,Boolean commodityStatus) throws DataException, DBException;

}
