package com.sabertooth.app_12firebase;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
public class addTrack_activity extends AppCompatActivity {
    List<Track>trackList;
    TextView textViewArtistName;
    EditText editTextTrackName;
    SeekBar seekBar;
    ListView listViewTracks;
    Button buttonAddTrack;
    DatabaseReference databaseTracks;

    @Override
    protected void onStart() {
        super.onStart();
        databaseTracks.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                trackList.clear();
                for(DataSnapshot trackSnapshot:dataSnapshot.getChildren()){
                    Track track=trackSnapshot.getValue(Track.class);
                    trackList.add(track);
                    //Log.d("Track_FUCK",track.getTrackRating()+"");
                    //Log.d("Track_FUCK",track.getTrackName());
                }
                Log.d("TRACK_FUCK","Came Here");
                Track_list_class tlc=new Track_list_class(getApplicationContext(),trackList);
                listViewTracks.setAdapter(tlc);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_track_activity);
        trackList=new ArrayList<>();
        textViewArtistName = findViewById(R.id.textViewArtistName);
        editTextTrackName = findViewById(R.id.editTextTrackName);
        seekBar = findViewById(R.id.seekBarRating);
        listViewTracks = findViewById(R.id.list_view_track);
        buttonAddTrack = findViewById(R.id.tackInputButton);
        Intent intent = getIntent();
        String id = intent.getStringExtra(MainActivity.ARTIST_ID);
        String name = intent.getStringExtra(MainActivity.ARTIST_NAME);
        textViewArtistName.setText(name);
        databaseTracks = FirebaseDatabase.getInstance().getReference("tracks").child(id);
        buttonAddTrack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveTrack();
                editTextTrackName.setText(null);
                seekBar.setProgress(0);
            }
        });
    }

    private void saveTrack() {
        String trackName = editTextTrackName.getText().toString();
        int rating = seekBar.getProgress();
        if (TextUtils.isEmpty(trackName)) {
            Toast.makeText(this, "Invalid Input", Toast.LENGTH_LONG).show();
        } else {
            String id = databaseTracks.push().getKey();
            Track track = new Track(id, trackName, rating);
            if (id != null) {
                try {
                    databaseTracks.child(id).setValue(track);
                    Toast.makeText(this, "Track Saved Successfully", Toast.LENGTH_LONG).show();
                } catch (Exception e) {
                    Log.d("fuck me", e.getMessage());
                    Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
                }
            }
        }
    }
}
