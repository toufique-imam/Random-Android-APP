package com.sabertooth.test3;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import static android.widget.Toast.LENGTH_LONG;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button toastt=findViewById(R.id.toastButton);
        Button cnt=findViewById(R.id.count_button);
        final TextView tt=findViewById(R.id.counter);
        final int[] cntt = {0};
        toastt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast tst=Toast.makeText(getApplicationContext(),"hello",LENGTH_LONG);
                tst.setGravity(Gravity.TOP|Gravity.LEFT, 400, 1000000);
                tst.show();
            }
        });
        cnt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cntt[0]++;
                tt.setText(toString(cntt[0]));
            }
            private String toString(int i) {
                return "" + i;
            }
        });
    }
}
