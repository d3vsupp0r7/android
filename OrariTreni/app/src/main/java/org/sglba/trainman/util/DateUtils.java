package org.sglba.trainman.util;

import org.joda.time.DateTime;
import org.joda.time.Duration;
import org.joda.time.Hours;
import org.joda.time.Interval;
import org.joda.time.Minutes;
import org.joda.time.Period;
import org.joda.time.format.PeriodFormatter;
import org.joda.time.format.PeriodFormatterBuilder;
import org.sglba.trainman.costraints.DatePatternFormatterCostraintEnum;

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
        SimpleDateFormat format = new SimpleDateFormat(DatePatternFormatterCostraintEnum.US_DATE_PATTERN_WITH_TIME.getValue());
        String dateFormatted="";
        try {
            Date d1 = format.parse(dateStringDeparture);
            Date d2 = format.parse(dateStringArrival);

            DateTime dj1 = new DateTime(d1);
            DateTime dj2 = new DateTime(d2);

            Period p = new Period(dj1, dj2);
            String formattedHour =  ""+p.getHours();
            String formattedMinutes =  ""+p.getMinutes();

            if(p.getHours()<10){
                formattedHour = "0"+p.getHours();
            }

            if(p.getMinutes()<10){
                formattedMinutes = "0"+p.getMinutes();
            }

            dateFormatted = formattedHour+":"+formattedMinutes;

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
