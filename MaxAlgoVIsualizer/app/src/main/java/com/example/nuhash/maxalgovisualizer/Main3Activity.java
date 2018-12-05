package com.example.nuhash.maxalgovisualizer;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import static com.example.nuhash.maxalgovisualizer.Main2Activity.mf;

public class Main3Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        MaxFlow maxFlow=mf;
        ArrayList<String>as=maxFlow.max_flow();
        int ans=maxFlow.get_max_flow();
        TextView tv=findViewById(R.id.text_ans);
        ListView lv=findViewById(R.id.list_view);
        tv.setText(ans+"");
        ListAdapter adapter = new ArrayAdapter<>(
                Main3Activity.this,
                R.layout.path_show,R.id.path_print,as);
        lv.setAdapter(adapter);
    }
}
