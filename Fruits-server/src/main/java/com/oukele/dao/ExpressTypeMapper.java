package com.oukele.dao;

import com.oukele.model.ExpressType;
import java.util.List;

public interface ExpressTypeMapper {
    int deleteByPrimaryKey(Integer expressTypeId);

    int insert(ExpressType record);

    ExpressType selectByPrimaryKey(Integer expressTypeId);

    List<ExpressType> selectAll();

    int updateByPrimaryKey(ExpressType record);
}