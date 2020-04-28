package com.item.vote.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {


    public static final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public  static String dateToString(Date date){
           if(date == null){
               return null;
           }
           return simpleDateFormat.format(date);

    }



}
