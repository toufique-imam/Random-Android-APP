package com.example.nuhash.maxalgovisualizer;

import android.content.Intent;
import android.support.v4.util.Pair;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import org.w3c.dom.Node;

import java.util.ArrayList;

public class Main2Activity extends AppCompatActivity {
    static ArrayList<Node_cls>graph_data;
    static MaxFlow mf;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        final Intent in=getIntent();
        graph_data=new ArrayList<>();
        boolean f=in.getBooleanExtra("YOYO",true);
        final Integer n= in.getExtras().getInt("n");
        final Integer m=in.getExtras().getInt("m");
        final Integer tap=in.getExtras().getInt("tap");
        final Integer sink=in.getExtras().getInt("sink");
        RecyclerView rv=findViewById(R.id.recycler_view);
        ListView lv;
        Button bt=findViewById(R.id.get_button);
        LinearLayoutManager layoutManager=new LinearLayoutManager(Main2Activity.this,LinearLayoutManager.VERTICAL,false);
        Adapter adp=new Adapter(Main2Activity.this,m,f);
        rv.setAdapter(adp);
        rv.setLayoutManager(layoutManager);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (graph_data.size() == m) {
                    LOG_PRINT();
                    mf = new MaxFlow(graph_data, n, m, sink, tap);
                    Intent intt=new Intent(Main2Activity.this,Main3Activity.class);
                    startActivity(intt);
                }
                else{
                    Toast.makeText(Main2Activity.this, "Not enough Data", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void LOG_PRINT() {
        for(Node_cls nc:graph_data){
            Log.e("FUCK",nc.from+"->"+nc.to+": "+nc.cap);
        }
    }
}
