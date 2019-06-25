package com.example.myapplication;

public class ClassListMachines
{

    public String machine_ID; //Maschinen_ID
    public String machine_name; //Name
    public String machine_loc; //Lagerort
    public boolean available; //Verfügbarkeit

    public ClassListMachines(String machine_ID, String machine_name, String loc, boolean available)
    {
        this.machine_ID = machine_ID;
        this.machine_name = machine_name;
        this.machine_loc = loc;
        this.available = available;
    }


    public String getId() {
        return machine_ID;
    }

    public String getName() {
        return machine_name;
    }

    public String getLoc() {
        return machine_loc;
    }

    public boolean getAvailability() {
        return available;
    }
}