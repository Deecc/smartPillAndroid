package com.dvlupit.smartpill.activities;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.dvlupit.smartpill.R;

public class LoggedActivity extends Activity {

    private String nomeUser,email;
    private boolean logado;
    private Button btSair;
    private TextView txUserName,txUserEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logged);



        txUserName = (TextView)findViewById(R.id.textViewUserLogged);
        txUserEmail = (TextView)findViewById(R.id.textViewEmailLogged);
        btSair = (Button)findViewById(R.id.btLogout);

        btSair.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sairConta(v);
            }
        });

        SharedPreferences settings = getSharedPreferences("usuario", LoggedActivity.MODE_PRIVATE);

        nomeUser = settings.getString("name", "");
        email = settings.getString("email","");
        logado = settings.getBoolean("logado", false);

        txUserName.setText(nomeUser);
        txUserEmail.setText(email);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_logged, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_backMain) {
            Intent i = new Intent(LoggedActivity.this, MainActivity.class);
            startActivity(i);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void sairConta(View view) {
        SharedPreferences settings = getSharedPreferences("usuario", LoggedActivity.MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();
        editor.clear();
        editor.commit();
        Intent i = new Intent(LoggedActivity.this, MainActivity.class);
        startActivity(i);

    }
}
