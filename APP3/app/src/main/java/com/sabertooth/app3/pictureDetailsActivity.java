package com.sabertooth.app3;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Display;
import android.widget.ImageView;

public class pictureDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_picture_details);
        Intent in=getIntent();
        int idx=in.getIntExtra("com.sabertooth.app3.INDEX",-1);
        if(idx>-1){
            int pic=getImg(idx);
            ImageView img=findViewById(R.id.imageView);
          //  img.setImageBitmap(img);
            scaleImg(img,pic);
        }
    }

    private int getImg(int index){
        switch (index){
            case 0:return  R.drawable.pic1;
            case 1:return  R.drawable.pic2;
            case 2:return  R.drawable.pic3;
            case 3:return  R.drawable.pic4;
            default: return  -1;
        }
    }
    private void scaleImg(ImageView img,int pic)
    {
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
    }
}
