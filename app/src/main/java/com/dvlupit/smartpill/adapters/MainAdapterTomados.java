package com.dvlupit.smartpill.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.dvlupit.smartpill.R;
import com.dvlupit.smartpill.entitys.MedicineTaken;

import java.util.List;

/**
 * Created by deck on 24/08/15.
 */
public class MainAdapterTomados extends ArrayAdapter<MedicineTaken> {
    private LayoutInflater myInflater;

    public MainAdapterTomados(Context context, List<MedicineTaken> listTaken) {
        super(context, R.layout.main_list_tomados, listTaken);

    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater layoutInflater = LayoutInflater.from(getContext());
        View customView = layoutInflater.inflate(R.layout.main_list_tomados, parent, false);


        MedicineTaken takenItem = getItem(position);

        ImageView capsuleTomado = (ImageView) customView.findViewById(R.id.iconMainTomados);
        TextView medNameTomado = (TextView) customView.findViewById(R.id.labelMainMedListTomadosName);
        TextView medQtdTomado = (TextView) customView.findViewById(R.id.labelMainMedListTomadosQtd);



        /*
        String scheduleTime = remItem.reminderSchedule.schedule;

        String systemTime = new SimpleDateFormat("HH:mm").format(System.currentTimeMillis());
        SimpleDateFormat format = new SimpleDateFormat("HH:mm");
        try {
           Date date1 = format.parse(scheduleTime);
           Date date2 = format.parse(systemTime);
           long convertedData1 = date1.getTime();
           long convertedData2 = date2.getTime();
           if (convertedData1 > convertedData2){
               MedicineTaken medicine_taken;
               capsuleTomado.setImageResource(R.drawable.capsule);
               medNameTomado.setText(remItem.medicine.name);
               medQtdTomado.setText(String.valueOf(remItem.medicine.quantity));
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }*/
        capsuleTomado.setImageResource(R.drawable.capsule);
        medNameTomado.setText(takenItem.name);
        medQtdTomado.setText(String.valueOf(takenItem.quantity));

        return customView;

    }



}
