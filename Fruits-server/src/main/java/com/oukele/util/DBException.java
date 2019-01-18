package com.oukele.util;

public class DBException extends Exception {
    public DBException(){
        super("向数据库执行相关数据出错");
    }
    public DBException(String msg){
        super(msg);
    }
}
