package com.sabertooth.app_12firebase;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BaseTransientBottomBar;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

class Artist {
    private String Artist_name, Artist_genre, Artist_id;

    public Artist() {
    }

    Artist(String artist_name, String artist_genre, String artist_id) {
        Artist_name = artist_name;
        Artist_genre = artist_genre;
        Artist_id = artist_id;
    }

    public String getArtist_name() {
        return Artist_name;
    }

    public void setArtist_name(String artist_name) {
        Artist_name = artist_name;
    }

    public String getArtist_genre() {
        return Artist_genre;
    }

    public void setArtist_genre(String artist_genre) {
        Artist_genre = artist_genre;
    }

    public String getArtist_id() {
        return Artist_id;
    }

    public void setArtist_id(String artist_id) {
        Artist_id = artist_id;
    }
}

public class MainActivity extends AppCompatActivity {
    public static final String ARTIST_NAME = "artistname";
    public static final String ARTIST_ID = "artistid";
    public static final String Feedback_Mail = "2013nuhash@gmail.com";
    EditText name_;
    Button add_button;
    Spinner spinnerGenres;
    DatabaseReference databaseArtist;
    ListView listView;
    List<Artist> artistList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        artistList = new ArrayList<>();
        setContentView(R.layout.activity_main);
        databaseArtist = FirebaseDatabase.getInstance().getReference("artist");
        name_ = findViewById(R.id.Input_Name);
        add_button = findViewById(R.id.Input_button);
        spinnerGenres = findViewById(R.id.Input_spinner);
        listView = findViewById(R.id.list_view_artist);
        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addArtist();
                try {
                    Snackbar.make(v, "Send Feedback", BaseTransientBottomBar.LENGTH_LONG).setAction("Go", new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts("mailto", Feedback_Mail, null));
                            intent.putExtra(Intent.EXTRA_EMAIL, Feedback_Mail);
                            intent.putExtra(Intent.EXTRA_SUBJECT, "Subject");
                            intent.putExtra(Intent.EXTRA_TEXT, "Body");
                            startActivity(Intent.createChooser(intent, "Send Email..."));
                            //Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts("mailto",Feedback_Mail, null));
                            //emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Subject");
                            //emailIntent.putExtra(Intent.EXTRA_TEXT, "Body");
                            //startActivity(Intent.createChooser(emailIntent, "Send email..."));
                        }
                    }).show();
                } catch (Exception e) {
                    e.printStackTrace();
                    Log.d("FUCK", e.getMessage());
                }
            }
        });
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Artist artist = artistList.get(position);
                Intent intent = new Intent(getApplicationContext(), addTrack_activity.class);
                intent.putExtra(ARTIST_ID, artist.getArtist_id());
                intent.putExtra(ARTIST_NAME, artist.getArtist_name());
                startActivity(intent);
            }
        });
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Artist artist=artistList.get(position);
                showUpdateDialog(artist.getArtist_id(),artist.getArtist_name());
                return true;
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        databaseArtist.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                artistList.clear();
                for (DataSnapshot artistSnapshot : dataSnapshot.getChildren()) {
                    Artist artist = artistSnapshot.getValue(Artist.class);
                    artistList.add(artist);
                }
                Artist_list adapter = new Artist_list(getApplicationContext(), artistList);
                listView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    private void showUpdateDialog(final String artistId, String artistName) {
        AlertDialog.Builder dialogueBuilder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        final View dialogVIew = inflater.inflate(R.layout.update_dialogue, null);
        dialogueBuilder.setView(dialogVIew);

        final Spinner spinnerGenres=dialogVIew.findViewById(R.id.edit_spinner_genre);
        final EditText editTextName = dialogVIew.findViewById(R.id.edit_name_input);
        final TextView textViewName = dialogVIew.findViewById(R.id.edit_name_show_input);
        final Button buttonUpdate = dialogVIew.findViewById(R.id.edit_name_edit_button);
        final Button buttonDelete=dialogVIew.findViewById(R.id.edit_name_delete_button);
        dialogueBuilder.setTitle("Updating Artist " + artistName);
        final AlertDialog alertDialog=dialogueBuilder.create();
        alertDialog.show();
        buttonUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name=editTextName.getText().toString();
                String genre=spinnerGenres.getSelectedItem().toString();
                if(name.length()<1){
                    textViewName.setText("invalid input");
                    Toast.makeText(getApplicationContext(),"InValid Input",Toast.LENGTH_SHORT).show();
                    return;
                }
                updateArtist(artistId,name,genre);
                alertDialog.dismiss();
            }
        });
        buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteArtist(artistId);
                alertDialog.dismiss();
            }
        });
    }

    private void deleteArtist(String artistId) {
        DatabaseReference databaseReference=FirebaseDatabase.getInstance().getReference("artist").child(artistId);
        DatabaseReference databaseReference1=FirebaseDatabase.getInstance().getReference("tracks").child(artistId);
        databaseReference.removeValue();
        databaseReference1.removeValue();
        Toast.makeText(this,"Deleted",Toast.LENGTH_SHORT).show();
    }

    private  boolean updateArtist(String id,String name,String genre){
        DatabaseReference databaseReference=FirebaseDatabase.getInstance().getReference("artist").child(id);
        Artist artist=new Artist(name,genre,id);
        databaseReference.setValue(artist);
        Toast.makeText(this,"Artist Updated",Toast.LENGTH_LONG).show();
        return  true;
    }

    private void addArtist() {
        String name = name_.getText().toString();
        String genre = spinnerGenres.getSelectedItem().toString();
        if (name.length() > 0) {
            String id = databaseArtist.push().getKey();
            Artist artist = new Artist(name, genre, id);
            if (id != null) {
                try {
                    databaseArtist.child(id).setValue(artist);
                } catch (Exception e) {
                    Log.d("Fuck me", e.getMessage());
                    e.printStackTrace();
                }
                Toast.makeText(this, "artist added", Toast.LENGTH_LONG).show();
            }
        } else {
            Toast.makeText(this, "Invalid Input", Toast.LENGTH_LONG).show();
        }
    }
}
