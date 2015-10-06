package com.dvlupit.smartpill.entitys;

import com.orm.SugarRecord;

/**
 * Created by deck on 18/08/15.
 */

public class User extends SugarRecord<User> {

    String pass;
    String email;

    public User() {
    }

    public User(String email, String pass) {
        this.email = email;
        this.pass = pass;
    }
}
