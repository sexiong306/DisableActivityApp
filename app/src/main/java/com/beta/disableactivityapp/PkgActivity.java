package com.beta.disableactivityapp;

import android.content.Context;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

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
        if (TextUtils.isEmpty(pkgName)){
            return;
        }
        try {
            PackageManager packageManager = this.getPackageManager();
            if (packageManager.getPackageInfo(pkgName, PackageManager.GET_ACTIVITIES) != null) {
                ActivityInfo[] activities = this.getPackageManager().getPackageInfo(pkgName, PackageManager.GET_ACTIVITIES).activities;
                if (activities == null){
                    return;
                }
                activityList = Arrays.asList(activities);
            }
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * Callback for the result from requesting permissions. This method
     * is invoked for every call on {@link #requestPermissions(String[], int)}.
     * <p>
     * <strong>Note:</strong> It is possible that the permissions request interaction
     * with the user is interrupted. In this case you will receive empty permissions
     * and results arrays which should be treated as a cancellation.
     * </p>
     *
     * @param requestCode  The request code passed in {@link #requestPermissions(String[], int)}.
     * @param permissions  The requested permissions. Never null.
     * @param grantResults The grant results for the corresponding permissions
     *                     which is either {@link PackageManager#PERMISSION_GRANTED}
     *                     or {@link PackageManager#PERMISSION_DENIED}. Never null.
     * @see #requestPermissions(String[], int)
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 0){
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED){
                Toast.makeText(this,"granted success",Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(this,"granted failed",Toast.LENGTH_SHORT).show();
            }
        }
    }

    <T extends View> T $(int id){
        return (T)findViewById(id);
    }
}
