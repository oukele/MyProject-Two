package com.oukele.dao;

import com.oukele.model.Supplier;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SupplierMapper {
    int deleteByPrimaryKey(Integer supplierId);

    int insert(Supplier record);

    Supplier selectByPrimaryKey(Integer supplierId);

    List<Supplier> selectAll();

    int updateByPrimaryKey(Supplier record);

    int getTotal();

    int updateSupplierStatus(@Param("supplierId") Integer supplierId,@Param("supplierStatus") Boolean supplierStatus);

    List<Supplier> selectSupplierStatus(int supplierStatus);

    List<Supplier> selectSupplierAddress(String supplierAddress);

}