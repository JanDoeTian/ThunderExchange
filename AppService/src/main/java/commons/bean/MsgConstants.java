package commons.bean;

public class MsgConstants {



//    public static final byte COUNTER = 0;
//    public static final byte GATEWAY = 1;
//    public static final byte SEQUENCER = 2;
//    public static final byte MATCH = 3;

    ///////////////////////////////////////////////////


    public static final short COUNTER_CONNECT = 0;
    public static final short COUNTER_DISCONNECT = 1;
    public static final short COUNTER_NEW_ORDER = 2;
    public static final short COUNTER_CANCEL_ORDER = 3;
    public static final short COUNTER_PING = 4;

    public static final short COUNTER_MSG_TYPE_MIN = 0;
    public static final short COUNTER_MSG_TYPE_MAX = 5000;



    public static final short MATCH_ORDER_DATA = 5001;
    public static final short MATCH_HQ_DATA = 5002;

    public static final short MATCH_MSG_TYPE_MIN = 5001;
    public static final short MATCH_MSG_TYPE_MAX = 10000;

    public static final short ERROR = 10001;
    public static final short TEST = 10002;

    ///////////////////////////////////////////////////

    // Message Status
    public static final byte NORMAL = 0;
    public static final byte CS_NOT_MATCH = 1;
    public static final byte GW_OFF = 2;
    public static final byte CACHE_FULL = 3;
    public static final byte REJECTED = 4;


    //////////////////////Exchange Status///////////////////////
    public static final int ON = 1;

    public static final int OFF = 0;

}
