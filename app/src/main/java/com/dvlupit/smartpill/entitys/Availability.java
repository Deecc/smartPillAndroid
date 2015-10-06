package com.dvlupit.smartpill.entitys;


import com.orm.SugarRecord;

/**
 * Created by deck on 18/08/15.
 */
public class Availability extends SugarRecord<Availability>{

    /**
     * Campos da tabela de disponibilidade.
     */

    String availability;

    public Availability() {
    }

    public Availability(String availability) {
        this.availability = availability;
    }
}
