package com.framgia.lifecycleactivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

/**
 * @author Admin
 * @date Feb 19, 2014
 */
public class Activity2 extends LifecycleActivity{
    private Button activity3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity2_layout);
        activity3 =(Button)findViewById(R.id.activity3Button);
        activity3.setOnClickListener(new OnClickListener() {
            
            @Override
            public void onClick(View v) {
               startActivity(new Intent(Activity2.this, Activity3.class));
            }
        });
    }
 
}
