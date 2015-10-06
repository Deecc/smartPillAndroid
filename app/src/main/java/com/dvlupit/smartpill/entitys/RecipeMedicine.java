package com.dvlupit.smartpill.entitys;

import com.orm.SugarRecord;

/**
 * Created by deck on 19/08/15.
 */
public class RecipeMedicine extends SugarRecord<RecipeMedicine> {

    Recipe recipe;
    Medicine medicine;

    public RecipeMedicine() {
    }

    public RecipeMedicine(Recipe recipe, Medicine medicine) {
        this.recipe = recipe;
        this.medicine = medicine;
    }
}
