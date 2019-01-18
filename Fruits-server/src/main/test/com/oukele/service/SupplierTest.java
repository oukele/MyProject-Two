package com.oukele.service;

import com.oukele.model.Supplier;
import com.oukele.serviceImp.SupplierServiceImp;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@ContextConfiguration(locations = "classpath:spring-root.xml")
public class SupplierTest {

    @Autowired
    SupplierServiceImp supplierServiceImp;

    @Test
    public void getList() {
        System.out.println(supplierServiceImp.list(1, 10));
    }

    @Test
    public void getSupplierStatus() {
        List<Supplier> suppliers = supplierServiceImp.selectSupplierStatus(0);
        for (Supplier supplier : suppliers) {
            System.out.println(supplier);
        }
        if (!(suppliers.size() > 0)) {
            System.out.println("没有数据");
        } else {
            System.out.println("有数据");
        }
    }

    @Test
    public void getSupplierAddress() {
        List<Supplier> suppliers = supplierServiceImp.selectSupplierAddress("广");
        for (Supplier supplier : suppliers) {
            System.out.println(supplier);
        }
        if (!(suppliers.size() > 0)) {
            System.out.println("没有数据");
        } else {
            System.out.println("有数据");
        }
    }

    @Test
    public void test() {
        //获取省份 、获取 出生日 、 获取性别
        // 前17位 系数
        int[] preCard17 = {7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2};
        // 余数与身份证第18位对应关系
        String[] card18 = {"1", "0", "X", "9", "8", "7", "6", "5", "4", "3"};
        //身份证 622924199605211012
        String cardId = "622924199605211012";

        String[] pre17 = cardId.substring(0, 17).split("");
        // 前17位与系数的乘积和
        int sum = 0;
        for (int i = 0; i < preCard17.length; i++) {
            sum += preCard17[i] * Integer.parseInt(pre17[i]);
        }
        // 求余数
        int seek = sum % 11;
        //检验
        if (cardId.substring(17).equals( card18[seek] )) {
            System.out.println(cardId + "是有效的身份证");
        } else {
            System.out.println(cardId + "无效身份证");
        }

    }

}
