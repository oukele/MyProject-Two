package com.oukele.dao;

import com.oukele.model.Customer;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CustomerMapper {
    int deleteByPrimaryKey(Integer customerId);

    int insert(Customer record);

    Customer selectByPrimaryKey(Integer customerId);

    List<Customer> selectAll();

    int updateByPrimaryKey(Customer record);

    int getTotal();

    List<Customer> searchText(@Param("searchName") String searchName);

    int searchTotal(@Param("searchName") String searchName);

    int  updateCustomerState(@Param("customerId") int customerId,@Param("customerState") Boolean customerState);

}