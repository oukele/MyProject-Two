package com.oukele.dao;

import com.oukele.model.Express;
import java.util.List;

public interface ExpressMapper {
    int deleteByPrimaryKey(Integer expressId);

    int insert(Express record);

    Express selectByPrimaryKey(Integer expressId);

    List<Express> selectAll();

    int updateByPrimaryKey(Express record);
}