package com.kuet.sabertooth.helloworld;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

//import android.widget.Toolbar;

public class MainActivity extends AppCompatActivity {

    // Used to load the 'native-lib' library on application startup.
    static {
        System.loadLibrary("native-lib");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       // Toolbar toolbar=(Toolbar) findViewById(R.id.a_main_toolbar);
        //setSupportActionBar(toolbar);
        Button AddBtn=(Button)findViewById(R.id.button);
        AddBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText Num1=(EditText)findViewById(R.id.Number_1);
                EditText Num2=(EditText)findViewById(R.id.Number_2);
                TextView resultText=(TextView)findViewById(R.id.ResulttextView);
                String tmp1=Num1.getText().toString();
                String tmp2=Num2.getText().toString();
                boolean f1;
                long num_1=0,num_2=0;
                f1=false;
                try{
                    num_1=Integer.parseInt(tmp1);
                }
                catch(NumberFormatException e){
                    f1=true;
                    resultText.setText(tmp1+" is not a number");
                }
                try {
                    num_2 = Integer.parseInt(tmp2);
                }
                catch (NumberFormatException e){
                    f1=true;
                    resultText.setText(tmp2+" is not a number");
                }
                if(f1==false) {
                    long  result = num_1+num_2 ;
                    resultText.setText(result+" ");
                }
                else{
                    resultText.setText("Invalid Input");
                }
            }
        });
        // Example of a call to a native method
        //TextView tv = (TextView) findViewById(R.id.sample_text);
        //tv.setText(stringFromJNI());
    }

    /**
     * A native method that is implemented by the 'native-lib' native library,
     * which is packaged with this application.
     */
    public native String stringFromJNI();
}
