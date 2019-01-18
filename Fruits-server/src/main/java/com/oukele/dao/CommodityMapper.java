package com.oukele.dao;

import com.oukele.model.Commodity;
import java.util.List;

public interface CommodityMapper {
    int deleteByPrimaryKey(Integer commodityId);

    int insert(Commodity record);

    Commodity selectByPrimaryKey(Integer commodityId);

    List<Commodity> selectAll();

    int getTotal();

    int updateByPrimaryKey(Commodity record);

    int updateCommodityStatus(Commodity commodity);

}