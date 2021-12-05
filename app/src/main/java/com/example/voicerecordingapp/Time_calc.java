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





        return null;
    }
}
