package com.sabertooth.counterwithreset;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final Button toast_btn,zero_btn,counter_btn;
        toast_btn=findViewById(R.id.toast_button);
        zero_btn=findViewById(R.id.zero_button);
        counter_btn=findViewById(R.id.counter_button);
        final TextView tv=findViewById(R.id.Count_show);
        final int[] count_ = {0};
        toast_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast tt=Toast.makeText(getApplicationContext(),"Toast Button Clicked",Toast.LENGTH_LONG);
                tt.show();
            }
        });
        zero_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(count_[0] !=0){
                    count_[0] =0;
                    zero_btn.setBackgroundColor(Color.GRAY);
                    zero_btn.setTextColor(Color.BLACK);
                }
                tv.setText(String.format("0"));
            }
        });
        counter_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(count_[0]==0){
                    zero_btn.setBackgroundColor(Color.BLUE);
                    zero_btn.setTextColor(Color.YELLOW);
                }
                count_[0]++;
                tv.setText(String.format("%d", count_[0]));
            }
        });
    }
}
