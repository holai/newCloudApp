package cn.com.newland.nle_sdk.responseEntity.base;

import java.io.Serializable;


/**
 * Created by marco on 2017/8/21.
 * 请求响应
 */

public class BaseResponseEntity<T> implements Serializable {
    private Object ErrorObj;
    private int Status;
    private int StatusCode;
    private String Msg;
    private T ResultObj;

    public Object getErrorObj() {
        return ErrorObj;
    }

    public void setErrorObj(Object errorObj) {
        ErrorObj = errorObj;
    }

    public void setMsg(String msg) {
        Msg = msg;
    }

    public void setErrorObj(String errorObj) {
        ErrorObj = errorObj;
    }

    public void setStatus(int status) {
        Status = status;
    }

    public void setStatusCode(int statusCode) {
        StatusCode = statusCode;
    }


    public void setResultObj(T resultObj) {
        ResultObj = resultObj;
    }


    public int getStatus() {
        return Status;
    }

    public int getStatusCode() {
        return StatusCode;
    }

    public String getMsg() {
        return Msg;
    }

    public T getResultObj() {
        return ResultObj;
    }

}
