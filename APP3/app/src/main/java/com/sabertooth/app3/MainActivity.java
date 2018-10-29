package com.sabertooth.app3;

import android.content.Intent;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {
    ListView myList;
    String[] items;
    String[] Prices;
    String[] description;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Resources res=getResources();
        myList=findViewById(R.id.Listview_1);
        items=res.getStringArray(R.array.items);
        Prices=res.getStringArray(R.array.Prices);
        description=res.getStringArray(R.array.descriptions);
        itemAdapter itt=new itemAdapter(this,items,Prices,description);
        myList.setAdapter(itt);
        myList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent showPic=new Intent(getApplicationContext(),pictureDetailsActivity.class);
                showPic.putExtra("com.sabertooth.app3.INDEX",position);
                startActivity(showPic);
            }
        });
    }
}
