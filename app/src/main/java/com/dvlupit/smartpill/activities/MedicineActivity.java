package com.dvlupit.smartpill.activities;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.dvlupit.smartpill.R;
import com.dvlupit.smartpill.adapters.MedicineAdapter;
import com.dvlupit.smartpill.entitys.Medicine;

import java.util.List;

import static android.R.layout.simple_list_item_1;

public class MedicineActivity extends Activity {

    Intent intent;
    private List<Medicine> medicines;
    private ListView listMedicines;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medicine);

        //Local
        listMedicines = (ListView)findViewById(R.id.listViewMedicines);
        medicines = Medicine.listAll(Medicine.class);
        ListAdapter medAdapter = new MedicineAdapter(this, medicines);
        listMedicines.setAdapter(medAdapter);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_medicine, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_add_medicine) {
            intent = new Intent(MedicineActivity.this, AddMedicine.class);
            startActivityForResult(intent, 0);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }




}
