package com.oukele.dao;

import com.oukele.model.CommodityCompany;
import java.util.List;

public interface CommodityCompanyMapper {
    int deleteByPrimaryKey(Integer commodityCompanyId);

    int insert(CommodityCompany record);

    CommodityCompany selectByPrimaryKey(Integer commodityCompanyId);

    List<CommodityCompany> selectAll();

    int updateByPrimaryKey(CommodityCompany record);
}