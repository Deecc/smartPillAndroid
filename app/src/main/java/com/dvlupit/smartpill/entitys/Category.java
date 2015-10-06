package com.dvlupit.smartpill.entitys;

import com.orm.SugarRecord;



/**
 * Created by deck on 18/08/15.
 */
public class Category extends SugarRecord <Category> {

   String category;

    public Category() {
    }

    public Category(String category) {
        this.category = category;
    }
}
