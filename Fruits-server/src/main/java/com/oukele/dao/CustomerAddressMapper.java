package com.oukele.dao;

import com.oukele.model.CustomerAddress;
import java.util.List;

public interface CustomerAddressMapper {
    int deleteByPrimaryKey(Integer customerAddressId);

    int insert(CustomerAddress record);

    CustomerAddress selectByPrimaryKey(Integer customerAddressId);

    List<CustomerAddress> selectAll();

    int updateByPrimaryKey(CustomerAddress record);
}