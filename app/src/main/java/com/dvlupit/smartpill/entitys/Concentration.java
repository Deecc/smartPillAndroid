package com.dvlupit.smartpill.entitys;


import com.orm.SugarRecord;



/**
 * Created by deck on 18/08/15.
 */
public class Concentration extends SugarRecord<Concentration>{

    String concentration;

    public Concentration() {
    }

    public Concentration(String concentration) {
        this.concentration = concentration;
    }
}
