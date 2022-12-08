package com.gudy.counter.bean.res;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * 持仓信息
 */
@Setter
@Getter
@NoArgsConstructor
@ToString
public class PosiInfo {

    private int id;
    private long uid;
    private int code;
    private String name;
    private long cost;
    private long count;


}
