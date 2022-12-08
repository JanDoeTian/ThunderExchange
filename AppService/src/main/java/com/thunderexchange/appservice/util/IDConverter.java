package com.thunderexchange.appservice.util;

public class IDConverter {

    public static long combineInt2Long(int high, int low) {
        return ((long) high << 32 & 0xFFFFFFFF00000000L) | ((long) low & 0xFFFFFFFFL);
    }

    public static int[] seperateLong2Int(long val) {
        int[] res = new int[2];
        res[1] = (int) (0xFFFFFFFFL & val);//低位
        res[0] = (int) ((0xFFFFFFFF00000000L & val) >> 32);//高位
        return res;
    }

}
