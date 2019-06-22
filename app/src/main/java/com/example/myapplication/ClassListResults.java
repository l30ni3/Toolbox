package com.example.myapplication;

public class ClassListResults
{

    public String name; //Name
    public String loc; //Lagerort
    public boolean available; //Verfügbarkeit

    public ClassListResults(String name, String loc, boolean available)
    {
        this.name = name;
        this.loc = loc;
        this.available = available;
    }


    public String getName() {
        return name;
    }

    public String getLoc() {
        return loc;
    }

    public boolean getAvailability() {
        return available;
    }
}