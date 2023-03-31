package com.example.MoviesCRUD;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UpdateMoviesActivity extends AppCompatActivity {

    EditText title_input, genre_input, year_input;
    Button update_button, delete_button;

    String id, title, genre, year;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.update_movies_form);

        title_input = findViewById(R.id.title_input2);
        genre_input = findViewById(R.id.genre_input2);
        year_input = findViewById(R.id.year_input2);
        update_button = findViewById(R.id.update_button);
        delete_button = findViewById(R.id.delete_button);


        getAndSetIntentData();


        ActionBar ab = getSupportActionBar();
        if (ab != null) {
            ab.setTitle(title);
        }

        update_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                MoviesDatabase MoviesDB = new MoviesDatabase(UpdateMoviesActivity.this);
                title = title_input.getText().toString().trim();
                genre = genre_input.getText().toString().trim();
                year = year_input.getText().toString().trim();
                MoviesDB.updateData(id, title, genre, year);
            }
        });
        delete_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                confirmDialog();
            }
        });

    }

    void getAndSetIntentData(){
        if(getIntent().hasExtra("id") && getIntent().hasExtra("title") &&
                getIntent().hasExtra("genre") && getIntent().hasExtra("year")){

            id = getIntent().getStringExtra("id");
            title = getIntent().getStringExtra("title");
            genre = getIntent().getStringExtra("genre");
            year = getIntent().getStringExtra("year");


            title_input.setText(title);
            genre_input.setText(genre);
            year_input.setText(year);
            Log.d("", title+" "+genre+" "+year);
        }else{
            Toast.makeText(this, "Няма добавени филми.", Toast.LENGTH_SHORT).show();
        }
    }

    void confirmDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Изтриване на " + title + " ?");
        builder.setMessage("Сигурни ли сте че искате да изтриете " + title + " ?");
        builder.setPositiveButton("Да", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                MoviesDatabase MoviesDB = new MoviesDatabase(UpdateMoviesActivity.this);
                MoviesDB.deleteOneRow(id);
                finish();
            }
        });
        builder.setNegativeButton("Не", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        builder.create().show();
    }
}
