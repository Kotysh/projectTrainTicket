package ru.dmitriykotyshov.other;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Дмитрий on 12.01.2018.
 */
public class MyDate {

    private int year;
    private int month;
    private int day;

    public MyDate(int year, int month, int day) {
        this.year = year;
        this.month = month;
        this.day = day;
    }

    public MyDate(String date){

        this.day = Integer.valueOf(date.substring(0,2));
        this.month = Integer.valueOf(date.substring(3,5));
        this.year = Integer.valueOf(date.substring(6,10));

    }

    public static boolean get5Minute(Timestamp timestamp){

        Date one = new Date();
        Date two = new Date(timestamp.getTime());
        long rz = two.getTime()-one.getTime();
        rz /= 1000;
        if (rz > 300){
            return true;
        }

        return false;

    }

    @Override
    public String toString() {
        return year+"-"+month+"-"+day;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }
}
