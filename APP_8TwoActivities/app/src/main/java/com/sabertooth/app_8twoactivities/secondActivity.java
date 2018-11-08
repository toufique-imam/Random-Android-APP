package com.sabertooth.app_8twoactivities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class secondActivity extends AppCompatActivity {
    final static String ER = "com.sabertooth.app_8twoactivities.extra.REPLY";
    private static final String LOG_TAG = secondActivity.class.getSimpleName();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Intent itt = getIntent();
        String message = itt.getStringExtra(MainActivity.EM);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        final Button bt2 = findViewById(R.id.button_second), bt1 = findViewById(R.id.button_main);
        final TextView tv2 = findViewById(R.id.text_view_second), tv1 = findViewById(R.id.text_view_main);
        final EditText ed2 = findViewById(R.id.edit_text_second), ed1 = findViewById(R.id.edit_text_main);
/*
        if(savedInstanceState!=null){
            boolean isVisible=savedInstanceState.getBoolean("reply_visible_");
            if(isVisible){
                tv2.setText(savedInstanceState.getString("reply_visible_"));
                tv2.setVisibility(View.VISIBLE);
            }
        }
        */
        if (message.length() > 0) {
            tv2.setText(message);
            tv2.setVisibility(View.VISIBLE);
        } else {
            tv2.setVisibility(View.INVISIBLE);
        }
        final Intent it = new Intent(this, MainActivity.class);
        bt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String reply = ed2.getText().toString();
                it.putExtra(ER, reply);
                setResult(RESULT_OK, it);
                finish();
            }
        });
    }
/*
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        TextView tt=findViewById(R.id.text_view_second);
        if(tt.getVisibility()==View.VISIBLE){
            outState.putBoolean("reply_visible_",true);
            outState.putString("reply_visible_",tt.getText().toString());
        }
    }
    */
    public void returnReply(View view) {}
}
