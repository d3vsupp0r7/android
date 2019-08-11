package org.sglba.trainman.util;

import org.joda.time.DateTime;
import org.joda.time.Duration;
import org.joda.time.Minutes;

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
        try {
            Date d1 = format.parse(dateStringDeparture);
            Date d2 = format.parse(dateStringArrival);
            DateTime dj1=new DateTime(d1);
            DateTime dj2=new DateTime(d2);

            Duration duration = new Duration(dj2,dj1);

            long minutes = duration.getStandardMinutes();
            long hours = minutes / 60;
            long minutesRemaining = minutes % 60;

            //in milliseconds
            dateFormatted=Long.toString(hours).replace("-","")+":"+Long.toString(minutesRemaining).replace("-","");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dateFormatted;

    }

    public static String formatCurrentDateForAPIService(){
        String dateFormatted="";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        //
        String pattern = "yyyy-MM-dd'T'HH:mm:ss";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        //
        dateFormatted=simpleDateFormat.format(date);
        return dateFormatted;
    }

}
