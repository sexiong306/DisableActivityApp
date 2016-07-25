package com.beta.disableactivityapp;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    ListView sysApp;
    ListView userApp;
    ArrayList<PackageInfo> sysAppList;
    ArrayList<PackageInfo> userAppList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sysApp = $(R.id.sysapplist);
        userApp = $(R.id.userapplist);
        getAppInfo(getApplicationContext());

        sysApp.setAdapter(new PkgListViewAdapter(this,sysAppList));
        userApp.setAdapter(new PkgListViewAdapter(this,userAppList));

    }

    void getAppInfo(Context context) {
        if (sysAppList == null){
            sysAppList = new ArrayList<>();
        }else{
            sysAppList.clear();
        }

        if (userAppList == null){
            userAppList = new ArrayList<>();
        }else{
            userAppList.clear();
        }

        PackageManager packageManager = context.getPackageManager();

        List<PackageInfo> packageInfos = packageManager.getInstalledPackages(0);
        for (PackageInfo packageInfo : packageInfos){
            if ((packageInfo.applicationInfo.flags & ApplicationInfo.FLAG_SYSTEM) > 0){
                sysAppList.add(packageInfo);
            }else{
                userAppList.add(packageInfo);
            }
        }

    }

    <T extends View> T $(int id){
        return (T)findViewById(id);
    }
}
