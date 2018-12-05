package com.example.nuhash.maxalgovisualizer;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    TextView tv;
    EditText ed1,ed3,ed4;
    Button bt1,bt2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        final EditText wtf=findViewById(R.id.edit_text_edge);
        tv.setText("Have a nice CT");
        bt1.setOnClickListener(new View.OnClickListener() {
            Integer n,m,sink,tap;
            @Override
            public void onClick(View v) {
                String s;
                try{
                    s=ed1.getText().toString();
                    n=Integer.valueOf(s);
                    s=wtf.getText().toString();
                    m=Integer.valueOf(s);
                    s=ed3.getText().toString();
                    tap=Integer.valueOf(s);
                    s=ed4.getText().toString();
                    sink=Integer.valueOf(s);
                    if(n>100 || sink<0 || tap<0 || sink>n || tap>n || m<n-1){
                        Toast.makeText(MainActivity.this, "Why are You Fooling around?", Toast.LENGTH_SHORT).show();
                    }
                    else{
                        Intent intent=new Intent(MainActivity.this,Main2Activity.class);
                        intent.putExtra("n",n);
                        intent.putExtra("m",m);
                        intent.putExtra("sink",sink);
                        intent.putExtra("tap",tap);
                        intent.putExtra("YOYO",true);
                        startActivity(intent);
                    }
                }catch (Exception e){
                    Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
        bt2.setOnClickListener(new View.OnClickListener() {
            Integer n,m,sink,tap;
            @Override
            public void onClick(View v) {
                String s;
                try{
                    s=ed1.getText().toString();
                    n=Integer.valueOf(s);
                    s=wtf.getText().toString();
                    m=Integer.valueOf(s);
                    s=ed3.getText().toString();
                    tap=Integer.valueOf(s);
                    s=ed4.getText().toString();
                    sink=Integer.valueOf(s);
                    if(n>100 || sink<0 || tap<0 || sink>n || tap>n || m<n-1){
                        Toast.makeText(MainActivity.this, "Why are You Fooling around?", Toast.LENGTH_SHORT).show();
                    }
                    else{
                        Intent intent=new Intent(MainActivity.this,Main2Activity.class);
                        intent.putExtra("n",n);
                        intent.putExtra("m",m);
                        intent.putExtra("sink",sink);
                        intent.putExtra("tap",tap);
                        intent.putExtra("YOYO",false);
                        startActivity(intent);
                    }
                }catch (Exception e){
                    Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void init() {
        tv=findViewById(R.id.text_view);
        ed1=findViewById(R.id.in_nodes);
        //ed2.findViewById(R.id.edit_text_edge);
        ed3=findViewById(R.id.in_tap);
        ed4=findViewById(R.id.in_sink);
        bt1=findViewById(R.id.take_edge_max);
        bt2=findViewById(R.id.take_edge_bpm);
    }
}
