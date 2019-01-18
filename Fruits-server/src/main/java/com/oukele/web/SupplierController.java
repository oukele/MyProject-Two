package com.oukele.web;

import com.oukele.model.Supplier;
import com.oukele.serviceImp.SupplierServiceImp;
import com.oukele.util.DBException;
import com.oukele.util.DataException;
import com.oukele.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping(path = "/supplier")
public class SupplierController {

    @Autowired
    private SupplierServiceImp supplierServiceImp;

    @GetMapping(path = "/getTotal")
    public Result getTotal() {
        return Result.getResult(supplierServiceImp.getTotal());
    }

    @GetMapping
    public Result list(@RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "10") int total) {
        return Result.getResult(supplierServiceImp.list(page, total));
    }

    /*
     * 按 供应商 状态 查询
     * @param supplierStatus
     * @return result
     * */
//    @GetMapping(path = "/supplierStatus")
//    public Result getSupplierStatus(int supplierStatus,@RequestParam(defaultValue = "1") int page,@RequestParam(defaultValue = "10") int total) {
//        if( (supplierStatus >1) || (supplierStatus <0) ){
//            return Result.error("参数异常。");
//        }
//        List<Supplier> list = supplierServiceImp.selectSupplierStatus(supplierStatus,page,total);
//        if( list.size() == 0 ){
//            return Result.error("没有找到您要查询的数据。");
//        }
//        return Result.getResult(list);
//    }
    /*
     * 按 供应商 状态 查询
     * @param supplierStatus
     * @return result
     * */
    @GetMapping(path = "/supplierStatus")
    public Result getSupplierStatus(int supplierStatus) {
        if( (supplierStatus >1) || (supplierStatus <0) ){
            return Result.error("参数异常。");
        }
        List<Supplier> list = supplierServiceImp.selectSupplierStatus(supplierStatus);
        if( list.size() == 0 ){
            return Result.error("没有找到您要查询的数据。");
        }
        return Result.getResult(list);
    }

    /*
     * 按 供应商 地址 模糊查询
     * @param supplierStatus
     * @return result
     * */
    @GetMapping(path = "/supplierAddress")
    public Result getSupplierAddress(String supplierAddress) {
        if( supplierAddress.isEmpty() ){
            return Result.error("参数异常。");
        }
        List<Supplier> list = supplierServiceImp.selectSupplierAddress(supplierAddress);
        if( list.size() == 0 ){
            return Result.error("没有找到您要查询的数据。");
        }
        return Result.getResult(list);
    }

    /**
     * 改变供应商的状态
     *
     * @param supplierId supplierStatus
     * @return Result
     */
    @PutMapping(path = "/{supplierId}/{supplierStatus}")
    public Result updateSupplierStatus(@PathVariable("supplierId") int supplierId, @PathVariable("supplierStatus") Boolean supplierStatus) {
        try {
            supplierServiceImp.updateSupplierStatus(supplierId, supplierStatus);
        } catch (DataException e) {
            return Result.error(e.getMessage());
        } catch (DBException e) {
            return Result.error(e.getMessage());
        }
        return Result.getResult("供应商状态修改成功");
    }

    /**
     * 删除供应商信息
     *
     * @param supplerId
     * @return Result
     */
    @DeleteMapping(path = "/{supplierId}")
    public Result delSupplier(@PathVariable("supplierId") int supplerId) {
        try {
            supplierServiceImp.deleteSupplier(supplerId);
        } catch (DBException e) {
            return Result.error(e.getMessage());
        } catch (DataException e) {
            return Result.error(e.getMessage());
        }
        return Result.getResult("删除成功");
    }

    /**
     * 修改供应商信息
     *
     * @param supplier
     * @return Result
     */
    @PutMapping
    public Result updateSupplier(@Valid @RequestBody Supplier supplier, BindingResult result) {
        try {
            supplierServiceImp.updateSupplier(supplier, result);
        } catch (DBException e) {
            return Result.error(e.getMessage());
        } catch (DataException e) {
            return Result.error(e.getMessage());
        }
        return Result.getResult("供应商信息修改成功");
    }

    /**
     * 添加供应商
     *
     * @param supplier
     * @return
     */
    @PostMapping
    public Result addSupplier(@Valid @RequestBody Supplier supplier, BindingResult result) {
        try {
            supplierServiceImp.addSupplier(supplier, result);
        } catch (DBException e) {
            return Result.error(e.getMessage());
        } catch (DataException e) {
            return Result.error(e.getMessage());
        }
        return Result.getResult("新增供应商成功");
    }

}
