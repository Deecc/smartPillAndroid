package com.dvlupit.smartpill.entitys;


import com.orm.SugarRecord;
import java.sql.Time;


/**
 * Created by deck on 18/08/15.
 */
public class ReminderSchedule extends SugarRecord<ReminderSchedule>{


    public String schedule;


    public ReminderSchedule() {
    }

    public ReminderSchedule(String schedule) {
        this.schedule = schedule;

    }
}
