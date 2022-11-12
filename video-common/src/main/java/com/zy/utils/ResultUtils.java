package com.zy.utils;

import com.zy.status.StatusCode;
import lombok.Data;

import java.sql.Statement;

/**
 * 数据返回工具类
 */
@Data
public class ResultUtils {
    /**
     * 无参数返回
     */
    public static ResultVo success() {
        return Vo(null, StatusCode.SUCCESS_CODE, null);
    }


    public static ResultVo success(String msg){
        return Vo(msg,StatusCode.SUCCESS_CODE,null);
    }
    /**
     * 返回带参数
     */
    public static ResultVo success(String msg,Object data){
        return Vo(msg, StatusCode.SUCCESS_CODE,data);
    }

    public static ResultVo success(Object data){
        return Vo(data);
    }

    private static ResultVo Vo(Object data) {
        return Vo(data);
    }


    public static ResultVo success(String msg,int code,Object data){
        return Vo(msg,code,data);
    }
    public static ResultVo Vo(String msg, int code, Object data) {
        return new ResultVo(msg, code, data);
    }

    /**
     * 错误返回
     */
    public static ResultVo error(String no, String token, String 没有权限){
        return Vo(null,StatusCode.ERROR_CODE,null);
    }
    public static ResultVo error(String msg){
        return Vo(msg,StatusCode.ERROR_CODE,null);
    }
    public static ResultVo error(String msg,int code,Object data){
        return Vo(msg,code,data);
    }
    public static ResultVo error(String msg,int code){
        return Vo(msg,code,null);
    }
    public static ResultVo error(String msg,Object data){
        return Vo(msg, StatusCode.ERROR_CODE,data);
    }

    /**
     * 是否重复邮箱
     */
    public static ResultVo e(String msg){
        return Vo(msg,StatusCode.ERROR_repeat,null);
    }
    public static ResultVo e(String msg,int code){
        return Vo(msg,StatusCode.ERROR_repeat,null);
    }

    /**
     * 是否重复账号
     */
    public static ResultVo Use(String msg){
        return Vo(msg,StatusCode.ERROR_username,null);
    }
    public static ResultVo Use(String msg,int code){
        return Vo(msg,StatusCode.ERROR_username,null);
    }


    /**
     * 验证码
     */
    public static ResultVo ver(String msg) {
        return Vo(msg, StatusCode.ERROR_verification, null);
    }

    public static ResultVo ver(String msg, int code) {
        return Vo(msg, StatusCode.ERROR_verification, null);
    }

    /**
     * 判断账号是否被封
     */
    public static ResultVo state(String msg){
        return Vo(msg,StatusCode.ERROR_state,null);
    }
    public static ResultVo state(String msg,int code){
        return Vo(msg,StatusCode.ERROR_state,null);
    }

}
