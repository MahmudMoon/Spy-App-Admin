package com.example.moonc.spyapp;

/**
 * Created by moonc on 11/14/2017.
 */

public class Contacts_Objects {
    String name ;
    String number;

    public Contacts_Objects() {
    }

    public Contacts_Objects(String name, String number) {
        this.name = name;
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}
