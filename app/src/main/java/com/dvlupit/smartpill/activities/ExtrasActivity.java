package com.dvlupit.smartpill.activities;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TableRow;

import com.dvlupit.smartpill.R;

public class ExtrasActivity extends Activity {

    Intent intent = new Intent();

    private TableRow tbRowPerfil;
    private TableRow tbRowPlanTrip;
    private TableRow tbRowReminder;
    private TableRow tbRowHistory;
    private TableRow tbRowRecipe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_extras);



        //Iniciando componentes
        tbRowPerfil = (TableRow)findViewById(R.id.tblRowPerfil);
        tbRowPlanTrip = (TableRow)findViewById(R.id.tblRowPlanTrip);
        tbRowReminder = (TableRow)findViewById(R.id.tblRowReminder);
        tbRowHistory = (TableRow)findViewById(R.id.tblRowHistory);
        tbRowRecipe = (TableRow)findViewById(R.id.tblRowRecipe);


        //Iniciando Listeners

        tbRowPerfil.setOnClickListener(tbRowPerfilListener);
        tbRowPlanTrip.setOnClickListener(tbRowPlanTripListener);
        tbRowReminder.setOnClickListener(tbRowReminderListener);
        tbRowHistory.setOnClickListener(tbRowHistoryListener);
        tbRowRecipe.setOnClickListener(tbRowRecipeListener);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_extras, menu);
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
            intent = new Intent(ExtrasActivity.this, MainActivity.class);
            startActivityForResult(intent, 0);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    OnClickListener tbRowPerfilListener = new OnClickListener() {
        @Override
        public void onClick(View v) {
            SharedPreferences settings = getSharedPreferences("usuario", ExtrasActivity.MODE_PRIVATE);
            Boolean logged = settings.getBoolean("logado", false);
            if (logged) {
                intent = new Intent(ExtrasActivity.this, LoggedActivity.class);
                startActivityForResult(intent, 0);
            }else {
                intent = new Intent(ExtrasActivity.this, UserActivity.class);
                startActivityForResult(intent, 0);
            }

        }


    };

    OnClickListener tbRowPlanTripListener = new OnClickListener() {
        @Override
        public void onClick(View v) {
            intent = new Intent(ExtrasActivity.this, MainActivity.class);
            startActivityForResult(intent, 0);

        }


    };

    OnClickListener tbRowReminderListener = new OnClickListener() {
        @Override
        public void onClick(View v) {
            intent = new Intent(ExtrasActivity.this, ReminderActivity.class);
            startActivityForResult(intent, 0);

        }


    };

    OnClickListener tbRowHistoryListener = new OnClickListener() {
        @Override
        public void onClick(View v) {
            intent = new Intent(ExtrasActivity.this, HistoryActivity.class);
            startActivityForResult(intent, 0);

        }


    };

    OnClickListener tbRowRecipeListener = new OnClickListener() {
        @Override
        public void onClick(View v) {
            intent = new Intent(ExtrasActivity.this, RecipeActivity.class);
            startActivityForResult(intent, 0);

        }


    };
}
