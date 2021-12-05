package com.example.voicerecordingapp;

import java.util.Date;
import java.util.concurrent.TimeUnit;

public class Time_calc {
    public String get_time_ago(long duration)
    {
        Date now = new Date();

        long second = TimeUnit.MILLISECONDS.toSeconds(now.getTime() - duration);
        long minute = TimeUnit.MILLISECONDS.toMinutes(now.getTime() - duration);
        long hour = TimeUnit.MILLISECONDS.toHours(now.getTime() - duration);
        long day = TimeUnit.MILLISECONDS.toDays(now.getTime() - duration);
        if (second<60)
        {
            return "Just Now";
        }else if (minute == 1)
        {
            return "A minute ago";
        }else if (minute > 1 && minute <60)
        {
            return minute + " Minutes ago";
        }else if (hour == 1)
        {
            return "A hour ago";
        }else if (hour > 1 && hour <24)
        {
            return hour + " Hours ago";
        }else if (day == 1)
        {
            return "A day ago";
        }else if (day > 1 && day <60)
        {
            return day + " Day ago";
        }




        return null;
    }
}
