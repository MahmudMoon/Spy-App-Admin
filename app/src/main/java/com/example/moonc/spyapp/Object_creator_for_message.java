package com.example.moonc.spyapp;

/**
 * Created by moonc on 11/15/2017.
 */

public class Object_creator_for_message {
    String number;
    String message;
    String date;

    public Object_creator_for_message(String number, String message, String date) {
        this.number = number;
        this.message = message;
        this.date = date;
    }

    public Object_creator_for_message() {
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
