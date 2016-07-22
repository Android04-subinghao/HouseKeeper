package com.example.admin.housekeeper.adapter;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.admin.housekeeper.R;

import java.util.ArrayList;

/**
 * Created by admin on 2016/7/19.
 */
public class AppInfoAdapter extends BaseAdapter {
    private Context mContext;
    private ArrayList<PackageInfo> mList;

    public AppInfoAdapter( Context context,ArrayList<PackageInfo> list) {
        mList = list;
        mContext = context;
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        viewHolder holder;
        PackageManager manager = mContext.getPackageManager();
        if (convertView == null){
            holder = new viewHolder();
            convertView = View.inflate(mContext, R.layout.appinfo_item,null);
            holder.mIV_Icon = (ImageView) convertView.findViewById(R.id.iv_appIcon);
            holder.mTV_Name = (TextView) convertView.findViewById(R.id.tv_appName);
            holder.mTV_Version = (TextView) convertView.findViewById(R.id.tv_appVersion);
            holder.mTV_PaackageName = (TextView) convertView.findViewById(R.id.tv_appPackageName);
            convertView.setTag(holder);
        }else{
            holder = (viewHolder) convertView.getTag();
        }

        holder.mIV_Icon.setImageDrawable(mList.get(position).applicationInfo.loadIcon(manager));
        holder.mTV_Name.setText(mList.get(position).applicationInfo.loadLabel(manager).toString());
        holder.mTV_Version.setText(mList.get(position).versionName);
        holder.mTV_PaackageName.setText(mList.get(position).packageName);
        return convertView;
    }
    private static class viewHolder{
        ImageView mIV_Icon;
        TextView mTV_PaackageName;
        TextView mTV_Name;
        TextView mTV_Version;

    }
}
