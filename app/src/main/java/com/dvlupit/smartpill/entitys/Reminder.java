package com.dvlupit.smartpill.entitys;

import com.orm.SugarRecord;

/**
 * Created by deck on 19/08/15.
 */
public class Reminder extends SugarRecord <Reminder> {

    public ReminderSchedule reminderSchedule;
    public Medicine medicine;

    public Reminder() {
    }

    public Reminder(ReminderSchedule reminderSchedule, Medicine medicine) {
        this.reminderSchedule = reminderSchedule;
        this.medicine = medicine;
    }
}
