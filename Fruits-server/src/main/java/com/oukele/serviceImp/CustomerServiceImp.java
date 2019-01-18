package com.oukele.serviceImp;

import com.github.pagehelper.PageHelper;
import com.oukele.dao.CustomerMapper;
import com.oukele.model.Customer;
import com.oukele.service.CustomerService;
import com.oukele.util.DBException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImp implements CustomerService {

    @Autowired
    private CustomerMapper customerMapper;

    /*
    * 分页 获取信息
    * */
    @Override
    public List<Customer> list(int page ,int total) {
        PageHelper.startPage(page,total);
        List<Customer> list = customerMapper.selectAll();
        return list;
    }

    /*
    * 获取总条数
    * */
    @Override
    public int getTotal() {
        return customerMapper.getTotal();
    }

    /*
    *  模糊查询
    * */
    @Override
    public List<Customer> searchList(int page ,int total ,String searchText) {
        //PageHelper.startPage(page,total);
        List<Customer> searchText1 = customerMapper.searchText(searchText);
        return searchText1;
    }
    /*
    *  模糊查询的总条数
    * */
    @Override
    public int searchTotal(String searchText) {
        return customerMapper.searchTotal(searchText);
    }

    /*
    * 修改状态
    * */
    @Override
    public void updateCustomerState(int customerId,Boolean customerState) throws DBException {
        int state = customerMapper.updateCustomerState(customerId,customerState);
        if( !(state > 0) ){
            throw new DBException("执行数据出错。");
        }
    }

}
