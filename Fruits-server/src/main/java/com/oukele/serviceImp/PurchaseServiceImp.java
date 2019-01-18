package com.oukele.serviceImp;

import com.github.pagehelper.PageHelper;
import com.oukele.dao.PurchaseMapper;
import com.oukele.model.Purchase;
import com.oukele.service.PurchaseService;
import com.oukele.util.DBException;
import com.oukele.util.DataException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;

import java.util.List;

@Service
public class PurchaseServiceImp implements PurchaseService {

    @Autowired
    private PurchaseMapper purchaseMapper;

    @Override
    public List<Purchase> getAll(int page, int total) {
        PageHelper.startPage(page, total);
        List<Purchase> purchases = purchaseMapper.selectAll();
        return purchases;
    }

    @Override
    public Purchase getInfo(int purchaseId) {
        return purchaseMapper.selectByPrimaryKey(purchaseId);
    }

    public List<Purchase> getAll() {
        List<Purchase> purchases = purchaseMapper.selectAll();
        return purchases;
    }


    @Override
    public int getTotal() {
        return purchaseMapper.getTotal();
    }

    /**
     * @param purchaseStatus
     * @parma purchaseId
     */
    @Override
    public void updatePurchaseStatus(int purchaseId, Boolean purchaseStatus) throws DataException, DBException {

        if (!(purchaseId > 0)) {
            throw new DataException("供应商编号出现错误，请重试");
        }
        int updatePurchaseStatus = purchaseMapper.updatePurchaseStatus(purchaseId, purchaseStatus);
        if (!(updatePurchaseStatus > 0)) {
            throw new DBException("执行数据出现错误，请联系管理员");
        }
    }

    /**
     * 删除 进货信息
     * @param purchaseId 进货 ID
     */
    @Override
    public void deletePurchase(int purchaseId) throws DataException, DBException {

        if (!(purchaseId > 0)) {
            throw new DataException("进货编号出现异常，请重试。");
        }
        int delete = purchaseMapper.deleteByPrimaryKey(purchaseId);

        if (!(delete > 0)) {
            throw new DBException("执行数据，出现异常，请联系管理员。");
        }
    }

    /**
     * 修改 进货信息
     *
     * @param purchase
     */
    @Override
    public void updatePurchase(Purchase purchase, BindingResult result) throws DataException, DBException {
        check(result);
        int update_purchase = purchaseMapper.updateByPrimaryKey(purchase);
        if (!(update_purchase > 0)) {
            throw new DBException("执行数据，出现异常，请重试。");
        }
    }

    /**
     * 新增 进货信息
     */
    @Override
    public void addPurchase(Purchase purchase, BindingResult result) throws DataException, DBException {
        check(result);
        int insert = purchaseMapper.insert(purchase);
        if( !(insert >0) ){
            throw new DataException("执行数据，出现错误，请联系管理员");
        }

    }

    /**
     * 根据 负责人 或者 供应商名称 、商品标签 进行 模糊搜索
     * */
    @Override
    public List<Purchase> getSearchPurchase(String searchName,int page,int total) {
        PageHelper.startPage(page,total);
        List<Purchase> list = purchaseMapper.searchPurchase(searchName);
        return list;
    }

    /*
     * 获取 模糊查询后 的条数 便于 进行分页
     * */
    @Override
    public int getSearchTotal(String searchName) {
        return purchaseMapper.getSearchTotal(searchName);
    }

    /**
     * 根据 进货 状态 进行 查询
     * */
    @Override
    public List<Purchase> selectPurchaseStatus(int purchaseStatus) {
        return purchaseMapper.selectPurchaseStatus(purchaseStatus);
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
