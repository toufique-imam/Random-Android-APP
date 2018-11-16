package com.sabertooth.app_11recyclerviewpractice;

import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class PlayerDetails extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player_details);
        Intent came=getIntent();
        int idx=came.getIntExtra("Index",-1);
        if(idx!=-1){
            packData pc=MyAdapater1.tmp_data.get(idx);
            if(pc!=null) {
                TextView name=findViewById(R.id.details_name),details=findViewById(R.id.details_about),perk=findViewById(R.id.details_specality);
                ImageView img=findViewById(R.id.details_player_image);
                name.setText(pc.name_);
                details.setText(pc.about_);
                perk.setText(pc.perks);
                img.setImageBitmap(pc.profilePic);
            }
        }
    }
}
