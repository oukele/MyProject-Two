package com.oukele.dao;

import com.oukele.model.Cancel;
import java.util.List;

public interface CancelMapper {
    int deleteByPrimaryKey(Integer cancelId);

    int insert(Cancel record);

    Cancel selectByPrimaryKey(Integer cancelId);

    List<Cancel> selectAll();

    int updateByPrimaryKey(Cancel record);
}