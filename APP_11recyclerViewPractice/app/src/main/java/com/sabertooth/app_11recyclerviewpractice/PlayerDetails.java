package com.sabertooth.app_11recyclerviewpractice;

import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.jgabrielfreitas.core.BlurImageView;

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
                TextView bestB=findViewById(R.id.details_bowling),bestBat=findViewById(R.id.details_batting),
                        RPL_=findViewById(R.id.details_rpl_trophies),RASHES_=findViewById(R.id.details_rashes_trophies);
                TextView name=findViewById(R.id.details_name),details=findViewById(R.id.details_about),
                        perk=findViewById(R.id.details_specality);
                BlurImageView img1=findViewById(R.id.details_image_1);
                ImageView img2=findViewById(R.id.details_image_2);
                name.setText(pc.name_);
                details.setText(pc.about_);
                perk.setText(pc.perks);
                img1.setImageBitmap(pc.profilePic);
                img1.setBlur(8);
                img2.setImageBitmap(pc.profilePic);
                bestB.setText("Total Wicket Taken :"+pc.best_bowling+"");
                RPL_.setText("Total RPL Trophies Won:"+pc.RPL+"");
                RASHES_.setText("Total Rashes Won: "+pc.Rashes+"");
                bestBat.setText("Highest Innings :"+pc.best_batting+"");
            }
        }
    }
}
