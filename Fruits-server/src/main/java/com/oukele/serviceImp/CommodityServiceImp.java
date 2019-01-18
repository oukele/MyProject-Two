package com.oukele.serviceImp;

import com.github.pagehelper.PageHelper;
import com.oukele.dao.CommodityMapper;
import com.oukele.dao.StockMapper;
import com.oukele.model.Commodity;
import com.oukele.model.Stock;
import com.oukele.service.ICommodityService;
import com.oukele.util.DBException;
import com.oukele.util.DataException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;

import java.util.List;

@Service
public class CommodityServiceImp implements ICommodityService {

    @Autowired
    private CommodityMapper commodityMapper;

    @Autowired
    private StockMapper stockMapper;

    @Override
    public int getTotal() {
        return commodityMapper.getTotal();
    }

    @Override
    public List<Commodity> list(int page, int total) {
        PageHelper.startPage(page, total);
        List<Commodity> commodities = commodityMapper.selectAll();
        return commodities;
    }

    @Override
    public void addCommodity(Commodity commodity, BindingResult result) throws DBException, DataException {

        check(result);
        //取出库存商品的数量
        Stock stock = stockMapper.selectCommodityCount(commodity.getStockId());
        Double old_count = stock.getStockCount();
        //添加的商品数量
        Double commodityCount = commodity.getCommodityCount();
        //新的库存数量 （ 原来的库存数量 - 添加的商品数量 ）
        double new_count = old_count - commodityCount;

        if (new_count < 0) {
            throw new DataException("商品库存数量不足");
        }
        //进行修改
        Stock update_stock = new Stock();
        update_stock.setStockId(commodity.getStockId());
        update_stock.setStockCount(new_count);
        //修改的结果
        int update_result = stockMapper.updateStockCommodityCount(update_stock);
        //修改的结果大于0 时 说明 库存数量 修改成功 则进行添加，否则 抛异常
        if (update_result > 0) {
            //添加
            commodityMapper.insert(commodity);
        } else {
            throw new DBException("商品添加失败，库存数量不足。");
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

    /**
     * 删除商品信息
     *
     * @param commodityId
     */
    @Override
    public void deleteCommodity(Integer commodityId) throws DBException {
        int result = commodityMapper.deleteByPrimaryKey(commodityId);
        if (!(result > 0)) {
            throw new DBException("删除失败，请联系管理员。");
        }
    }

    /**
     * 修改商品信息
     *
     * @param commodity
     */
    @Override
    public void updateCommodity(Commodity commodity, BindingResult result) throws DataException, DBException {

        check(result);

        //取出库存商品的数量
        Stock stock = stockMapper.selectCommodityCount(commodity.getStockId());
        Double old_count_stock = stock.getStockCount();
        //老的商品数量
        Commodity old_commodity = commodityMapper.selectByPrimaryKey(commodity.getCommodityId());
        // 新的 商品数量
        Double new_count_commodity = commodity.getCommodityCount();

        //如果 新进来的商品数量小于之前的商品数量 代表着 此部分商品数量要回退到 库存中
        //否则 就是 减去 库存的数量
        if (new_count_commodity < old_commodity.getCommodityCount()) {
            // 这个是 部分商品数量回退到 库存的数量
            double v = old_commodity.getCommodityCount() - new_count_commodity;
            // 现库存商品数量 + v = v1
            double v1 = v + old_count_stock;
            //进行库存的修改操作
            Stock stock_update = new Stock();
            stock_update.setStockId(commodity.getStockId());
            stock_update.setStockCount(v1);
            int update_stock = stockMapper.updateStockCommodityCount(stock_update);
            //如果 库存商品数量修改失败，中断
            if (!(update_stock > 0)) {
                throw new DBException("数据库执行操作，出现错误，请联系管理员");
            }
        } else {

            //如果 新的商品数量 跟之前 的商品数量 相等 说明 数量 没有修改，否则先进行数量修改
            if (old_commodity.getCommodityCount() != new_count_commodity) {

                // 这里可以得到 新修改商品数量 = 新修改的商品数量 - 原来商品的数量
                double v = new_count_commodity - old_commodity.getCommodityCount();

                Stock stock_update = new Stock();
                stock_update.setStockId(commodity.getStockId());

                //  新的库存=库存数量 - 新修改商品数量
                double news_update_stock_count = old_count_stock - v;

                //如果 新库存数量 小于 则中断
                if (news_update_stock_count <= 0) {
                    throw new DataException("库存数量不足。");
                }

                stock_update.setStockCount(news_update_stock_count);
                int update_stock = stockMapper.updateStockCommodityCount(stock_update);
                //如果 库存商品数量修改失败，中断
                if (!(update_stock > 0)) {
                    throw new DBException("数据库执行操作，出现错误，请联系管理员");
                }
            }
        }

        int result_update = commodityMapper.updateByPrimaryKey(commodity);
        if (!(result_update > 0)) {
            throw new DBException("数据库执行操作，出现错误，请联系管理员");
        }
    }

    /**
     * 修改商品状态
     */
    @Override
    public void updateCommodityStatus(Integer commodityId, Boolean commodityStatus) throws DataException, DBException {
        Commodity commodity = new Commodity();
        commodity.setCommodityId(commodityId);
        commodity.setCommodityStatus(commodityStatus);

        if (commodity.getCommodityStatus() == null || commodity.getCommodityId() == null) {
            throw new DataException();
        } else if (commodityId == null) {
            throw new DataException("商品的id为:" + commodity);
        } else if (commodityStatus == null) {
            throw new DataException("缺少状态参数");
        } else if (commodityMapper.updateCommodityStatus(commodity) == 0) {
            throw new DBException();
        }
        commodityMapper.updateCommodityStatus(commodity);
    }

}
