package com.oukele.service;

import com.oukele.dao.CustomerMapper;
import com.oukele.model.Customer;
import com.oukele.serviceImp.CustomerServiceImp;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@ContextConfiguration(locations = "classpath:spring-root.xml")
public class CustomerTest {

    @Autowired
    private CustomerServiceImp customerServiceImp;

    @Test
    public void getList(){
        List<Customer> list = customerServiceImp.list(1, 10);
        for (Customer customer : list) {
            System.out.println(customer.getCustomerState());
        }
    }

    @Test
    public void search(){
        List<Customer> h = customerServiceImp.searchList(1, 10, "h");
        for (Customer customer : h) {
            System.out.println(customer.getCustomerUser());
        }

        int h1 = customerServiceImp.searchTotal("h");
        System.out.println(h1);

    }

}
