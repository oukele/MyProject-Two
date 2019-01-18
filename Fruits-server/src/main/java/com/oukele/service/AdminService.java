package com.oukele.service;

import com.oukele.model.Admin;
import com.oukele.util.DBException;

public interface AdminService {
    /*
    * 根据 账号 检验 是否 存在
    * */
    void checkAdminUser(String adminUser)throws DBException;

    /*
    * 登陆
    * */
    void login(Admin admin)throws DBException;

}
