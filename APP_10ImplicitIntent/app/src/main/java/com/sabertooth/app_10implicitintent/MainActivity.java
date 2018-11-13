package com.sabertooth.app_10implicitintent;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ShareCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button website = findViewById(R.id.url_opener), location = findViewById(R.id.location_opner), text_share = findViewById(R.id.share_opener);
        final EditText web_text = findViewById(R.id.url_editor), loc_text = findViewById(R.id.location_editor), text_edit = findViewById(R.id.share_edit);
        TextView tvi=findViewById(R.id.text_view_for_outside_intents);
        Intent it=getIntent();
        Uri ss=it.getData();
        if(ss!=null){
            String s=ss.toString();
            tvi.setText(s);
        }
        website.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = web_text.getText().toString();
                if (!url.startsWith("www.") && !url.startsWith("http://") && url.startsWith("https://")) {
                    url = "www." + url;
                }
                if (!url.startsWith("http://") && !url.startsWith("https://")) {
                    url = "http://" + url;
                }
                Uri webpage = Uri.parse(url);
                Intent intn = new Intent(Intent.ACTION_VIEW, webpage);
                if (intn.resolveActivity(getPackageManager()) != null) {
                    startActivity(intn);
                }
                web_text.setText(null);
            }
        });
        location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String loc=loc_text.getText().toString();
                Uri address=Uri.parse("geo:0,0?q="+loc);
                Intent intn=new Intent(Intent.ACTION_VIEW,address);
                if(intn.resolveActivity(getPackageManager())!=null){
                    startActivity(intn);
                }
                loc_text.setText(null);
            }
        });
        text_share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String share=text_edit.getText().toString();
                String mimetype="text/plain";
                ShareCompat.IntentBuilder
                        .from(MainActivity.this)
                        .setType(mimetype)
                        .setChooserTitle("Share this text with: ")
                        .setText(share)
                        .startChooser();
            }
        });
    }
}
