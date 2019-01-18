package com.oukele.web;

import com.oukele.model.Customer;
import com.oukele.serviceImp.CustomerServiceImp;
import com.oukele.util.DBException;
import com.oukele.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/customer")
public class CustomerController {

    @Autowired
    private CustomerServiceImp customerServiceImp;

    /*
    * 获取 信息 与 条数
    * */
    @GetMapping(path = "")
    public Result getList(@RequestParam(defaultValue = "1") int page , @RequestParam(defaultValue = "10") int total){
        return Result.getResult(customerServiceImp.list(page,total),customerServiceImp.getTotal());
    }

    /*
    *  模糊搜索
    * */
    @GetMapping(path = "/search")
    public Result getSearch(@RequestParam(defaultValue = "1") int page ,@RequestParam(defaultValue = "10") int total,@RequestParam String searchName){
        List<Customer> list = customerServiceImp.searchList(page, total, searchName);
        int total1 = customerServiceImp.searchTotal(searchName);
        return Result.getResult(list,total1);
    }

    /*
    * 修改状态
    * */
    @GetMapping(path = "/updateState/{customerId}/{customerState}")
    public Result updateState(@PathVariable("customerId") int customerId ,@PathVariable("customerState") Boolean customerState){
        try {
            customerServiceImp.updateCustomerState(customerId,customerState);
        } catch (DBException e) {
            return Result.error(e.getMessage());
        }
        return Result.getResult("状态修改成功。");
    }

}
