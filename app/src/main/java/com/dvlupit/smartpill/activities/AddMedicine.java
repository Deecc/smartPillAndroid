package com.dvlupit.smartpill.activities;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.dvlupit.smartpill.R;
import com.dvlupit.smartpill.Util.Connectivity;
import com.dvlupit.smartpill.entitys.ActiveIngredient;
import com.dvlupit.smartpill.entitys.Availability;
import com.dvlupit.smartpill.entitys.Manufacturer;
import com.dvlupit.smartpill.entitys.Medicine;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

public class AddMedicine extends Activity {
    Intent intent = new Intent();
    private Connectivity con;
    Boolean isConnected;

    private String URL = "http://smartpill.noip.me:8080/MedicamentosWS/setMedicine?", status, email;

    private Medicine medicine;

    private EditText medNome;
    private EditText medActiveIngred;
    private EditText medManufaturer;
    private EditText medAvailability;
    private EditText medQuantity;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_medicine);


        medNome = (EditText) findViewById(R.id.editTextMedNome);
        medActiveIngred = (EditText) findViewById(R.id.editTextActiveIngredient);
        medManufaturer = (EditText) findViewById(R.id.editTextMedFabricante);
        medAvailability = (EditText) findViewById(R.id.editTextAvailability);
        medQuantity= (EditText) findViewById(R.id.editTextMedQuantidade);

        SharedPreferences settings = getSharedPreferences("usuario", AddMedicine.MODE_PRIVATE);
        email = settings.getString("email", "");
        Log.e("Add Med", "Email shared: " + email);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_add_medicine, menu);
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
            addMedicine();
            intent = new Intent(AddMedicine.this, MainActivity.class);
            startActivityForResult(intent, 0);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void addMedicine() {
        StrictMode.ThreadPolicy policy = new
                StrictMode.ThreadPolicy.Builder()
                .permitAll().build();
        StrictMode.setThreadPolicy(policy);
        con = new Connectivity(getApplicationContext());
        isConnected = con.isConnectingToInternet();
        if(isConnected){
            new Thread() {
                public void run() {


                    postHttp(email, medNome.getText().toString(), medQuantity.getText().toString());
                }
            }.start();

        } else {
            Availability av = new Availability(medAvailability.getText().toString());
            av.save();
            Manufacturer manu = new Manufacturer(medManufaturer.getText().toString());
            manu.save();
            ActiveIngredient act = new ActiveIngredient(medActiveIngred.getText().toString());
            act.save();

            medicine = new Medicine(medNome.getText().toString(),
                    Integer.parseInt(medQuantity.getText().toString()),
                    null,
                    null,
                    manu,
                    av,
                    null,
                    act);
            medicine.save();
        }
    }

    private void postHttp(String email, String name, String quantity) {
        HttpClient httpClient = new DefaultHttpClient();
        HttpPost httpPost = new HttpPost(URL
                +"email="+email+
                "&"
                +"name="+medNome.getText().toString()
                +"&"
                +"quantity="+medQuantity.getText().toString()
        );


        try {
            ArrayList<NameValuePair> valores = new ArrayList<NameValuePair>();
            valores.add(new BasicNameValuePair("email", email));
            valores.add(new BasicNameValuePair("name", name));
            valores.add(new BasicNameValuePair("quantity",quantity));


            httpPost.setEntity(new UrlEncodedFormEntity(valores));
            final HttpResponse resposta = httpClient.execute(httpPost);

            runOnUiThread(new Runnable() {
                public void run() {
                    try {

                        String jsonStr = EntityUtils.toString(resposta.getEntity());
                        Log.e("JsonString", "String: "+jsonStr);
                        if (jsonStr.matches("Funcionou")) {
                            Toast.makeText(AddMedicine.this, "Medicamento adicionado com sucesso! ", Toast.LENGTH_LONG).show();

                            Intent i = new Intent(AddMedicine.this, MainActivity.class);
                            startActivity(i);

                        } else {
                            Log.d("Jason", "Não retornou jason");
                        }
                    } catch (IOException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
            });
        } catch (ClientProtocolException e) {
        } catch (IOException e) {
        }

    }
}
