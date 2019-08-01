package com.example.oraritreni;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {
    public static String formatDate(String dateString){
        String dateFormatted="";
        try {
            String pattern = "yyyy-MM-dd'T'HH:mm:ss";
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
            Date date = simpleDateFormat.parse(dateString);
            DateFormat dateFormat = new SimpleDateFormat("HH:mm");
            dateFormatted=dateFormat.format(date);
        }catch(ParseException e){
            e.printStackTrace();
        }
        return dateFormatted;
    }
}
