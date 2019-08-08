package org.sglba.trainman.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

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

    public static String formatDateToUE(String dateString){
        String dateFormatted="";
        try {
            DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date date = sdf.parse(dateString);
            //
            String pattern = "dd-MM-yyyy";
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
            //
            dateFormatted=simpleDateFormat.format(date);
        }catch(ParseException e){
            e.printStackTrace();
        }
        return dateFormatted;
    }

    public static String calculateDurationTime(String dateStringDeparture,String dateStringArrival) {
        //HH converts hour in 24 hours format (0-23), day calculation
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        String dateFormatted="";
        Date d1 = null;
        Date d2 = null;
        try {
            d1 = format.parse(dateStringDeparture);
            d2 = format.parse(dateStringArrival);

            //in milliseconds
            long diff = d2.getTime() - d1.getTime();

            long diffSeconds = diff / 1000 % 60;
            long diffMinutes = diff / (60 * 1000) % 60;
            long diffHours = diff / (60 * 60 * 1000) % 24;
            long diffDays = diff / (24 * 60 * 60 * 1000);

            Date h = new Date(diff);
            DateFormat dateFormat = new SimpleDateFormat("HH:mm");
            dateFormatted=dateFormat.format(h);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return dateFormatted;

    }
}
