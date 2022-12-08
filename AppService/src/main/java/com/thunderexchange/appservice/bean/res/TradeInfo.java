package com.gudy.counter.bean.res;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 成交记录
 */
@Setter
@Getter
@NoArgsConstructor
public class TradeInfo {

    //    date: "20191127",
//    time: "13:00:05",
//    code: "000001",
//    name: "平安银行",
//    tradePrice: 7.91,
//    tradeCount:1000,
//    tradeMoney:7910,
//    fee:0.5

    private int id;
    private long uid;
    private int code;
    private String name;
    private int direction;
    private float price;
    private int tcount;
    private int oid;
    private String date;
    private String time;

}
