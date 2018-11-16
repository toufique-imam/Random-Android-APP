package com.sabertooth.app_11recyclerviewpractice;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Display;
import android.widget.ImageView;

public class List_show_sub extends AppCompatActivity {
    RecyclerView.Adapter adp;
    RecyclerView rc;
    GridLayoutManager lmnag;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_show_sub);
        ImageView bg=findViewById(R.id.image_secondary);
        scale_image(bg,R.drawable.back_second);
        adp=new MyAdapater1(this,MainActivity.main_data);
        rc=findViewById(R.id.recycle_view_1);
        lmnag=new GridLayoutManager(this,2);
        rc.setLayoutManager(lmnag);
        rc.setAdapter(adp);
    }

    private void scale_image(ImageView img,int pic) {
        Display screen=getWindowManager().getDefaultDisplay();//will give us access to screen
        BitmapFactory.Options opt=new BitmapFactory.Options();//java lib that will scale images for us
        opt.inJustDecodeBounds=true;
        BitmapFactory.decodeResource(getResources(),pic,opt);
        int imgWid=opt.outWidth;
        int scr=screen.getWidth();

        if(imgWid>scr){
            opt.inSampleSize= Math.round((float)(imgWid)/ (float)scr);
        }

        opt.inJustDecodeBounds=false;
        Bitmap scaleImg=BitmapFactory.decodeResource(getResources(),pic,opt);
        img.setImageBitmap(scaleImg);
        img.setScaleType(ImageView.ScaleType.FIT_XY);
    }
}
