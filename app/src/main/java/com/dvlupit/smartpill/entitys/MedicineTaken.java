package com.dvlupit.smartpill.entitys;

import com.orm.SugarRecord;

/**
 * Created by deck on 24/08/15.
 */
public class MedicineTaken extends SugarRecord<MedicineTaken> {
    public String name;
    public int quantity;

    public Refer refers;
    public Concentration concentrations;
    public Manufacturer manufacturers;
    public Availability availabilities;
    public Category categories;
    public ActiveIngredient activeIngredients;

    //public Medicine medicine;

    public MedicineTaken() {
    }

    //public MedicineTaken(Medicine medicine){
       // this.medicine = medicine;
    //}

    public MedicineTaken(String name, int quantity, Refer refers, Concentration concentrations, Manufacturer manufacturers, Availability availabilities, Category categories, ActiveIngredient activeIngredients) {
        this.name = name;
        this.quantity = quantity;
        this.refers = refers;
        this.concentrations = concentrations;
        this.manufacturers = manufacturers;
        this.availabilities = availabilities;
        this.categories = categories;
        this.activeIngredients = activeIngredients;
    }
}
