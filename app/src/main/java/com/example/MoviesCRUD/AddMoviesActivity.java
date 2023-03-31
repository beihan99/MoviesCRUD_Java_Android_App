package com.example.MoviesCRUD;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddMoviesActivity extends AppCompatActivity {

    EditText title_input, genre_input, year_input;
    Button add_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_movies_form);

        title_input = findViewById(R.id.title_input);
        genre_input = findViewById(R.id.genre_input);
        year_input = findViewById(R.id.year_input);
        add_button = findViewById(R.id.add_button);
        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MoviesDatabase MoviesDB = new MoviesDatabase(AddMoviesActivity.this);
                MoviesDB.addBook(title_input.getText().toString().trim(),
                        genre_input.getText().toString().trim(),
                        Integer.valueOf(year_input.getText().toString().trim()));
            }
        });
    }
}
