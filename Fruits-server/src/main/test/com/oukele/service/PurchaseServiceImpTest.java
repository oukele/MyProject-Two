package com.oukele.service;


import com.oukele.model.Purchase;
import com.oukele.serviceImp.PurchaseServiceImp;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@ContextConfiguration(locations = "classpath:spring-root.xml")
public class PurchaseServiceImpTest {

    @Autowired
    PurchaseServiceImp purchaseServiceImp;

    @Test
    public void getAll(){
        List<Purchase> list = purchaseServiceImp.getAll(1, 10);
        for (Purchase purchase : list) {
            System.out.println(purchase.getCommodityTag());
        }
    }
    // 进货表状态

    //根据 供应商名称 、 负责人 进行模糊查询

    //根据 商品标签 进行 查询

}
