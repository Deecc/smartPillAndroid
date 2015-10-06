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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by deck on 24/08/15.
 */
public class MainAdapterTomar extends ArrayAdapter<Medicine> {
    private LayoutInflater myInflater;

    public MainAdapterTomar(Context context, List<Medicine> listMedicine) {
        super(context, R.layout.main_list_tomar, listMedicine);

    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater layoutInflater = LayoutInflater.from(getContext());
        View customView = layoutInflater.inflate(R.layout.main_list_tomar, parent, false);


        Medicine medItem = getItem(position);

        ImageView capsuleTomar = (ImageView) customView.findViewById(R.id.iconMainTomar);
        TextView medNameTomar = (TextView) customView.findViewById(R.id.labelMainMedListTomarName);
        TextView medQtdTomar = (TextView) customView.findViewById(R.id.labelMainMedListTomarQtd);

        capsuleTomar.setImageResource(R.drawable.capsule);
        medNameTomar.setText(medItem.name);
        medQtdTomar.setText(String.valueOf(medItem.quantity));

        /*String scheduleTime = medItem.reminderSchedule.schedule;
        String systemTime = new SimpleDateFormat("HH:mm").format(System.currentTimeMillis());
        SimpleDateFormat format = new SimpleDateFormat("HH:mm");
        try {
            Date date1 = format.parse(scheduleTime);
            Date date2 = format.parse(systemTime);
            long convertedData1 = date1.getTime();
            long convertedData2 = date2.getTime();
            if (convertedData1 < convertedData2) {
                capsuleTomar.setImageResource(R.drawable.capsule);
                medNameTomar.setText(remItem.medicine.name);
                medQtdTomar.setText(String.valueOf(remItem.medicine.quantity));
            }

        } catch (ParseException e) {
            e.printStackTrace();
        }*/

        return customView;
    }
}
