package com.beta.disableactivityapp;

import android.content.Context;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PkgActivity extends AppCompatActivity {
    ListView pkgActivity;
    List<ActivityInfo> activityList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pkg);
        pkgActivity = $(R.id.activitylist);
         getActivity(this);
        pkgActivity.setAdapter(new ActivityListAdapter(this,activityList,getIntent().getStringExtra("name")));
    }

    void getActivity(Context context){
        if (activityList == null){
            activityList = new ArrayList<>();
        }else{
            activityList.clear();
        }
       String pkgName = getIntent().getStringExtra("name");
        try {
            ActivityInfo[] activities = this.getPackageManager().getPackageInfo(pkgName, PackageManager.GET_ACTIVITIES).activities;
            activityList = Arrays.asList(activities);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
    }

    <T extends View> T $(int id){
        return (T)findViewById(id);
    }
}
