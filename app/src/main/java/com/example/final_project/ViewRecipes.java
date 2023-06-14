package com.example.final_project;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

public class ViewRecipes extends AppCompatActivity {

    TextView recipeName;
    SQLiteDatabase db;
    Recipes Rec = new Recipes();        //instantiating class Recipe object


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_recipe);

        // calling the action bar
        ActionBar actionBar = getSupportActionBar();

        // showing the back button in action bar
        actionBar.setDisplayHomeAsUpEnabled(true);


        recipeName = findViewById(R.id.recipePrint);        //attaching text view to the view widget on screen

        //connecting db object to the created Program database
        db = openOrCreateDatabase("Program", MODE_PRIVATE, null);

        // executing select statement to read all data from Recipe table of Program database
        Cursor cursor = db.rawQuery("SELECT * FROM Recipes", null);

        //checking whether any data present or not
        if (cursor.getCount() == 0) {           //if not...
            showStatus(ViewRecipes.this, "Error", "No Data");
        } else {
            StringBuffer buffer = showRecords(cursor);          //if present...
            showStatus(ViewRecipes.this, "Recorded Recipes..", buffer.toString());
            cursor.getCount();
        }
        cursor.close();

    }

    public StringBuffer showRecords(Cursor cursor) {        //function to create a string from the fetched record
        StringBuffer buffer = new StringBuffer();
        while (cursor.moveToNext()) {

            buffer.append("\n" + "Recipe" + + cursor.getInt(0) + ": \n");

            Rec.setRecName(cursor.getString(1));            //passing fetched data to class object attributes
            Rec.setRecIngreQuant(cursor.getString(2));
            Rec.setRecSteps(cursor.getString(3));
            Rec.setRecTime(cursor.getString(4));


            buffer.append("Recipe Name: \n" + Rec.getRecName() + "\n");             //getting fetched data from class object to create display string
            buffer.append("Recipe Ingredients & Quantity: \n" + Rec.getRecIngreQuant() + "\n");
            buffer.append("Recipe Steps: \n" + Rec.getRecSteps() + "\n");
            buffer.append("Recipe Time Required (Min): \n" + Rec.getRecTime() + "\n\n");
        }
        return buffer;
        }


    public void showStatus(Context context, String title, String resultContent) {       //function to display fetched data string in the text view on the screen
        recipeName.setText(resultContent);
    }

    // this event will enable the back
    // function to the button on press
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
