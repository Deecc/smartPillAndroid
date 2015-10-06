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

import java.util.List;

/**
 * Created by deck on 24/08/15.
 */
public class ReminderMedicinesAdapter extends ArrayAdapter<Medicine> {

    private LayoutInflater myInflater;

    public ReminderMedicinesAdapter(Context context, List<Medicine> listMedicine) {
        super(context, R.layout.reminder_medicine_list, listMedicine);

    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater layoutInflater = LayoutInflater.from(getContext());
        View customView = layoutInflater.inflate(R.layout.reminder_medicine_list, parent, false);

        Medicine medItem = getItem(position);

        TextView medName = (TextView) customView.findViewById(R.id.labelRemMedListName);


        // capsule.setImageResource(R.drawable.capsule);
        medName.setText(medItem.name);


        return customView;
    }
}
