package com.beta.disableactivityapp;

import android.content.ComponentName;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.io.DataOutputStream;
import java.io.OutputStream;
import java.util.List;

/**
 * author：pudgeli on 16/7/25
 * email：pudgeli@tencent.com
 */
public class ActivityListAdapter extends ListViewAdapter<ActivityInfo>{
    String pkgName;
    public ActivityListAdapter(Context context, List content,String pkgName) {
        super(context, content);
        this.pkgName = pkgName;
    }

    /**
     * Get a View that displays the data at the specified position in the data set. You can either
     * create a View manually or inflate it from an XML layout file. When the View is inflated, the
     * parent View (GridView, ListView...) will apply default layout parameters unless you use
     * {@link LayoutInflater#inflate(int, ViewGroup, boolean)}
     * to specify a root view and to prevent attachment to the root.
     *
     * @param position    The position of the item within the adapter's data set of the item whose view
     *                    we want.
     * @param convertView The old view to reuse, if possible. Note: You should check that this view
     *                    is non-null and of an appropriate type before using. If it is not possible to convert
     *                    this view to display the correct data, this method can create a new view.
     *                    Heterogeneous lists can specify their number of view types, so that this View is
     *                    always of the right type (see {@link #getViewTypeCount()} and
     *                    {@link #getItemViewType(int)}).
     * @param parent      The parent that this view will eventually be attached to
     * @return A View corresponding to the data at the specified position.
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = super.getView(position, convertView, parent);
        ViewHolder vh = (ViewHolder) convertView.getTag();
        ActivityInfo activityInfo = content.get(position);
        vh.mTextView.setText(activityInfo.name);
        final String activityName = activityInfo.name;
        vh.mTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ComponentName componentName = new ComponentName(pkgName,activityName);
                PackageManager packageManager = mContext.getPackageManager();
                int status = packageManager.getComponentEnabledSetting(componentName);
                if (status == PackageManager.COMPONENT_ENABLED_STATE_DEFAULT
                        || status == PackageManager.COMPONENT_ENABLED_STATE_ENABLED){
//                    packageManager.setComponentEnabledSetting(componentName,PackageManager.COMPONENT_ENABLED_STATE_DISABLED,PackageManager.DONT_KILL_APP);
                    try {
                        execCmd("pm disable com.qq.bugly.beta.demo/com.tencent.bugly.beta.ui.BetaActivity");
                        Toast.makeText(mContext,"disable",Toast.LENGTH_SHORT).show();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }else{
//                    packageManager.setComponentEnabledSetting(componentName, PackageManager.COMPONENT_ENABLED_STATE_ENABLED,PackageManager.DONT_KILL_APP);
                    try {
                        execCmd("pm enable com.qq.bugly.beta.demo/com.tencent.bugly.beta.ui.BetaActivity");
                        Toast.makeText(mContext,"enable",Toast.LENGTH_SHORT).show();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

            }
        });
        return convertView;
    }


    void execCmd(String cmd){
        try{
            Process process = Runtime.getRuntime().exec("su");
            OutputStream os = process.getOutputStream();
            DataOutputStream dos = new DataOutputStream(os);
            dos.writeBytes(cmd);
            dos.flush();
            dos.close();
            os.close();
        }
        catch(Throwable t)
        {
            t.printStackTrace();
        }

    }
}
