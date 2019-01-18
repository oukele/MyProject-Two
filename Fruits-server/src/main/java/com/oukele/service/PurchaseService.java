package com.oukele.service;

import com.oukele.model.Purchase;
import com.oukele.util.DBException;
import com.oukele.util.DataException;
import org.springframework.validation.BindingResult;

import java.util.List;

public interface PurchaseService {

    /**
     * 得到全部信息
     * */
    List<Purchase> getAll(int page,int total);

    /*
    * 根据id 获取单个对象
    * */
    Purchase getInfo(int purchaseId);

    /**
     * 得到 purchase 表中的 条数
     * */
    int getTotal();

    /**
     * 根据 id 修改 状态
     * */
    void updatePurchaseStatus(int purchaseId,Boolean purchaseStatus) throws DataException, DBException;

    /**
     * 根据 Id  删除 进货信息
     * */
    void deletePurchase(int purchaseId) throws DataException,DBException;

    /**
     * 修改 进货信息
     * */
    void updatePurchase(Purchase purchase, BindingResult result)throws DataException,DBException;

    /**
     *  新增 进货信息
     * */
    void addPurchase(Purchase purchase, BindingResult result)throws DataException,DBException;

    /**
     * 根据 负责人 或者 供应商名称 、商品标签 进行 模糊搜索
     * */
    List<Purchase> getSearchPurchase(String searchName, int page, int total);

    /*
    * 获取 模糊查询后 的条数 便于 进行分页
    * */
    int getSearchTotal(String searchName);

    /**
     *  根据 进货 状态 进行 模糊查询
     * */
    List<Purchase> selectPurchaseStatus(int purchaseStatus);

}
