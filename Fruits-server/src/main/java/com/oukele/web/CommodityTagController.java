package com.oukele.web;

import com.oukele.serviceImp.CommodityTagServiceImp;
import com.oukele.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/commodityTag")
public class CommodityTagController {

    @Autowired
    private CommodityTagServiceImp commodityTagServiceImp;

    /*
    * 得到 全部 的数据
    * */
    @GetMapping(path = "")
    public Result getCommodieyTag(){
        return Result.getResult(commodityTagServiceImp.getCommodityTag());
    }

}
