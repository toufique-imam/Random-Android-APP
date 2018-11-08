package com.sabertooth.app_7appbar;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toolbar;

public class MainActivity extends AppCompatActivity {
    private android.support.v7.widget.Toolbar tb2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tb2=findViewById(R.id.appbar_2);
        setSupportActionBar(tb2);
        /*Button bt1=findViewById(R.id.appbar_button),bt2=findViewById(R.id.appbar_2_Button);
        bt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView tv=findViewById(R.id.appbar_text);
                tv.setText("Clicked");
                setSupportActionBar(tb1);
            }
        });

        bt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setSupportActionBar(tb1);
            }
        });
        */
    }
}
