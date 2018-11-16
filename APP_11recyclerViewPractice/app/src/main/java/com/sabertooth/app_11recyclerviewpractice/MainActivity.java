package com.sabertooth.app_11recyclerviewpractice;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    final int REQUEST_CODE = 1;
    static ArrayList<packData> main_data = new ArrayList<>();
    Button add_player_button, image_taker_button, list_button_show;
    ImageView imgview;
    EditText name_, des_, perks_;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ImageView bg=findViewById(R.id.Image_main);
        scale_image(bg,R.drawable.back);
        imgview = findViewById(R.id.ImageView_1);
        name_ = findViewById(R.id.Name);
        des_ = findViewById(R.id.Description);
        perks_ = findViewById(R.id.Specality);
        add_player_button = findViewById(R.id.add_button);
        image_taker_button = findViewById(R.id.image_take_button);
        list_button_show = findViewById(R.id.go_to_list_button);
        add_player_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s_name_ = name_.getText().toString(), s_des_ = des_.getText().toString(), s_perks_ = perks_.getText().toString();
                Bitmap s_pro_pic = null;
                Log.d(MainActivity.class.getSimpleName(), "Came Here 1");
                if (imgview.getVisibility() == View.VISIBLE) {
                    s_pro_pic = ((BitmapDrawable) imgview.getDrawable()).getBitmap();
                }
                Log.d(MainActivity.class.getSimpleName(), "Came Here 2");
                if (s_pro_pic != null) {
                    packData pc;
                    Log.d(MainActivity.class.getSimpleName(), "Came Here 3");
                    if (s_name_.length() < 1) {
                        Toast.makeText(getApplicationContext(), "Invalid Input", Toast.LENGTH_SHORT).show();
                    } else {
                        Log.d(MainActivity.class.getSimpleName(), "Came Here 4");
                        if (s_des_.length() < 1) s_des_ = "No Description Added Yet";
                        if (s_perks_.length() < 1) s_perks_ = "No Unique Speciality";
                        pc = new packData(s_name_, s_des_, s_perks_, s_pro_pic);
                        main_data.add(pc);
                    }
                } else {
                    Log.d(MainActivity.class.getSimpleName(), "Came Here 5");
                    Toast.makeText(getApplicationContext(), "Invalid Input", Toast.LENGTH_SHORT).show();
                }
                imgview.setImageBitmap(null);
                name_.setText(null);
                des_.setText(null);
                perks_.setText(null);
                imgview.setVisibility(View.INVISIBLE);
            }
        });
        image_taker_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intt = new Intent();
                intt.setType("image/*");
                intt.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intt, "Select Picture"), REQUEST_CODE);
            }
        });
        list_button_show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent Intent1 = new Intent(getApplicationContext(), List_show_sub.class);
                startActivity(Intent1);
            }
        });
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

    protected void onActivityResult(int req_code, int res_code, Intent data) {
        super.onActivityResult(req_code, res_code, data);
        if (req_code == REQUEST_CODE && res_code == RESULT_OK && null != data) {
            Uri imageUri = data.getData();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), imageUri);
                imgview.setImageBitmap(bitmap);
                imgview.setVisibility(View.VISIBLE);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
