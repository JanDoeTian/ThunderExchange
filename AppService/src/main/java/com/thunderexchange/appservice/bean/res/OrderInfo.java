package com.gudy.counter.bean.res;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * 委托信息
 */
@Setter
@Getter
@NoArgsConstructor
@ToString
public class OrderInfo {

    private int id;
    private long uid;
    private int code;
    private String name;
    private int direction;
    private int type;
    private long price;
    private long ocount;
    private int status;
    private String date;
    private String time;

}
