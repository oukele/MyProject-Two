package com.oukele.web;
import com.oukele.model.Commodity;
import com.oukele.serviceImp.CommodityServiceImp;
import com.oukele.util.DBException;
import com.oukele.util.DataException;
import com.oukele.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "/commodity")
public class CommodityController {

    @Autowired
    private CommodityServiceImp commodityServiceImp;

    /**
     * 获取商品的条数
     * */
    @GetMapping(path="/total")
    private Result getTotal(){
        return Result.getResult(commodityServiceImp.getTotal());
    }

    /**
     * 分页的商品数据
     * @param page 页数
     * @param total 每页分配多少条数据
     * @return Result
     * */
    @RequestMapping(path = "",method = RequestMethod.GET)
    private Result getList(@RequestParam(defaultValue = "1") int page,@RequestParam(defaultValue = "10") int total){
        List<Commodity> list = commodityServiceImp.list(page,total);
        return  Result.getResult(list);
    }

    /**
     * 添加商品信息
     * @param commodity
     * @return Result
     * */
    @RequestMapping(path = "",method = RequestMethod.POST,produces = "application/json;charset=utf-8")
    private Result addCommodity(@Valid @RequestBody Commodity commodity , BindingResult result){
        try {
            commodityServiceImp.addCommodity(commodity,result);
        } catch (DBException e) {
            return  Result.error(e.getMessage());
        } catch (DataException e) {
            return  Result.error(e.getMessage());
        }
        return  Result.getResult("添加成功");
    }

    /**
     * 删除商品信息
     * @param commodityId
     * @return Result
     * */
    @RequestMapping(path = "/{commodityId}",method = RequestMethod.DELETE)
    private Result deleteCommodity(@PathVariable("commodityId") Integer commodityId){
        try {
            commodityServiceImp.deleteCommodity(commodityId);
        } catch (DBException e) {
            return Result.error(e.getMessage());
        }
        return Result.getResult("删除成功");
    }

    /**
     * 下架商品 改变商品的状态
     * @param commodityId commodityStatus
     * @return Result
     * */
    @RequestMapping(path = "/{commodityId}/{commodityStatus}",method
            = RequestMethod.PUT)
    private Result updateCommodityStatus(@PathVariable("commodityId") Integer commodityId,@PathVariable("commodityStatus") boolean commodityStatus ){
        try {
            commodityServiceImp.updateCommodityStatus(commodityId,commodityStatus);
        } catch (DataException e) {
            return  Result.error(e.getMessage());
        } catch (DBException e) {
            return  Result.error(e.getMessage());
        }
        return  Result.getResult();
    }

    /**
     * 修改商品信息
     * @param commodity
     * @return Result
     * */
    @RequestMapping(path = "",method = RequestMethod.PUT)
    private Result updateCommodity(@Valid @RequestBody Commodity commodity,BindingResult result){
        try {
            commodityServiceImp.updateCommodity(commodity,result);
        } catch (DataException e) {
            return Result.error(e.getMessage());
        } catch (DBException e) {
            return Result.error(e.getMessage());
        }
        return Result.getResult("修改成功");
    }


}