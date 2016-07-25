package com.beta.disableactivityapp;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import java.util.ArrayList;

public class PkgActivity extends AppCompatActivity {
    ListView pkgActivity;
    ArrayList<PackageInfo>activityList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pkg);
        pkgActivity = $(R.id.activitylist);
        pkgActivity.setAdapter(new ListViewAdapter(this,activityList));
    }

    void getActivity(Context context){
        if (activityList == null){
            activityList = new ArrayList<>();
        }else{
            activityList.clear();
        }
        getIntent().getStringExtra("name");
    }

    <T extends View> T $(int id){
        return (T)findViewById(id);
    }
}
