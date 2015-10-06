package com.dvlupit.smartpill.activities;


import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.dvlupit.smartpill.R;
import com.dvlupit.smartpill.Util.Connectivity;
import com.dvlupit.smartpill.adapters.MainAdapterTomados;
import com.dvlupit.smartpill.adapters.MainAdapterTomar;
import com.dvlupit.smartpill.adapters.MedicineAdapter;
import com.dvlupit.smartpill.entitys.Medicine;
import com.dvlupit.smartpill.entitys.MedicineTaken;
import com.dvlupit.smartpill.entitys.Recipe;
import com.dvlupit.smartpill.entitys.Reminder;
import com.dvlupit.smartpill.entitys.ReminderSchedule;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MainActivity extends Activity {

    Intent intent = new Intent();

    private Connectivity con;
    Boolean isConnected;

    private boolean logado;

    private ListView listTomados;
    private ListView listTomar;


    private List<Reminder> reminders;
    private List<Medicine> medicines;
    private List<MedicineTaken> medicine_takens;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedPreferences settings = getSharedPreferences("usuario", MainActivity.MODE_PRIVATE);
        logado = settings.getBoolean("logado", false);

        if(logado){
            listTomados = (ListView)findViewById(R.id.listViewRemediosTomados);
            listTomar = (ListView)findViewById(R.id.listViewRemediosTomar);

            //zeraTudo();

            medicines = Medicine.listAll(Medicine.class);
            medicine_takens = MedicineTaken.listAll(MedicineTaken.class);



            if(medicines.isEmpty()) {
                Toast.makeText(MainActivity.this, "Você não possui medicamentos!", Toast.LENGTH_LONG).show();
                intent = new Intent(MainActivity.this, AddMedicine.class);
                startActivityForResult(intent, 0);
            } else {
                ListAdapter adapterTomar = new MainAdapterTomar(this,medicines);
                listTomar.setAdapter(adapterTomar);
                listTomar.setOnItemClickListener(listenerTomar);




                final ListAdapter adapterTomados = new MainAdapterTomados(this, medicine_takens);
                listTomados.setAdapter(adapterTomados);

                listTomados.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                    @Override
                    public void onItemClick(AdapterView<?> parent, View view,
                                            final int position, long id) {
                        MedicineTaken medTake = (MedicineTaken)listTomados.getAdapter().getItem(position);
                        discardMedicine(medTake);


                    }
                });



            }
        } else {
            Intent i = new Intent(MainActivity.this, UserActivity.class);
            startActivity(i);
        }

}

    private void zeraTudo() {
        Medicine.deleteAll(Medicine.class);
        MedicineTaken.deleteAll(MedicineTaken.class);
        Reminder.deleteAll(Reminder.class);
        Recipe.deleteAll(Recipe.class);
        ReminderSchedule.deleteAll(ReminderSchedule.class);
    }

    private void takeMedicine(Medicine medicine){
            MedicineTaken medTaken = new MedicineTaken(medicine.name, medicine.quantity, medicine.refers, medicine.concentrations,medicine.manufacturers, medicine.availabilities, medicine.categories, medicine.activeIngredients);
            medTaken.quantity += 1;
            medicine.quantity -= 1;
            medTaken.save();
            medicine.save();


   }

    private void discardMedicine(MedicineTaken medicineTaken){
        //Medicine medicine = new Medicine(medicineTaken.name, medicineTaken.quantity,medicineTaken.refers,medicineTaken.concentrations,medicineTaken.manufacturers,medicineTaken.availabilities,medicineTaken.categories,medicineTaken.activeIngredients);
        medicineTaken.quantity -= 1;
        medicineTaken.save();



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch (item.getItemId()) {

            case R.id.action_addMedicine:
                intent = new Intent(MainActivity.this, AddMedicine.class);
                startActivityForResult(intent, 0);
                return true;

            case R.id.action_schedule:
                intent = new Intent(MainActivity.this, ReminderActivity.class);
                startActivityForResult(intent, 0);
                return true;

            case R.id.action_box:
                intent = new Intent(MainActivity.this, MedicineActivity.class);
                startActivityForResult(intent, 0);
                return true;

            case R.id.action_extra:
                intent = new Intent(MainActivity.this, ExtrasActivity.class);
                startActivityForResult(intent, 0);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private AdapterView.OnItemClickListener listenerTomar = new AdapterView.OnItemClickListener() {

        @Override
        public void onItemClick(AdapterView<?> parent, View view,
                                final int position, long id) {
            Log.e("Tomar", "Ok detectou o click");
            Medicine medItem = (Medicine) listTomar.getAdapter().getItem(position);
            takeMedicine(medItem);


        }
    };

}

