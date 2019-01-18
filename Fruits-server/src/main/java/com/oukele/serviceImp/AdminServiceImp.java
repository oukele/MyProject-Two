package com.oukele.serviceImp;

import com.oukele.dao.AdminMapper;
import com.oukele.model.Admin;
import com.oukele.service.AdminService;
import com.oukele.util.DBException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

@Service
public class AdminServiceImp implements AdminService {

    @Autowired
    private AdminMapper adminMapper;
    @Autowired
    HttpSession session;

    @Override
    public void checkAdminUser(String adminUser) throws DBException {
        int result = adminMapper.selectByAdminUser(adminUser);
        if( result == 0 ){
            throw new DBException("账号不存在，请重新输入");
        }
    }
    /*
    *  登陆
    * */
    @Override
    public void login(Admin admin) throws DBException {
        Admin login = adminMapper.login(admin);
        if (login == null){
            throw new DBException("账号与密码不匹配，请重新输入。");
        }else{
            session.setAttribute("adminUser",login);
        }
    }
}
