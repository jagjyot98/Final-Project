package com.example.final_project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class AddRecipes extends AppCompatActivity {

    EditText recipeName, recipeIngredients, recipeSteps, recipeTime;
    Button btnSubmit;
    SQLiteDatabase db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_recipies);

        //creating back button on screen
        ActionBar actionBar = getSupportActionBar();

        // showing the back button in action bar
        actionBar.setDisplayHomeAsUpEnabled(true);

        Recipes Rec = new Recipes();                //instantiating class object

        recipeName = findViewById(R.id.RecipeName);     //attaching edit text variables with the activity view widgets
        recipeIngredients = findViewById(R.id.IngredientsNquantity);
        recipeSteps = findViewById(R.id.RecipeSteps);
        recipeTime = findViewById(R.id.TimeInput);

        btnSubmit = findViewById(R.id.btnSubmit);

        recipeName.requestFocus();

        // creating database
        db = openOrCreateDatabase("Program", MODE_PRIVATE, null);
        //creating table "Recipes" inside the "Program" table
        db.execSQL("CREATE TABLE IF NOT EXISTS Recipes (id INTEGER PRIMARY KEY AUTOINCREMENT, recipe_Name TEXT, recipe_Ingredients TEXT, recipe_Steps TEXT, recipe_Time TEXT);");

        btnSubmit.setOnClickListener(new View.OnClickListener() {       // on click listener for "Save button to add data to database table"
            @Override
            public void onClick(View v) {
                                                    // transfering data from edit text variables to the class object attributes
                Rec.setRecName(recipeName.getText().toString());
                Rec.setRecIngreQuant(recipeIngredients.getText().toString());
                Rec.setRecSteps(recipeSteps.getText().toString());
                Rec.setRecTime(recipeTime.getText().toString());

                ContentValues contentValues = new ContentValues();

                //attaching class object values to the insert query
                contentValues.put("recipe_Name", Rec.getRecName());
                contentValues.put("recipe_Ingredients", Rec.getRecIngreQuant());
                contentValues.put("recipe_Steps", Rec.getRecSteps());
                contentValues.put("recipe_Time", Rec.getRecTime());

                long newRowId = db.insert("Recipes", null, contentValues);      //executing insert statement

                //checking whether data successfully inserted or not
                if (newRowId != -1) {
                    Toast.makeText(AddRecipes.this, "Recipe record successfully added", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(AddRecipes.this, "Error adding recipe record", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}



