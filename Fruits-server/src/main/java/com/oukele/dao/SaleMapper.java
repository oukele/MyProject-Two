package com.oukele.dao;

import com.oukele.model.Sale;
import java.util.List;

public interface SaleMapper {
    int deleteByPrimaryKey(Integer saleId);

    int insert(Sale record);

    Sale selectByPrimaryKey(Integer saleId);

    List<Sale> selectAll();

    int updateByPrimaryKey(Sale record);
}