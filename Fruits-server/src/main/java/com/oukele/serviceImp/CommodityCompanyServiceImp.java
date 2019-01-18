package com.oukele.serviceImp;

import com.oukele.dao.CommodityCompanyMapper;
import com.oukele.model.CommodityCompany;
import com.oukele.service.ICommodityCompany;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommodityCompanyServiceImp implements ICommodityCompany {
    @Autowired
    private CommodityCompanyMapper commodityCompanyMapper;

    @Override
    public List<CommodityCompany> list() {
        return commodityCompanyMapper.selectAll();
    }
}
