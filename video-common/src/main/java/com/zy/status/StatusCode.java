package com.zy.status;

/**
 * 返回状态码
 */
public class StatusCode {
    //返回成功
    public static final int SUCCESS_CODE = 200;
    //错误状态码
    public static final int ERROR_CODE = 500;
    //无权限
    public static final int NO_LOGIN = 600;
    public static final int NO_AUTH = 700;
    // 是否重复邮箱
    public static final int ERROR_repeat = 666;
    // 是否重复账号
    public static final int ERROR_username = 999;
    // 验证码
    public static final int ERROR_verification = 1000;
    // 判断账号是否被封
    public static final int ERROR_state = 1001;
}
