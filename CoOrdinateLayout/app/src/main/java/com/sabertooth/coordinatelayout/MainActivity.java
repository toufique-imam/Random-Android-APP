package com.sabertooth.coordinatelayout;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

class FloatingActionButtonBehavior extends CoordinatorLayout.Behavior<com.getbase.floatingactionbutton.FloatingActionButton>{
    FloatingActionButtonBehavior(){}
    FloatingActionButtonBehavior(Context context,AttributeSet attrs){}

    @Override
    public boolean layoutDependsOn(@NonNull CoordinatorLayout parent, @NonNull com.getbase.floatingactionbutton.FloatingActionButton child, @NonNull View dependency) {
        return dependency instanceof Snackbar.SnackbarLayout;
    }

    @Override
    public boolean onDependentViewChanged(@NonNull CoordinatorLayout parent, @NonNull com.getbase.floatingactionbutton.FloatingActionButton child, @NonNull View dependency) {
        float translationY=Math.min(0,dependency.getTranslationY()-dependency.getHeight());
        child.setTranslationY(translationY);
        return true;
    }
}
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        com.getbase.floatingactionbutton.FloatingActionButton fab=findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(v,"hello Sncakbar",Snackbar.LENGTH_LONG).show();
            }
        });
    }
}
