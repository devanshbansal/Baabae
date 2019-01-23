package com.example.bansal.baabae.Utility;

import java.security.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

public class UtilityFunctions {


    public static String generateUniqueID(){

        return UUID.randomUUID().toString();
    }

    public static String generateTimeStamp(){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy-hh-mm-ss");
       return simpleDateFormat.format(new Date());
    }

    public static String convertTimeStampIntoDate(Timestamp ts){

     return new SimpleDateFormat("yyyyMMdd").format(ts);
    }
}
