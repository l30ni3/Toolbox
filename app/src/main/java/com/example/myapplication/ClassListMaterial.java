package com.example.myapplication;

public class ClassListMaterial
{

    public String material_ID; //Material_ID
    public String material_name; //Name
    public String material_loc; //Lagerort
    public Integer amount; //Bestand

    public ClassListMaterial(String material_ID, String material_name, String material_loc, Integer amount)
    {
        this.material_ID = material_ID;
        this.material_name = material_name;
        this.material_loc = material_loc;
        this.amount = amount;
    }


    public String getId() {
        return material_ID;
    }

    public String getName() {
        return material_name;
    }

    public String material_loc() {
        return material_loc;
    }

    public Integer amount() {
        return amount;
    }
}