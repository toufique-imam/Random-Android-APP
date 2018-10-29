package com.sabertooth.app2;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Attempts to launch an activity within our own app
        Button secondactivity_=findViewById(R.id.activity2);
        secondactivity_.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent startIntent=new Intent(getApplicationContext(),secondActivity.class);
                startIntent.putExtra("com.sabertooth.app2.SOMETHING","Nuhash");
                startActivity(startIntent);
            }
        });
        Button googleBtn=findViewById(R.id.googlebutton);
        googleBtn.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                String gg="http://google.com";
                Uri web=Uri.parse(gg);
                Intent gotogg=new Intent(Intent.ACTION_VIEW,web);
                if(gotogg.resolveActivity(getPackageManager())!=null){
                    startActivity(gotogg);
                }
            }
        });
    }
}
