package com.framgia.lifecycleactivity;


import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.TextView;

public class MainActivity extends LifecycleActivity {
    private Button finish;
    private Button activity2;
    private Chronometer chrono;
    
    private TextView tvFirstTime;
    private TextView tvLastTime;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        finish = (Button)findViewById(R.id.finishButton);
        finish.setOnClickListener(new OnClickListener() {
            
            @Override
            public void onClick(View v) {
              finish();
            }
        });
//        Animation mAnimation = new AlphaAnimation(1, 0);
//        mAnimation.setDuration(200);
//        mAnimation.setInterpolator(new LinearInterpolator());
//        mAnimation.setRepeatCount(Animation.INFINITE);
//        mAnimation.setRepeatMode(Animation.REVERSE); 
        
        activity2 = (Button)findViewById(R.id.activity2Button);
//        activity2.startAnimation(mAnimation);
        activity2.setOnClickListener(new OnClickListener() {
            
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, Activity2.class));
            }
        });
        chrono = (Chronometer)findViewById(R.id.chronometer);
        
        tvFirstTime = (TextView)findViewById(R.id.tv_firstInstallTime);
        tvLastTime =  (TextView)findViewById(R.id.tv_lastUpdateTime);
        PackageManager packageMng = getPackageManager();
        try {
            long firstInstallTime = packageMng.getPackageInfo("com.workpail.inkpad.notepad.notes",PackageManager.GET_ACTIVITIES).firstInstallTime;
            tvFirstTime.setText("First Install Time"+ String.valueOf(firstInstallTime));
            
            long lastUpdateTime = packageMng.getPackageInfo("com.workpail.inkpad.notepad.notes", PackageManager.GET_ACTIVITIES).lastUpdateTime;
            tvLastTime.setText("Last Update Time"+String.valueOf(lastUpdateTime));
        } catch (Exception e) {
            Log.i("LOG_TAG", e.getMessage());
        }
        
    }
    @Override
    protected void onPause() {
        chrono.stop();
        super.onPause();
        
    }
    @Override
    protected void onResume() {
        super.onResume();
        chrono.setBase(SystemClock.elapsedRealtime());
        chrono.start();
    }

    
}
