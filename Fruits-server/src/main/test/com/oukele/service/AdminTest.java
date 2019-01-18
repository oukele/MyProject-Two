package com.oukele.service;

import com.oukele.serviceImp.AdminServiceImp;
import com.oukele.util.DBException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@ContextConfiguration(locations = "classpath:spring-root.xml")
public class AdminTest {

    @Autowired
    private AdminServiceImp adminServiceImp;

    @Test
    public void check(){
        try {
            adminServiceImp.checkAdminUser("oukele11");
        } catch (DBException e) {
            e.printStackTrace();
        }
    }

}
