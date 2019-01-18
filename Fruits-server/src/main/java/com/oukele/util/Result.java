package com.oukele.util;

import java.util.List;

public class Result {
    // 响应的状态
    private int code;
    //携带的信息
    private String msg;
    //返回的数据
    private Object data;
    //数据的条数
    private int total;

    //构造方法
    private Result(){}
    private Result(int code){
        this.code = code;
    }
    //响应编码 加 信息
    private Result(int code ,String msg){
        this.code = code;
        this.msg = msg;
    }
    //响应编码 加 信息 加 数据
    private Result(int code ,String msg , Object data){
        this.code = code;
        this.msg = msg;
        this.data = data;
    }
    //响应编码 加 信息 加 数据
    private Result(int code ,String msg , Object data,int total){
        this.code = code;
        this.msg = msg;
        this.data = data;
        this.total = total;
    }

    //通过此方法返回结果
    public static Result getResult(){
        return new Result(202);
    }
    public static Result getResult(int code ,String msg ){
        return new Result(code,msg);
    }
    public static Result getResult(Object data){
        return new Result(202,"SUCCESS",data);
    }
    public static Result getResult(Object data,int total){
        return new Result(202,"SUCCESS",data,total);
    }
    //出现错误
    public static Result error(Object data){
        return new Result(414,"Error",data);
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
