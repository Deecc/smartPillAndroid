package com.dvlupit.smartpill.entitys;

import com.orm.SugarRecord;

import java.sql.Date;

/**
 * Created by deck on 19/08/15.
 */
public class UsersMedicine extends SugarRecord<UsersMedicine> {

    Date expiryDate;
    int quantity;

    User user;
    Medicine medicine;

    public UsersMedicine() {
    }

    public UsersMedicine(Date expiryDate, int quantity, User user, Medicine medicine) {
        this.expiryDate = expiryDate;
        this.quantity = quantity;
        this.user = user;
        this.medicine = medicine;
    }
}
