package com.sabertooth.calculator;

import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.concurrent.atomic.AtomicReference;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button add,sub,mul,div,mod,percentage_,power,logfunc;
        add=findViewById(R.id.addition);
        sub=findViewById(R.id.Subtraction);
        mul=findViewById(R.id.Multiplication);
        div=findViewById(R.id.Division);
        mod=findViewById(R.id.Mod);
        power=findViewById(R.id.power);
        percentage_=findViewById(R.id.percentage);
        logfunc=findViewById(R.id.LOGxY);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText t1,t2;
                t1=findViewById(R.id.Number_1);
                t2=findViewById(R.id.Number_2);
                TextView ans = findViewById(R.id.Result);
                String s1,s2;
                s1=t1.getText().toString();
                s2=t2.getText().toString();
                try {
                    double num1,num2;
                    num1=Double.parseDouble(s1);
                    num2=Double.parseDouble(s2);
                    ans.setText(String.format("%s + %s= %f", s1, s2,num1+num2));
                }
                catch (NumberFormatException e)
                {
                    ans.setText("Invalid");
                }
            }
        });
        sub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText t1,t2;
                t1=findViewById(R.id.Number_1);
                t2=findViewById(R.id.Number_2);
                TextView ans = findViewById(R.id.Result);
                String s1,s2;
                s1=t1.getText().toString();
                s2=t2.getText().toString();
                try {
                    double num1,num2;
                    num1=Double.parseDouble(s1);
                    num2=Double.parseDouble(s2);
                    ans.setText(String.format("%s - %s= %f", s1, s2,num1-num2));
                }
                catch (NumberFormatException e)
                {
                    ans.setText("Invalid");
                }
            }
        });
        mul.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText t1,t2;
                t1=findViewById(R.id.Number_1);
                t2=findViewById(R.id.Number_2);
                TextView ans = findViewById(R.id.Result);
                String s1,s2;
                s1=t1.getText().toString();
                s2=t2.getText().toString();
                try {
                    double num1,num2;
                    num1=Double.parseDouble(s1);
                    num2=Double.parseDouble(s2);
                    ans.setText(String.format("%s * %s= %f", s1, s2,num1*num2));
                }
                catch (NumberFormatException e)
                {
                    ans.setText("Invalid");
                }
            }
        });
        div.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText t1,t2;
                t1=findViewById(R.id.Number_1);
                t2=findViewById(R.id.Number_2);
                TextView ans = findViewById(R.id.Result);
                String s1,s2;
                s1=t1.getText().toString();
                s2=t2.getText().toString();
                try {
                    double num1,num2;
                    num1=Double.parseDouble(s1);
                    num2=Double.parseDouble(s2);
                    ans.setText(String.format("%s / %s= %f", s1, s2,num1/num2));
                }
                catch (NumberFormatException e)
                {
                    ans.setText("Invalid");
                }
            }
        });

        mod.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText t1,t2;
                t1=findViewById(R.id.Number_1);
                t2=findViewById(R.id.Number_2);
                TextView ans = findViewById(R.id.Result);
                String s1,s2;
                s1=t1.getText().toString();
                s2=t2.getText().toString();
                try {
                    int num1,num2;
                    num1=Integer.parseInt(s1);
                    num2=Integer.parseInt(s2);
                    int num3=num1%num2;
                    ans.setText(""+num1+" % "+num2+" = "+num3);
                }
                catch (NumberFormatException e)
                {
                    ans.setText("Invalid");
                }
            }
        });

        power.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText t1,t2;
                t1=findViewById(R.id.Number_1);
                t2=findViewById(R.id.Number_2);
                TextView ans = findViewById(R.id.Result);
                String s1,s2;
                s1=t1.getText().toString();
                s2=t2.getText().toString();
                try {
                    double num1,num2;
                    num1=Double.parseDouble(s1);
                    num2=Double.parseDouble(s2);
                    ans.setText(String.format("%s ^ %s= %f", s1, s2,Math.pow(num1,num2)));
                }
                catch (NumberFormatException e)
                {
                    ans.setText("Invalid");
                }
            }
        });

        logfunc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText t1,t2;
                t1=findViewById(R.id.Number_1);
                t2=findViewById(R.id.Number_2);
                TextView ans = findViewById(R.id.Result);
                String s1,s2;
                s1=t1.getText().toString();
                s2=t2.getText().toString();
                try {
                    double num1,num2;
                    num1=Double.parseDouble(s1);
                    num2=Double.parseDouble(s2);
                    ans.setText(String.format("log %s in base %s = %f", s1, s2,Math.log(num1)/Math.log(num2)));
                }
                catch (NumberFormatException e)
                {
                    ans.setText("Invalid");
                }
            }
        });

        percentage_.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText t1,t2;
                t1=findViewById(R.id.Number_1);
                t2=findViewById(R.id.Number_2);
                TextView ans = findViewById(R.id.Result);
                String s1,s2;
                s1=t1.getText().toString();
                s2=t2.getText().toString();
                try {
                    double num1,num2;
                    num1=Double.parseDouble(s1);
                    num2=Double.parseDouble(s2);
                    ans.setText(String.format("profit in %s at rate %s  = %f", s1, s2,num1*num2/100));
                }
                catch (NumberFormatException e)
                {
                    ans.setText("Invalid");
                }
            }
        });

}
}
