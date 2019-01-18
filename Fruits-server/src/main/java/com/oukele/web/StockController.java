package com.oukele.web;

import com.oukele.model.Stock;
import com.oukele.serviceImp.StockServiceImp;
import com.oukele.util.DBException;
import com.oukele.util.DataException;
import com.oukele.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path="/stock")
public class StockController {

    @Autowired
    private StockServiceImp stockServiceImp;
    /**
     * 获取全部库存的信息
     * */
    @RequestMapping(path = "",method = RequestMethod.GET)
    private Result getList(@RequestParam(defaultValue = "1") int page , @RequestParam(defaultValue = "10") int total){
        return Result.getResult(stockServiceImp.list(page,total),stockServiceImp.getTotal());
    }

    /**
     * 根据id 获取 库存信息
     * @param stockId
     * return result
     * */
    @GetMapping(path = "/{stockId}")
    private Result getIdInfo(@PathVariable("stockId") int stockId){
        return Result.getResult(stockServiceImp.selectByPrimaryKey(stockId));
    }

    /*
    *  模糊搜索
    * */
    @GetMapping(path = "/search")
    private Result search(@RequestParam String searchText  ){
        return Result.getResult(stockServiceImp.selectSearch(searchText));
    }

    /*
    * 修改库存信息
    * */
    @PutMapping(path = "")
    public Result updateStock(@RequestBody Stock stock){
        try {
            stockServiceImp.updateStock(stock);
        }catch (DBException e){
            return Result.getResult(e.getMessage());
        }catch (DataException e){
            return Result.getResult(e.getMessage());
        }
        return Result.getResult("修改成功");
    }

    /*
    *  入库
    * */
    @PostMapping(path = "")
    public Result addStock(@RequestBody Stock stock){
        try {
            stockServiceImp.addStock(stock);
        } catch (DBException e) {
            return Result.error(e.getMessage());
        }
        return Result.getResult("入库成功");
    }



}
