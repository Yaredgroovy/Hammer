package cn.pengxun.vshop.mvp.model.entity;

import java.io.Serializable;

import cn.pengxun.vshop.mvp.model.api.Address;

/**
 * 服务器返回的数据固定为这种方式(字段名可根据服务器更改)
 * @author liu-feng 
 * @date 2017/3/13 0013.
 * Email:w710989327@foxmail.com
 */
public class BaseEntity<T> implements Serializable {
    private T data;
    private String code;
    private String msg;

    public T getData() {
        return data;
    }

    public String getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public boolean isSuccess() {
        if (code.equals(Address.RequestSuccess))
            return true;
        return false;
    }
}
