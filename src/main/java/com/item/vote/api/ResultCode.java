package com.item.vote.api;

/**
 * 枚举了一些常用API操作码
 */
public enum ResultCode implements IErrorCode {
    SUCCESS(0, "操作成功"),
    FAILED(-1, "操作失败"),



    VALIDATE_FAILED(404, "参数检验失败"),
    UNAUTHORIZED(401, "暂未登录或token已经过期"),
    FORBIDDEN(403, "没有相关权限"),



    UNMANAGER(800,"您并非管理员，没有此权限"),
    UNSUPERMANAGER(801,"您并非超级管理员，没有此权限"),
    UNAUTHORIZEDSESSION(900, "暂未登录或session已经过期"),
    USERNAMEEXIST(901, "用户名已经存在");
    private long code;
    private String message;

    private ResultCode(long code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public long getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }
}