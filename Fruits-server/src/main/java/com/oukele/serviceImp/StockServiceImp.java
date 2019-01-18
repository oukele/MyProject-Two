package com.oukele.serviceImp;

import com.github.pagehelper.PageHelper;
import com.oukele.dao.StockMapper;
import com.oukele.model.Stock;
import com.oukele.service.IStock;
import com.oukele.util.DBException;
import com.oukele.util.DataException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class StockServiceImp implements IStock {

    @Autowired
    private StockMapper stockMapper;

    @Override
    public List<Stock> list(int page,int total) {
        PageHelper.startPage(page,total);
        return stockMapper.selectAll();
    }
    @Override
    public int getTotal() {
        return stockMapper.getTotal();
    }

    @Override
    public Stock selectByPrimaryKey(int stockId) {
        return stockMapper.selectByPrimaryKey(stockId);
    }

    /*
    * 修改库存信息
    * */
    @Override
    public void updateStock(Stock stock) throws DBException, DataException {
        if( !( stock.getStockId() > 0 ) ){
            throw new DataException("数据有错误，请重试。");
        }
        int updateByPrimaryKey = stockMapper.updateByPrimaryKey(stock);

        if( !(updateByPrimaryKey>0) ){
            throw new DBException("执行数据出错，请联系管理员");
        }
    }
    /*
    *  模糊搜索
    * */
    @Override
    public List<Stock> selectSearch(String searchText) {
        return stockMapper.selectSearch(searchText);
    }
    /*
    *  入库
    * */
    @Override
    public void addStock(Stock stock) throws DBException {

        int insert = stockMapper.insert(stock);

        if( !(insert >0) ){
            throw new DBException("执行数据，出现错误，请联系管理员");
        }

    }
}
