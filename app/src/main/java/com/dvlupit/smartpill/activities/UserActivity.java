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
import android.widget.Button;
import android.widget.EditText;
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


public class UserActivity extends Activity {

    private Connectivity con;
    Boolean isConnected;
    private String URL = "http://smartpill.noip.me:8080/UsuariosWS/login?", userId, name, userEmail;
    private EditText editTextEmailLogin, editTextPassLogin;
    private Button btLogin, btAddAcc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        editTextEmailLogin = (EditText) findViewById(R.id.editTextEmailLogin);
        editTextPassLogin = (EditText) findViewById(R.id.editTextPassLogin);
        btLogin = (Button)findViewById(R.id.btLogin);
        btAddAcc = (Button)findViewById(R.id.btAddAccount);

        btLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StrictMode.ThreadPolicy policy = new
                        StrictMode.ThreadPolicy.Builder()
                        .permitAll().build();
                StrictMode.setThreadPolicy(policy);
                con = new Connectivity(getApplicationContext());
                isConnected = con.isConnectingToInternet();
                if (isConnected)
                    logarCliente(v);
                else
                    Toast.makeText(UserActivity.this, "Você não está conectado a internet.Por favor ative uma conexão.", Toast.LENGTH_LONG).show();
            }
        });

        btAddAcc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(UserActivity.this, UserRegister.class );
                startActivity(i);
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_user, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void logarCliente(View view) {
        new Thread() {
            public void run() {
                postHttp(editTextEmailLogin.getText().toString(),editTextPassLogin.getText().toString());
            }
        }.start();
    }

    public void postHttp(String email,String senha) {
        HttpClient httpClient = new DefaultHttpClient();
        HttpPost httpPost = new HttpPost(URL
                +"email="+editTextEmailLogin.getText().toString()+
                "&"
                +"pass="+editTextPassLogin.getText().toString());

        try {
            ArrayList<NameValuePair> valores = new ArrayList<NameValuePair>();
            valores.add(new BasicNameValuePair("email", email));
            valores.add(new BasicNameValuePair("password", senha));


            httpPost.setEntity(new UrlEncodedFormEntity(valores));
            final HttpResponse resposta = httpClient.execute(httpPost);

            runOnUiThread(new Runnable() {
                public void run() {
                    try {

                        String jsonStr = EntityUtils.toString(resposta.getEntity());
                        Log.e("JSON", "Teste: "+ jsonStr.substring(3));
                        if (jsonStr != null) {
                            try {
                                JSONObject jsonObj = new JSONObject(jsonStr);

                                 userId = jsonObj.getString("id");
                                 name = jsonObj.getString("name");
                                 userEmail = jsonObj.getString("email");



                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }


                        if (userId != null) {
                            Toast.makeText(UserActivity.this, "Login efetuado com sucesso.\n "+"Bem Vindo "+ name, Toast.LENGTH_LONG).show();

                            SharedPreferences settings = getSharedPreferences("usuario",UserActivity.MODE_PRIVATE);
                            SharedPreferences.Editor editor = settings.edit();
                            editor.putString("email", userEmail);
                            editor.putString("userId", userId);
                            editor.putBoolean("logado",true);
                            editor.putString("name", name);
                            editor.commit();

                            Intent i = new Intent(UserActivity.this, MainActivity.class);
                            startActivity(i);

                        } else {
                            Log.d("Jason", "Não retornou jason");
                            Toast.makeText(UserActivity.this, "Usuário ou senha inválida. ", Toast.LENGTH_LONG).show();

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

