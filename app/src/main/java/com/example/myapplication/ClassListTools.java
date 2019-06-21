package com.example.myapplication;

public class ClassListTools
{

    public String name; //Name
    public String id; //ID
    public String loc; //Lagerort
    public boolean available; //Verfügbarkeit

    public ClassListTools(String name, String id, String loc, boolean available)
    {
        this.name = name;
        this.id = id;
        this.loc = loc;
        this.available = available;
    }


    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public String getLoc() {
        return loc;
    }

    public boolean getAvailability() {
        return available;
    }
}