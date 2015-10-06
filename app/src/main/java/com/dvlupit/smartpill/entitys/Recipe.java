package com.dvlupit.smartpill.entitys;


import android.widget.ImageView;

import com.orm.SugarRecord;

import java.util.Date;

/**
 * Created by deck on 19/08/15.
 */
public class Recipe extends SugarRecord<Recipe> {

    public String recipePath;
    public String name;
    public Date date;

    public Recipe() {
    }

    public Recipe(String recipePath, String name, Date date) {
        this.recipePath = recipePath;
        this.name = name;
        this.date = date;
    }
}
