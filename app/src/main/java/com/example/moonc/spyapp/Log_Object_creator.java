package com.example.moonc.spyapp;

/**
 * Created by moonc on 11/15/2017.
 */

public class Log_Object_creator {
    String number;
    String time;

    public Log_Object_creator() {
    }





    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Log_Object_creator( String number, String time) {
        this.number = number;
        this.time = time;
    }
}
