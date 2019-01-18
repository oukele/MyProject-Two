package com.oukele.web;

import com.oukele.serviceImp.CommodityCompanyServiceImp;
import com.oukele.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/commodityCompany")
public class CommodityCompanyController {

    @Autowired
    private CommodityCompanyServiceImp commodityCompanyServiceImp;


    /**
     * 得到商品单位数据
     * */
    @RequestMapping(path = "",method = RequestMethod.GET)
    private Result getList(){
        return Result.getResult(commodityCompanyServiceImp.list());
    }
}
