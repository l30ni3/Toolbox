package com.example.myapplication;

public class ClassListMachines
{

    public String machine_ID; //Maschinen_ID
    public String machine_name; //Name
    public String machine_serial; //Seriennummer
    public boolean available; //Verfügbarkeit

    public ClassListMachines(String machine_ID, String machine_name, String machine_serial, boolean available)
    {
        this.machine_ID = machine_ID;
        this.machine_name = machine_name;
        this.machine_serial = machine_serial;
        this.available = available;
    }


    public String getName() {
        return machine_ID;
    }

    public String getId() {
        return machine_name;
    }

    public String getSerial() {
        return machine_serial;
    }

    public boolean getAvailability() {
        return available;
    }
}