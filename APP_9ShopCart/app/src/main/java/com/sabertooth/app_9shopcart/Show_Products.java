package com.sabertooth.app_9shopcart;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

public class Show_Products extends AppCompatActivity {
    ListView shop_list_show;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(Show_Products.class.getSimpleName(),"Came_here_show_1");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show__products);
        shop_list_show=findViewById(R.id.list_recycle);
        Log.d(Show_Products.class.getSimpleName(),"Came_here_show_2");
        list_adapter la_=new list_adapter(this,MainActivity.item_pack);
        Log.d(Show_Products.class.getSimpleName(),"Came_here_show_3");
        shop_list_show.setAdapter(la_);
        Log.d(Show_Products.class.getSimpleName(),"Came_here_show_4");
        shop_list_show.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String locc=MainActivity.item_pack.get(position).loc;
                Uri address=Uri.parse("geo:0,0?q="+locc);
                Intent itt=new Intent(Intent.ACTION_VIEW,address);
                if(itt.resolveActivity(getPackageManager())!=null){
                    startActivity(itt);
                }
                else
                    Log.d(Show_Products.class.getSimpleName(),"no app to show this shit");
            }
        });
    }
}
