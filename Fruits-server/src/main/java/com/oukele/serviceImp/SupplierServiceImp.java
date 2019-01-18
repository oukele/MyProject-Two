package com.oukele.serviceImp;

import com.github.pagehelper.PageHelper;
import com.oukele.dao.SupplierMapper;
import com.oukele.model.Supplier;
import com.oukele.service.SupplierService;
import com.oukele.util.DBException;
import com.oukele.util.DataException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;

import java.util.List;

@Service
public class SupplierServiceImp implements SupplierService {

    @Autowired
    private SupplierMapper supplierMapper;

    @Override
    public int getTotal() {
        return supplierMapper.getTotal();
    }

    @Override
    public List<Supplier> selectSupplierStatus(int supplierStatus) {
        List<Supplier> suppliers = supplierMapper.selectSupplierStatus(supplierStatus);
        return suppliers;
    }

    @Override
    public List<Supplier> selectSupplierAddress(String supplierAddress) {
        List<Supplier> suppliers = supplierMapper.selectSupplierAddress(supplierAddress);
        return suppliers;
    }

    @Override
    public List<Supplier> list(int page, int total) {
        PageHelper.startPage(page, total);
        List<Supplier> list = supplierMapper.selectAll();
        return list;
    }

    @Override
    public void addSupplier(Supplier supplier, BindingResult result) throws DBException, DataException {
        check(result);
        int result_insert = supplierMapper.insert(supplier);
        if (!(result_insert > 0)) {
            throw new DBException("执行数据出现异常，请联系管理员。");
        }
    }

    @Override
    public void deleteSupplier(Integer supplierId) throws DBException, DataException {

        if (!(supplierId > 0)) {
            throw new DataException("该供应商编号，出现异常，请联系管理员");
        }
        int result = supplierMapper.deleteByPrimaryKey(supplierId);
        if (!(result > 0)) {
            throw new DBException("执行数据出现异常，请联系管理员");
        }
    }

    @Override
    public void updateSupplier(Supplier supplier, BindingResult result) throws DataException, DBException {
        check(result);

        int update_result = supplierMapper.updateByPrimaryKey(supplier);

        if (!(update_result > 0)) {
            throw new DBException("执行数据出现错误，请联系管理员。");
        }

    }

    @Override
    public void updateSupplierStatus(Integer supplierId, Boolean supplierStatus) throws DataException, DBException {
        if (supplierId < 0) {
            throw new DataException("供应商编号，不正确，请联系管理员");
        }
        int result = supplierMapper.updateSupplierStatus(supplierId, supplierStatus);

        if (!(result > 0)) {
            throw new DBException("数据库，出现异常，请联系管理员。");
        }
    }

    private void check(BindingResult result) throws DataException {
        if (result.hasErrors()) {
            String error = "";
            List<ObjectError> errors = result.getAllErrors();
            for (ObjectError err : errors) {
                error += err;
            }
            throw new DataException(error);
        }
    }

}
