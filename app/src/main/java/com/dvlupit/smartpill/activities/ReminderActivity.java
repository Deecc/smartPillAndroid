package com.dvlupit.smartpill.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.dvlupit.smartpill.R;
import com.dvlupit.smartpill.adapters.ReminderAdapter;
import com.dvlupit.smartpill.entitys.Reminder;

import java.util.List;


public class ReminderActivity extends Activity {

    Intent intent;

    private ListView listReminders;
    private List<Reminder> reminders;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reminder);

        listReminders = (ListView) findViewById(R.id.listViewRemindeSchedule);
        reminders = Reminder.listAll(Reminder.class);
        if (reminders.isEmpty()) {
            Toast.makeText(this, "Você ainda não possui lembretes!!", Toast.LENGTH_LONG).show();
            intent = new Intent(ReminderActivity.this, AddReminder.class);
            startActivityForResult(intent, 0);
        } else {
            ListAdapter reminderAdapter = new ReminderAdapter(this, reminders);
            listReminders.setAdapter(reminderAdapter);
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_reminder, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_reminder) {
            intent = new Intent(ReminderActivity.this, AddReminder.class);
            startActivityForResult(intent, 0);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
