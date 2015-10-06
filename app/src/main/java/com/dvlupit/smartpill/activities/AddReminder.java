package com.dvlupit.smartpill.activities;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TimePicker;

import com.dvlupit.smartpill.R;
import com.dvlupit.smartpill.adapters.ReminderMedicinesAdapter;
import com.dvlupit.smartpill.entitys.Medicine;
import com.dvlupit.smartpill.entitys.Reminder;
import com.dvlupit.smartpill.entitys.ReminderSchedule;

import java.util.List;

public class AddReminder extends Activity {

    private List<Medicine> medList;
    private TimePicker timePickerReminder;
    private ListView listReminderMedicines;
    private ReminderSchedule reminderSchedule;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_reminder);

        timePickerReminder = (TimePicker)findViewById(R.id.timePickerReminder);
        listReminderMedicines = (ListView)findViewById(R.id.listViewReminderMedicine);
        medList = Medicine.listAll(Medicine.class);
        ListAdapter medAdapter = new ReminderMedicinesAdapter(this, medList);
        listReminderMedicines.setAdapter(medAdapter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_add_reminder, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_add_reminder) {
            String time = timePickerReminder.getCurrentHour().toString() + ":" + timePickerReminder.getCurrentMinute().toString();
            reminderSchedule = new ReminderSchedule(time);
            reminderSchedule.save();
            int row = listReminderMedicines.getFirstVisiblePosition();
            Medicine medicine = medList.get(row);
            Reminder rem = new Reminder(reminderSchedule,medicine);
            rem.save();
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
