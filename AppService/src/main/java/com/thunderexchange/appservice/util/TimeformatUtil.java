package com.thunderexchange.appservice.util;


import org.apache.commons.lang3.time.DateFormatUtils;

import java.util.Date;

public class TimeformatUtil {

    private static final String YYYY_MM_DD = "yyyyMMdd";

    private static final String HH_MM_SS = "HH:mm:ss";

    private static final String YYYY_MM_DD_HH_MM_SS = "yyyyMMdd HH:mm:ss";

    public static String yyyyMMdd(Date date){
        return DateFormatUtils.format(date,YYYY_MM_DD);
    }

    public static String yyyyMMdd(long timestamp){
        return DateFormatUtils.format(timestamp,YYYY_MM_DD);
    }

    public static String hhMMss(Date date){
        return DateFormatUtils.format(date,HH_MM_SS);
    }

    public static String hhMMss(long timestamp){
        return DateFormatUtils.format(timestamp,HH_MM_SS);
    }


    public static String yyyyMMddHHmmss(Date date){
        return DateFormatUtils.format(date,YYYY_MM_DD_HH_MM_SS);
    }

}
