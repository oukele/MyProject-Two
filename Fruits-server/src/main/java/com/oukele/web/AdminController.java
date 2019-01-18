package com.oukele.web;

import com.oukele.model.Admin;
import com.oukele.serviceImp.AdminServiceImp;
import com.oukele.util.DBException;
import com.oukele.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping(path = "/admin")
public class AdminController {
    @Autowired
    private AdminServiceImp adminServiceImp;
    @Autowired
    HttpSession session;

    @GetMapping(path = "/check")
    public Result checkUser(@RequestParam String adminUser){
        try {
            adminServiceImp.checkAdminUser(adminUser);
        } catch (DBException e) {
           return Result.error(e.getMessage());
        }
        return Result.getResult();
    }

    @PostMapping(path = "")
    public Result login(@RequestBody Admin admin){
        try {
            adminServiceImp.login(admin);
        } catch (DBException e) {
            return Result.error(e.getMessage());
        }
        return Result.getResult();
    }

    /*
    *  查询 session 的信息
    * */
    @GetMapping(path = "")
    public Result getSession(){
        Admin adminUser = (Admin) session.getAttribute("adminUser");
        if( adminUser == null ){
            return Result.error("404");
        }
        return Result.getResult(session.getAttribute("adminUser"));
    }

    @GetMapping(path="/logout")
    public Result logout(){
        session.removeAttribute("adminUser");
        return Result.getResult();
    }

}
