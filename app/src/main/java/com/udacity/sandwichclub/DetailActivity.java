package com.udacity.sandwichclub;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.udacity.sandwichclub.model.Sandwich;
import com.udacity.sandwichclub.repositories.SandwichRepositoryImpl;

public class DetailActivity extends AppCompatActivity {

    public static final String EXTRA_KEY = "extra_key";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        ImageView ingredientsIv = findViewById(R.id.image_iv);

        Intent intent = getIntent();
        if (intent == null) {
            closeOnError();
        }

        String key = intent.getStringExtra(EXTRA_KEY);
        if (key == null || key.isEmpty()) {
            // EXTRA_KEY not found in intent
            closeOnError();
            return;
        }

        Sandwich sandwich = SandwichRepositoryImpl.getInstance().getSandwichFromKeyName(key);
        if (sandwich == null) {
            // Sandwich data unavailable
            closeOnError();
            return;
        }

        populateUI(sandwich);
        Picasso.with(this)
                .load(sandwich.getImage())
                .into(ingredientsIv);

        setTitle(sandwich.getMainName());
    }

    private void closeOnError() {
        finish();
        Toast.makeText(this, R.string.detail_error_message, Toast.LENGTH_SHORT).show();
    }

    private void populateUI(Sandwich sandwich) {
        ((TextView) findViewById(R.id.origin_tv)).setText(sandwich.getPlaceOfOrigin());
        ((TextView) findViewById(R.id.description_tv)).setText(sandwich.getDescription());
        ((TextView) findViewById(R.id.ingredients_tv)).setText("");//sandwich.getIngredients());
        ((TextView) findViewById(R.id.also_known_tv)).setText("");//sandwich.getAlsoKnownAs());
    }
}
