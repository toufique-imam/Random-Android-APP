package com.sabertooth.app_11recyclerviewpractice;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Display;
import android.widget.ImageView;

import com.jgabrielfreitas.core.BlurImageView;

public class List_show_sub extends AppCompatActivity {
    RecyclerView.Adapter adp;
    RecyclerView rc;
    GridLayoutManager lmnag;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_show_sub);
        BlurImageView bg=findViewById(R.id.image_secondary);
        resize(bg,R.drawable.back_second);
        adp=new MyAdapater1(this,MainActivity.main_data);
        rc=findViewById(R.id.recycle_view_1);
        lmnag=new GridLayoutManager(this,2);
        rc.setLayoutManager(lmnag);
        rc.setAdapter(adp);
    }

    private void resize(BlurImageView img, int pic) {
        Display screen=getWindowManager().getDefaultDisplay();
        int scrh=screen.getHeight();
        int scrw=screen.getWidth();
        Drawable image=getDrawable(pic);
        Bitmap b = ((BitmapDrawable)image).getBitmap();
        Bitmap bitmapResized = Bitmap.createScaledBitmap(b, scrw, scrh, false);
        img.setImageBitmap(bitmapResized);
        img.setScaleType(ImageView.ScaleType.FIT_XY);
        img.setBlur(2);
    }
}
