package com.oukele.dao;

import com.oukele.model.CommodityTag;
import java.util.List;

public interface CommodityTagMapper {
    int deleteByPrimaryKey(Integer commodityTagId);

    int insert(CommodityTag record);

    CommodityTag selectByPrimaryKey(Integer commodityTagId);

    List<CommodityTag> selectAll();

    int updateByPrimaryKey(CommodityTag record);
}