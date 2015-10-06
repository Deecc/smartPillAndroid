package com.dvlupit.smartpill.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.dvlupit.smartpill.R;
import com.dvlupit.smartpill.entitys.Medicine;
import com.dvlupit.smartpill.entitys.Reminder;

import java.util.List;

/**
 * Created by deck on 24/08/15.
 */
public class ReminderAdapter extends ArrayAdapter<Reminder> {
    private LayoutInflater myInflater;

    public ReminderAdapter(Context context, List<Reminder> listReminder) {
        super(context, R.layout.reminder_list, listReminder);

    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater layoutInflater = LayoutInflater.from(getContext());
        View customView = layoutInflater.inflate(R.layout.reminder_list, parent, false);

        Reminder remItem = getItem(position);

        ImageView picture = (ImageView) customView.findViewById(R.id.reminder_clock);
        TextView reminderMed = (TextView) customView.findViewById(R.id.labelReminderMed);
        TextView reminderTime = (TextView) customView.findViewById(R.id.labelReminderTime);



        picture.setImageResource(R.drawable.clock);
        reminderMed.setText(remItem.medicine.name);
        reminderTime.setText(remItem.reminderSchedule.schedule);


        return customView;
    }
}
