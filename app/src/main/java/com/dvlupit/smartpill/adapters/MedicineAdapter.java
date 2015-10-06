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
 * Created by deck on 22/08/15.
 */
 public class MedicineAdapter extends ArrayAdapter<Medicine> {

    private LayoutInflater myInflater;

    public MedicineAdapter(Context context, List<Medicine> listMedicine) {
        super(context, R.layout.medicines_list, listMedicine);

    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater layoutInflater = LayoutInflater.from(getContext());
        View customView = layoutInflater.inflate(R.layout.medicines_list, parent, false);

        Medicine medItem = getItem(position);

        ImageView capsule = (ImageView) customView.findViewById(R.id.icon);
        TextView medName = (TextView) customView.findViewById(R.id.labelMedListName);
        TextView medQtd = (TextView) customView.findViewById(R.id.labelMedListQtd);

       // capsule.setImageResource(R.drawable.capsule);
        medName.setText(medItem.name);
        medQtd.setText(String.valueOf(medItem.quantity));

        return customView;
    }

}



