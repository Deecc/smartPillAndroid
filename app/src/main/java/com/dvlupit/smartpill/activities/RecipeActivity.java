package com.dvlupit.smartpill.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.dvlupit.smartpill.R;
import com.dvlupit.smartpill.adapters.RecipeAdapter;
import com.dvlupit.smartpill.entitys.Recipe;

import java.util.List;

public class RecipeActivity extends Activity {

    Intent intent;
    private List<Recipe> recipes;
    private ListView listRecipes;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe);

        listRecipes = (ListView) findViewById(R.id.listViewRecipes);
        recipes = Recipe.listAll(Recipe.class);
        if (recipes.isEmpty()){
            intent = new Intent(RecipeActivity.this, AddRecipe.class);
            startActivityForResult(intent, 0);
        } else {
            ListAdapter recipeAdapter = new RecipeAdapter(this, recipes);
            listRecipes.setAdapter(recipeAdapter);

            listRecipes.setOnItemClickListener(
                    new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            String recipe = "Receita: " + String.valueOf(recipes.get(position).name) + " \nData: " + String.valueOf(recipes.get(position).date);
                            Toast.makeText(RecipeActivity.this, recipe, Toast.LENGTH_LONG).show();
                        }
                    }
            );
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_recipe, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_recipe) {
            intent = new Intent(RecipeActivity.this, AddRecipe.class);
            startActivityForResult(intent, 0);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
