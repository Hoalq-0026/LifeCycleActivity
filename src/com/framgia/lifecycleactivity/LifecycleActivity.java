package com.framgia.lifecycleactivity;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.widget.RemoteViews;
import android.widget.Toast;

/**
 * @author Admin
 * @date Feb 19, 2014
 */
public class LifecycleActivity extends Activity
{   private static final String LOG_TAG = "LifecycleActivity";

    private NotificationManager notifyMgr;
    private boolean enableNotifications = true;
    private final String className;
    
    public LifecycleActivity(){
        super();
        this.className = this.getClass().getName();
    }
    
//    public LifecycleActivity(final boolean enableNotifications){
//        this();
//        this.enableNotifications = enableNotifications;
//    }
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        notifyMgr = (NotificationManager)this.getSystemService(Context.NOTIFICATION_SERVICE);
        debugEvent("onCreate");
    }
    
    // notify helper
    private void debugEvent(String method) {
        long ts = System.currentTimeMillis();
        Log.i(LOG_TAG, "***"+method+" "+className +" "+ts);
        if (enableNotifications) {
            Notification notification = new Notification(R.drawable.star, "Lifecycle Event "+method , 0);
            RemoteViews notificationContentView = new RemoteViews(getPackageName(), R.layout.custom_notification_layout);
            notification.contentView = notificationContentView;
            notification.contentIntent = PendingIntent.getActivity(this, 0, null, 0);
            notification.flags = Notification.FLAG_AUTO_CANCEL;
            
            notificationContentView.setImageViewResource(R.id.image, android.R.drawable.btn_star);
            notificationContentView.setTextViewText(R.id.lifecycle_class, getClass().getName());
            notificationContentView.setTextViewText(R.id.lifecycle_method, method);
            notificationContentView.setTextColor(R.id.lifecycle_method, getResources().getColor(android.R.color.black));
            notificationContentView.setTextViewText(R.id.lifecycle_timestamp, Long.toString(ts));
            
            notifyMgr.notify((int)System.currentTimeMillis(), notification);
        }
    }

    @Override
    protected void onDestroy() {
        debugEvent("onDestroy");
        super.onDestroy();
    }
    @Override
    protected void onPause() {
        debugEvent("onPause");
        super.onPause();
    }
    @Override
    protected void onRestart() {
        debugEvent("onRestart");
        super.onRestart();
    }
    @Override
    protected void onResume() {
        debugEvent("onResume");
        super.onResume();
    }
    @Override
    protected void onStart() {
        debugEvent("onStart");
        super.onStart();
    }
    @Override
    protected void onStop() {
        debugEvent("onStop");
        super.onStop();
    }

   
    // state related
    
    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        debugEvent("onRestoreInstanceState");
        super.onRestoreInstanceState(savedInstanceState);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        debugEvent("onSaveInstanceState");
        super.onSaveInstanceState(outState);
    }

  
  
    // configuration related
    
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        debugEvent("onConfigurationChanged");
        super.onConfigurationChanged(newConfig);
    }

    @Override
    public Object onRetainNonConfigurationInstance() {
        debugEvent("onRetainNonConfigurationInstance");
        return super.onRetainNonConfigurationInstance();
    }

    //other handy Activity methods

    @Override
    public void finish() {
        debugEvent("finish");
        super.finish();
    }

    @Override
    public boolean isFinishing() {
        debugEvent("isFinishing");
        return super.isFinishing();
    }

    @Override
    public void onLowMemory() {
        Toast.makeText(this, "onLowMemory", Toast.LENGTH_SHORT).show();
        super.onLowMemory();
    }
 
}
