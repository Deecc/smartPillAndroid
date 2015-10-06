package com.dvlupit.smartpill.activities;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.Toast;

import com.dvlupit.smartpill.R;
import com.dvlupit.smartpill.Util.Connectivity;
import com.dvlupit.smartpill.entitys.User;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

public class UserRegister extends Activity {

    private AlertDialog alertPass;

    private Connectivity con;
    Boolean isConnected;
    private String URL = "http://smartpill.noip.me:8080/UsuariosWS/setUser?";

    private EditText nameUser;
    private EditText emailUser;
    private EditText passUser;
    private EditText pass2User;
    private ScrollView userRegisterScroll;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_register);

        //Componentes
        nameUser = (EditText)findViewById(R.id.editTextNameUser);
        emailUser = (EditText)findViewById(R.id.editTextEmailUser);
        passUser = (EditText)findViewById(R.id.editTextPassUser);
        pass2User = (EditText)findViewById(R.id.editTextPass2User);
        userRegisterScroll = (ScrollView)findViewById(R.id.scrollViewUserRegister);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_user_register, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_add_user) {
            if (isPassValid(passUser, pass2User)) {
                StrictMode.ThreadPolicy policy = new
                        StrictMode.ThreadPolicy.Builder()
                        .permitAll().build();
                StrictMode.setThreadPolicy(policy);
                con = new Connectivity(getApplicationContext());
                isConnected = con.isConnectingToInternet();
                if (isConnected)
                    addUser(this.findViewById(android.R.id.content));
                else
                    Toast.makeText(UserRegister.this, "Você não está conectado a internet.Por favor ative uma conexão.", Toast.LENGTH_LONG).show();

            }
            else alertPass();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public boolean isPassValid(EditText tx1, EditText tx2){
        if (tx1.getText().toString().equals(tx2.getText().toString()))
            return true;
        else return false;
    }

    private void alertPass() {
        LayoutInflater li = getLayoutInflater();
        View view = li.inflate(R.layout.alert_pass, null);
        //definimos para o botão do layout um clickListener
        view.findViewById(R.id.bt).setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                //exibe um Toast informativo.
                Toast.makeText(UserRegister.this, "alerta.dismiss()", Toast.LENGTH_SHORT).show();
                //desfaz o alerta.
                alertPass.dismiss();
            }
        });

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Senhas diferentes");
        builder.setView(view);
        alertPass = builder.create();
        alertPass.show();
    }

    public void addUser(View view) {
        new Thread() {
            public void run() {
                postHttp(emailUser.getText().toString(), nameUser.getText().toString(),passUser.getText().toString());
            }
        }.start();
    }

    public void postHttp(String email, String userName,String pass) {
        HttpClient httpClient = new DefaultHttpClient();
        HttpPost httpPost = new HttpPost(URL
                +"email="+emailUser.getText().toString()
                +"&"
                +"name="+nameUser.getText().toString()
                +"&"
                +"pass="+passUser.getText().toString());

        try {
            ArrayList<NameValuePair> valores = new ArrayList<NameValuePair>();
            valores.add(new BasicNameValuePair("email", email));
            valores.add(new BasicNameValuePair("name", userName));
            valores.add(new BasicNameValuePair("pass", pass));


            httpPost.setEntity(new UrlEncodedFormEntity(valores));
            final HttpResponse resposta = httpClient.execute(httpPost);

            runOnUiThread(new Runnable() {
                public void run() {
                    try {

                        String jsonStr = EntityUtils.toString(resposta.getEntity());

                        if (jsonStr != null) {
                            Toast.makeText(UserRegister.this, "Cadastro realizado com sucesso!\n "+"Você será redirecionado a tela de login.", Toast.LENGTH_LONG).show();

                            Intent i = new Intent(UserRegister.this, UserActivity.class);
                            startActivity(i);

                        } else {

                            Toast.makeText(UserRegister.this, "Ocorreu um erro ao processar. ", Toast.LENGTH_LONG).show();

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
