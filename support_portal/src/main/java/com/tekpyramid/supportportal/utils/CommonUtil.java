package com.tekpyramid.supportportal.utils;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class CommonUtil {

    private CommonUtil() {}
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern(CommonConstants.DD_MM_YYYY_T_HH_MM_SS_SSS_Z);


    public static String getCurrentDate() {
        return formatter.format(LocalDateTime.now(ZoneId.of(CommonConstants.ZONE)));
    }

}

