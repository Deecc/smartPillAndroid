package com.dvlupit.smartpill.database;

/**
 * Created by deck on 18/08/15.
 */
public class Connection {
    private static Connection smartpillwsDB = new Connection();

    public static Connection getInstance() {
        return smartpillwsDB;
    }

    private Connection() {
    }
}
