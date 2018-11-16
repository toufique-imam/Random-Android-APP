package com.sabertooth.bittobyte_workshop;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

public class MainActivity extends AppCompatActivity {
    RecyclerView.Adapter adp;
    RecyclerView rc;
    GridLayoutManager lmang;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        adp=new MyAdapter(this);
        rc=findViewById(R.id.recycle_view_1);
        lmang=new GridLayoutManager(this,2);
        rc.setLayoutManager(lmang);
        rc.setAdapter(adp);
    }
}