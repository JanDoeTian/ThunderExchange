package commons.order;

import lombok.Getter;

@Getter
public enum OrderStatus {


    // order : NOT_SET --> ORDER_ED --> PART_TRADE --> TRADE_ED
    //                          \-->    REJECT

    //cancel: NOT_SET --> PART_CANCEL --> CANCEL_ED
    //                          \-->    REJECT
    //

    NOT_SET(-1),

    CANCEL_ED(1),
    PART_CANCEL(2),

    //    ORDER_STANDBY(3),
    ORDER_ED(3),

    TRADE_ED(4),
    PART_TRADE(5),

    REJECT(6);


    private int code;

    OrderStatus(int code) {
        this.code = code;
    }


}
