package com.gudy.counter.bean.res;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 通用返回格式
 */
@AllArgsConstructor
public class CounterRes {

    public static final int SUCCESS = 0;
    public static final int RELOGIN = 1;
    public static final int FAIL = 2;

    @Getter
    private int code;

    @Getter
    private String message;

    @Getter
    private Object data;


    public CounterRes(Object data){
        this(0,"",data);
    }



}
