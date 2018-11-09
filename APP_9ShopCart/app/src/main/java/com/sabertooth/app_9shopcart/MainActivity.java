package com.sabertooth.app_9shopcart;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import java.util.ArrayList;
class pack_data{
    String name,description;
    Integer price;
    pack_data(String a,Integer p,String d){
        name=a;
        description=d;
        price=p;
    }
}
public class MainActivity extends AppCompatActivity {
    final static ArrayList< pack_data >item_pack=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button add_bt=findViewById(R.id.Add_button),show_bt=findViewById(R.id.Show_button);
        final EditText ed1=findViewById(R.id.item_name_input), ed2=findViewById(R.id.item_price_input), ed3=findViewById(R.id.item_description_input);
        add_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s1=ed1.getText().toString();
                String s2=ed2.getText().toString();
                String s3=ed3.getText().toString();
                if(s1.length()<1 || s2.length()<1){
                    Toast tt=Toast.makeText(getApplicationContext(),"Invalid Input",Toast.LENGTH_LONG);
                    tt.show();
                }
                else {
                    Integer i=Integer.valueOf(s2);
                    if(s3.length()<1)s3="No Description";
                    pack_data p=new pack_data(s1,i,s3);
                    item_pack.add(p);
                    Toast tt=Toast.makeText(getApplicationContext(),"Done",Toast.LENGTH_LONG);
                    tt.show();
                    ed1.setText(null);
                    ed2.setText(null);
                    ed3.setText(null);
                }
            }
        });
        show_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent shop_show_intent=new Intent(getApplicationContext(),Show_Products.class);
                startActivity(shop_show_intent);
            }
        });
    }
}
