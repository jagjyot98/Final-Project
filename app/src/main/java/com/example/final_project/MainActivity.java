package com.example.final_project;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.content.Intent;

public class MainActivity extends AppCompatActivity {

    Button btnAddRecipe, btnViewRecipe;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //attaching button variables with the activity button widgets
        btnAddRecipe = findViewById(R.id.btnAddRecipes);
        btnViewRecipe = findViewById(R.id.btnViewRecipies);

        // onclick listener for Add Recipe button to direct user to add recipe page
        btnAddRecipe.setOnClickListener((v)->{
            Intent myIntent = new Intent(MainActivity.this, AddRecipes.class);
            startActivity(myIntent);
        } );

        // onclick listener for View Recipe button to direct user to view recipes page
        btnViewRecipe.setOnClickListener((v)->{
            Intent myIntent = new Intent(MainActivity.this, ViewRecipes.class);
            startActivity(myIntent);
        } );
    }
}