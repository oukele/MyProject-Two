package com.oukele.service;

import ch.qos.logback.classic.Logger;
import com.google.gson.Gson;
import com.oukele.model.Commodity;
import com.oukele.serviceImp.CommodityServiceImp;
import com.oukele.util.Result;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@ContextConfiguration(locations = "classpath:spring-root.xml")
public class CommodityTest {
    @Autowired
    public CommodityServiceImp commodityServeImp;

    Logger logger = (Logger) LoggerFactory.getLogger(CommodityTest.class);

    @Test
    public void commodityList(){
        List<Commodity> list = commodityServeImp.list(1,3);
        logger.info(new Gson().toJson(Result.getResult(list)));
    }
}
