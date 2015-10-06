package com.dvlupit.smartpill.entitys;

import com.orm.SugarRecord;



/**
 * Created by deck on 18/08/15.
 */
public class Manufacturer extends SugarRecord<Manufacturer> {

    String manufacturer;

    public Manufacturer() {
    }

    public Manufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }
}
