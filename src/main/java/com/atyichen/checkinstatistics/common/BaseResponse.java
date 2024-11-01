package com.atyichen.checkinstatistics.common;

import lombok.Data;

import java.io.Serializable;
@Data // 注意 这里必须要加Data， 否则浏览器无法解析返回数据 报错406
public class BaseResponse<T> implements Serializable {
    private int code;
    private T data;
    private String message;

    public BaseResponse(int code, T data, String message) {
        this.code = code;
        this.data = data;
        this.message = message;
    }
    public BaseResponse(int code, T data) {
        this(code, data, "");
    }
    public BaseResponse(ErrorCode errorCode) {
        this(errorCode.getCode(), null, errorCode.getMessage());
    }
}
