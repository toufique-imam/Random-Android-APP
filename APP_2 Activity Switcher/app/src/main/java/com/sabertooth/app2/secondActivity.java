package com.sabertooth.app2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class secondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        if(getIntent().hasExtra("com.sabertooth.app2.SOMETHING")){
            TextView text=(TextView)findViewById(R.id.text_1);
            String s=getIntent().getExtras().getString("com.sabertooth.app2.SOMETHING");
            text.setText(s);
        }
    }
}
