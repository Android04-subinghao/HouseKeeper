package com.example.admin.housekeeper.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.admin.housekeeper.R;
import com.example.admin.housekeeper.bean.PhoneInfo;

import java.util.ArrayList;

/**
 * Created by admin on 2016/7/20.
 */
public class PhoneListBoxAdapter extends BaseAdapter {
    private Context mContext;
    private ArrayList<PhoneInfo> mList;

    public PhoneListBoxAdapter(Context context, ArrayList<PhoneInfo> list) {
        mContext = context;
        mList = list;
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
        ViewHolder holder;
        if(convertView == null){
            holder = new ViewHolder();
            convertView = View.inflate(mContext, R.layout.phonelistbox_item,null);
            holder.mImageView = (ImageView) convertView.findViewById(R.id.iv_phonelistbox_icon);
            holder.mTV_name = (TextView) convertView.findViewById(R.id.tv_phonelistbox_name);
            holder.mTV_info = (TextView) convertView.findViewById(R.id.tv_phonelistbox_info);
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }
        holder.mImageView.setBackgroundResource(mList.get(position).getMap());
        holder.mTV_name.setText(mList.get(position).getHeadLine());
        holder.mTV_info.setText(mList.get(position).getSubHand());
        return convertView;
    }
    private static class ViewHolder{
        ImageView mImageView;
        TextView mTV_name;
        TextView mTV_info;
    }
}
