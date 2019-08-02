package com.example.oraritreni;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {
    //Classe di utilità che si occupa di convertire il formato data string della response in un formato adatto alla UI.
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
