package com.oukele.service;

import com.oukele.model.Stock;
import com.oukele.serviceImp.StockServiceImp;
import com.oukele.util.Result;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@ContextConfiguration(locations = "classpath:spring-root.xml")
public class StockTest {

    @Autowired
    private StockServiceImp stockServiceImp;

    @Test
    public void getAll(){
        System.out.println(Result.getResult(stockServiceImp.list(1,10), stockServiceImp.getTotal()));
    }

    @Test
    public void getSearch(){
        List<Stock> stocks = stockServiceImp.selectSearch("箱");
        for (Stock stock : stocks) {
            System.out.println(stock.getStockRemarks());
        }
    }

}
