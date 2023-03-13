package com.example.myapplication3.models;

public class Day {
    private int year;
    private int month;
    private int day;
    private String checkInTime;
    private String checkOutTime;
    private String name;

    public Day(int year, int month, int day, String checkInTime, String checkOutTime, String name) {
        this.year = year;
        this.month = month;
        this.day = day;
        this.checkInTime = checkInTime;
        this.checkOutTime = checkOutTime;
        this.name = name;
    }

    public int getYear() {
        return year;
    }

    public int getMonth() {
        return month;
    }

    public int getDay() {
        return day;
    }

    public String getCheckInTime() {
        return checkInTime;
    }

    public String getCheckOutTime() {
        return checkOutTime;
    }

    public String getName() {
        return name;
    }


    public char[] getHours() {
        int checkInHour = Integer.parseInt(checkInTime.substring(0, 2));
        int checkInMinute = Integer.parseInt(checkInTime.substring(3, 5));
        int checkOutHour = Integer.parseInt(checkOutTime.substring(0, 2));
        int checkOutMinute = Integer.parseInt(checkOutTime.substring(3, 5));

        int workedMinutes = (checkOutHour - checkInHour) * 60 + checkOutMinute - checkInMinute;
        int workedHours = workedMinutes / 60;
        int workedMinutesLeft = workedMinutes % 60;

        return (workedHours + "h " + workedMinutesLeft + "m").toCharArray();
    }
}
