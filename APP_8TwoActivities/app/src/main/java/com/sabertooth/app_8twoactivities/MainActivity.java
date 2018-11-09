package com.sabertooth.app_8twoactivities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    static final String EM = "com.sabertooth.app_8twoactivities.extra.MESSAGE";
    final static int TR = 1;
    TextView tv;
    Button bt1;
    EditText ed1;
    private static final String LOG_TAG = MainActivity.class.getSimpleName();

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if (tv.getText().toString().length() > 0) {
            outState.putBoolean("rv", true);
            outState.putString("rv", tv.getText().toString());
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(LOG_TAG, "______");
        Log.d(LOG_TAG, "on Create");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv = findViewById(R.id.text_view_main);
        bt1 = findViewById(R.id.button_main);
        ed1 = findViewById(R.id.edit_text_main);
        final Intent intnt = new Intent(this, secondActivity.class);
        if (savedInstanceState != null) {
                boolean isVisible = savedInstanceState.getBoolean("rv");
                tv.setVisibility(View.VISIBLE);
                tv.setText(savedInstanceState.getString("rv"));

        }
        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ss;
                ss = ed1.getText().toString();
                ed1.setText(null);
                intnt.putExtra(EM, ss);
                startActivityForResult(intnt, TR);
            }
        });
    }

    public void onActivityResult(int reqCode, int resCode, Intent data) {
        super.onActivityResult(reqCode, resCode, data);
        if (reqCode == TR) {
            if (resCode == RESULT_OK) {
                String reply = data.getStringExtra(secondActivity.ER);
                TextView tv1 = findViewById(R.id.text_view_main);
                    tv1.setText(reply);
                    tv1.setVisibility(View.VISIBLE);
            }
        }
    }

    public void Second_activity(View view) {
        Log.d(LOG_TAG, "Button Clicked!");
    }
}
