package com.sabertooth.app_12firebase;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

class Artist {
    String Artist_name, Artist_genre, Artist_id;

    public Artist() {

    }

    public Artist(String artist_name, String artist_genre, String artist_id) {
        Artist_name = artist_name;
        Artist_genre = artist_genre;
        Artist_id = artist_id;
    }

    public void setArtist_name(String artist_name) {
        Artist_name = artist_name;
    }

    public void setArtist_genre(String artist_genre) {
        Artist_genre = artist_genre;
    }

    public void setArtist_id(String artist_id) {
        Artist_id = artist_id;
    }

    public String getArtist_genre() {
        return Artist_genre;
    }

    public String getArtist_id() {
        return Artist_id;
    }

    public String getArtist_name() {
        return Artist_name;
    }
}

public class MainActivity extends AppCompatActivity {
    EditText name_;
    Button add_button;
    Spinner spinnerGenres;
    DatabaseReference databaseArtist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        databaseArtist = FirebaseDatabase.getInstance().getReference("artist");
        name_ = findViewById(R.id.Input_Name);
        add_button = findViewById(R.id.Input_button);
        spinnerGenres = findViewById(R.id.Input_spinner);
        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addArtist();
            }
        });
    }

    private void addArtist() {
        String name = name_.getText().toString();
        String genre = spinnerGenres.getSelectedItem().toString();
        if (name.length() > 0) {
            String id = databaseArtist.push().getKey();
            Artist artist = new Artist(name, genre, id);
            if (id != null) {
                databaseArtist.child(id).setValue(artist);
                Toast.makeText(this,"artist added",Toast.LENGTH_LONG).show();
            }
        } else {
            Toast.makeText(this,"Invalid Input",Toast.LENGTH_LONG).show();
        }
    }
}
