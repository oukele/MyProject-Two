package com.oukele.util;

public class DataException extends Exception {
    public DataException(){
        super("数据结构有错误");
    }
    public DataException(String msg){
        super(msg);
    }
}
