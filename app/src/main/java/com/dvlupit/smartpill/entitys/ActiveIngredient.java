package com.dvlupit.smartpill.entitys;

import com.orm.SugarRecord;


/**
 * Created by deck on 18/08/15.
 */
public class ActiveIngredient extends SugarRecord<ActiveIngredient> {


    /**
     * Campos da tabela de Ingredientes Ativos;
     */
    String activeIngredient;

    public ActiveIngredient() {
    }

    public ActiveIngredient(String activeIngredient) {
        this.activeIngredient = activeIngredient;
    }


}
