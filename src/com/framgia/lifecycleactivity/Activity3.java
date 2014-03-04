package com.framgia.lifecycleactivity;

import java.util.Date;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

/**
 * @author Admin
 * @date Feb 19, 2014
 */
public class Activity3 extends LifecycleActivity{
    private static final String COUNT_KEY ="cKey";
    
    private TextView numResumes;
    private int count;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity3_layout);
        
        numResumes = (TextView)findViewById(R.id.numResumes);
        
        Date date = (Date)this.getLastNonConfigurationInstance();
        if (date != null) {
            Toast.makeText(this, "\"LastNonConfiguration\" object present:" + date , Toast.LENGTH_LONG).show();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        numResumes.setText(String.valueOf(count));
        count++;
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        if ((savedInstanceState != null) && savedInstanceState.containsKey(COUNT_KEY)) {
            count = savedInstanceState.getInt(COUNT_KEY);
        }
        super.onRestoreInstanceState(savedInstanceState);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putInt(COUNT_KEY, count);
        super.onSaveInstanceState(outState);
    }

    @Override
    public Object onRetainNonConfigurationInstance() {
        // TODO Auto-generated method stub
        return new Date();
    }
    
}
