package com.oukele.service;

import com.oukele.model.Supplier;
import com.oukele.util.DBException;
import com.oukele.util.DataException;
import org.springframework.validation.BindingResult;

import java.util.List;

public interface SupplierService {

    /**
     * 数据的总条数
     * */
    int getTotal();

    /*
    * 按 供应商 状态 模糊查询
    * */
    List<Supplier> selectSupplierStatus(int supplierStatus);
    /*
     * 按 供应商 地址 模糊查询
     * */
    List<Supplier> selectSupplierAddress(String supplierAddress);

    /**
     * 分页获取商品的全部信息
     * */
    List<Supplier> list(int page, int total);
    /**
     * 添加供应商 ,验证器
     * */
    void addSupplier(Supplier supplier , BindingResult result) throws DBException, DataException;
    /**
     * 删除供应商
     * */
    void deleteSupplier(Integer supplierId) throws DataException,DBException;
    /**
     *修改供应商
     * */
    void updateSupplier(Supplier supplier,BindingResult result) throws DataException, DBException;
    /**
     *供应商状态
     * */
    void updateSupplierStatus(Integer supplierId,Boolean supplierStatus) throws DataException, DBException;


}
