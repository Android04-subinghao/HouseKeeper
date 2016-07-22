package com.example.admin.housekeeper.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.admin.housekeeper.R;
import com.example.admin.housekeeper.bean.PhoneNumber;

import java.util.ArrayList;

/**
 * Created by admin on 2016/7/14.
 */
public class PhoneAdapter extends BaseAdapter {
    private ArrayList<PhoneNumber> mList;
    private Context                mContext;

    public PhoneAdapter( Context context,ArrayList<PhoneNumber> list) {
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
        ViewHolder holder;
        if(convertView == null){
            holder = new ViewHolder();
            convertView = View.inflate(mContext, R.layout.phone_number,null);
            holder.mTV_id = (TextView) convertView.findViewById(R.id.tv_phone_id);
            holder.mTV_name = (TextView) convertView.findViewById(R.id.tv_phone_name);
            holder.mTV_number = (TextView) convertView.findViewById(R.id.tv_phone_number);
            convertView.setTag(holder);
        }else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.mTV_id.setText(mList.get(position).getId());
        holder.mTV_name.setText(mList.get(position).getName());
        holder.mTV_number.setText(mList.get(position).getNumber());
        return convertView;
    }
    private static class ViewHolder{
        TextView mTV_id;
        TextView mTV_name;
        TextView mTV_number;
    }
}
