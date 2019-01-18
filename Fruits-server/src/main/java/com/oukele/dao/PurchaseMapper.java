package com.oukele.dao;

import com.oukele.model.Purchase;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PurchaseMapper {
    int deleteByPrimaryKey(Integer purchaseId);

    int insert(Purchase record);

    Purchase selectByPrimaryKey(Integer purchaseId);

    List<Purchase> selectAll();

    int updateByPrimaryKey(Purchase record);

    int getTotal();

    int updatePurchaseStatus(@Param("purchaseId") int purchaseId,@Param("purchaseStatus") Boolean purchaseStatus);

    List<Purchase> searchPurchase(@Param("searchName") String searchName);

    List<Purchase> selectPurchaseStatus(int purchaseStatus);

    int getSearchTotal(@Param("searchName") String searchName);

}