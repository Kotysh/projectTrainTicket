package ru.dmitriykotyshov.other;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Дмитрий on 12.01.2018.
 */
public class MyDate {

    private final static String FORMAT_DATE = "dd/MM/yyyy";

    private MyDate(){

    }

    public static boolean get5Minute(Timestamp timestamp){

        return (System.currentTimeMillis() - timestamp.getTime())/1000/60 < 5;

    }

    public static String dateFormatAddDay(Date date){

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DATE, 1);
        return new SimpleDateFormat(FORMAT_DATE).format(calendar.getTime());

    }

    public static String dateFormat(Date date){

        return new SimpleDateFormat(FORMAT_DATE).format(date);

    }

}
