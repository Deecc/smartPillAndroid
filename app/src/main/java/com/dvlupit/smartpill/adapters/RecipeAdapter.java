package com.dvlupit.smartpill.adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.dvlupit.smartpill.R;

import com.dvlupit.smartpill.entitys.Recipe;

import java.io.File;
import java.util.List;

/**
 * Created by deck on 23/08/15.
 */
public class RecipeAdapter extends ArrayAdapter<Recipe> {

    private LayoutInflater myInflater;

    public RecipeAdapter(Context context, List<Recipe> listRecipe) {
        super(context, R.layout.recipe_list, listRecipe);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater layoutInflater = LayoutInflater.from(getContext());
        View customView = layoutInflater.inflate(R.layout.recipe_list, parent, false);

        Recipe recipeItem = getItem(position);

        ImageView picture = (ImageView) customView.findViewById(R.id.recipe_pic);
        TextView recipeName = (TextView) customView.findViewById(R.id.labelRecipeName);
        TextView recipeDate = (TextView) customView.findViewById(R.id.labelRecipeDate);

        File imgFile = new File(recipeItem.recipePath);
        if (imgFile.exists()) {
            Bitmap bm = BitmapFactory.decodeFile(imgFile.getAbsolutePath());
            picture.setImageBitmap(bm);

        }else picture.setImageResource(R.drawable.clock);
        recipeName.setText(recipeItem.name);
        //recipeDate.setText(String.valueOf(recipeItem.date));

        return customView;
    }

}
