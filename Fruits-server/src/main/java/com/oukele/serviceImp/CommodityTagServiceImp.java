package com.oukele.serviceImp;

import com.oukele.dao.CommodityCompanyMapper;
import com.oukele.dao.CommodityTagMapper;
import com.oukele.model.CommodityTag;
import com.oukele.service.CommodityTagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommodityTagServiceImp implements CommodityTagService {

    @Autowired
    private CommodityTagMapper commodityTagMapper;

    /*
    * 得 全部的 商品标检
    * */
    @Override
    public List<CommodityTag> getCommodityTag() {
        return commodityTagMapper.selectAll();
    }

}
