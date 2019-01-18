package com.oukele.dao;

import com.oukele.model.Admin;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AdminMapper {

    int deleteByPrimaryKey(Integer adminId);

    int insert(Admin record);

    Admin selectByPrimaryKey(Integer adminId);

    Admin login(Admin admin);

    int selectByAdminUser(@Param("adminUser") String adminUser);

    List<Admin> selectAll();

    int updateByPrimaryKey(Admin record);
}