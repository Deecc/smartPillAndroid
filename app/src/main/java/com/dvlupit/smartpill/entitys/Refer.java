package com.dvlupit.smartpill.entitys;


import com.orm.SugarRecord;

/**
 * Created by deck on 18/08/15.
 */
public class Refer extends SugarRecord<Refer> {

    /**
     * Campos da tabela referencia
     */

    String reference;

    public Refer() {
    }

    public Refer(String reference) {
        this.reference = reference;
    }
}
