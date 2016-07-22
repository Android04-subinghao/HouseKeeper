package com.example.admin.housekeeper.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.admin.housekeeper.bean.Function;
import com.example.admin.housekeeper.R;

import java.util.ArrayList;

/**
 * Created by admin on 2016/7/18.
 */
public class FunctionAdapter extends BaseAdapter {
    Context mContext;
    ArrayList<Function> mList;

    public FunctionAdapter(Context context, ArrayList<Function> list) {
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
        if (convertView == null){
            holder = new ViewHolder();
            convertView = View.inflate(mContext,R.layout.gridview_main_item,null);
            holder.mImageView = (ImageView) convertView.findViewById(R.id.iv_main_grid);
            holder.mTextView = (TextView) convertView.findViewById(R.id.tv_main_grid);
            convertView.setTag(holder);
        }else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.mImageView.setBackgroundResource(mList.get(position).getIcon());
        holder.mTextView.setText(mList.get(position).getName());
        return convertView;
    }
    private static class ViewHolder{
        ImageView mImageView;
        TextView mTextView;
    }
}
