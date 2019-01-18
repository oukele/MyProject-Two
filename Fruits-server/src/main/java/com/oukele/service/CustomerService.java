package com.oukele.service;

import com.oukele.model.Customer;
import com.oukele.util.DBException;

import java.util.List;

public interface CustomerService {

    /*
    *  分页查询信息
    * */
    List<Customer> list(int page,int total);

    /*
    *  数据的总条数
    * */
    int getTotal();

    /*
    *  模糊查询
    * */
    List<Customer> searchList(int page , int total,String searchText);

    /*
    *  模糊查询的条数
    * */
    int searchTotal(String searchText);

    /*
    *
    * 修改状态
    * */
    void updateCustomerState(int customerId,Boolean customerState) throws DBException;

}
