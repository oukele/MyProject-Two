package com.oukele.web;

import com.oukele.model.Purchase;
import com.oukele.serviceImp.PurchaseServiceImp;
import com.oukele.util.DBException;
import com.oukele.util.DataException;
import com.oukele.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "/purchase")
public class PurchaseController {
    @Autowired
    private PurchaseServiceImp purchaseServiceImp;

    /**
     * @param page 页数
     * @param total 每页的条数
     * @return Result
     * */
    @GetMapping(path = "")
    public Result getListAll(@RequestParam(defaultValue = "1") int page,@RequestParam(defaultValue = "10") int total){
        List<Purchase> all = purchaseServiceImp.getAll(page, total);
        return Result.getResult(all);
    }
    @GetMapping(path = "/to")
    public Result getListAll(){
        List<Purchase> all = purchaseServiceImp.getAll();
        return Result.getResult(all);
    }

    /*
    * 根据 Id  获取 信息
    * */
    @GetMapping(path = "/{purchaseId}")
    public Result getInfo(@PathVariable("purchaseId")int purchaseId){
        Purchase info = purchaseServiceImp.getInfo(purchaseId);
        return Result.getResult(info);
    }

    /**
     *得到 全部 数据的条数
     * */
    @GetMapping(path = "/getTotal")
    public Result getTotal(){
        return Result.getResult(purchaseServiceImp.getTotal());
    }

    /**
     * 根据 负责人 或者 供应商名称 、商品标签 进行 模糊搜索
     * */
    @GetMapping(path = "/searchPurchase")
    public Result getSearch(@RequestParam String searchName,@RequestParam(defaultValue = "1")int page,@RequestParam(defaultValue = "10") int total){
        return Result.getResult(purchaseServiceImp.getSearchPurchase(searchName,page,total));
    }

    /**
     *得到 模糊查后 数据的条数
     * */
    @GetMapping(path = "/searchTotal")
    public Result getSearchTotal(@RequestParam String searchName){
        return Result.getResult(purchaseServiceImp.getSearchTotal(searchName));
    }

    /**
     * 根据 进货 状态 进行 查询
     * */
    @GetMapping(path = "/purchaseStatus")
    public Result getSelectPurchaseStatus(@RequestParam  int purchaseStatus){
        if( (purchaseStatus >1) || (purchaseStatus <0) ){
            return Result.error("参数异常。");
        }
        List<Purchase> list = purchaseServiceImp.selectPurchaseStatus(purchaseStatus);
        if( list.size() == 0 ){
            return Result.error("没有找到您要查询的数据。");
        }
        return Result.getResult(list);
    }

    /**
     * @param purchaseId
     * @param purchaseStatus
     * */
    @PutMapping(path = "/{purchaseId}/{purchaseStatus}")
    public Result updatePurchaseStatus(@PathVariable("purchaseId")int purchaseId,@PathVariable("purchaseStatus") Boolean purchaseStatus ){
        try {
            purchaseServiceImp.updatePurchaseStatus(purchaseId,purchaseStatus);
        } catch (DataException e) {
            return Result.error(e.getMessage());
        } catch (DBException e) {
            return Result.error(e.getMessage());
        }
        return Result.getResult("进货状态修改成功");
    }

    /**
     * 删除进货信息
     * */
    @DeleteMapping(path = "/{purchaseId}")
    public Result deletePurchase(@PathVariable("purchaseId")int purchaseId){
        try {
            purchaseServiceImp.deletePurchase(purchaseId);
        } catch (DataException e) {
            return Result.error(e.getMessage());
        } catch (DBException e) {
            return Result.error(e.getMessage());
        }
        return Result.getResult("删除成功。");
    }

    /**
     *  修改 进货信息
     * */
    @PutMapping(path = "")
    public Result updatePurchase(@Valid @RequestBody Purchase purchase, BindingResult result){
        try {
            purchaseServiceImp.updatePurchase(purchase,result);
        } catch (DataException e) {
            return Result.error(e.getMessage());
        } catch (DBException e) {
            return Result.error(e.getMessage());
        }
        return Result.getResult("信息修改成功");
    }

    /**
     * 新增 进货 信息
     * */
    @PostMapping(path = "")
    public Result addPurchase(@Valid @RequestBody Purchase purchase,BindingResult result){
        try {
            purchaseServiceImp.addPurchase(purchase,result);
        } catch (DataException e) {
            return Result.error(e.getMessage());
        } catch (DBException e) {
            return Result.error(e.getMessage());
        }
        return Result.getResult("新增进货信息成功");
    }
}
