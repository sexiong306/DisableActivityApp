package com.beta.disableactivityapp;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * author：pudgeli on 16/7/25
 * email：pudgeli@tencent.com
 */
public class PkgListViewAdapter extends ListViewAdapter{
    public PkgListViewAdapter(Context context, List content) {
        super(context, content);
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
        PackageInfo packageInfo = (PackageInfo)content.get(position);
        vh.mTextView.setText(packageInfo.packageName);
        final String name = packageInfo.packageName;
        vh.mTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent();
                i.setClass(mContext,PkgActivity.class);
                i.putExtra("name",name);
                mContext.startActivity(i);
            }
        });
        return convertView;
    }
}
