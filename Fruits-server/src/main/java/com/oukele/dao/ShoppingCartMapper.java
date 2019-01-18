package com.oukele.dao;

import com.oukele.model.ShoppingCart;
import java.util.List;

public interface ShoppingCartMapper {
    int deleteByPrimaryKey(Integer shoppingCartId);

    int insert(ShoppingCart record);

    ShoppingCart selectByPrimaryKey(Integer shoppingCartId);

    List<ShoppingCart> selectAll();

    int updateByPrimaryKey(ShoppingCart record);
}