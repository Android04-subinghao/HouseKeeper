package com.example.admin.housekeeper.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.admin.housekeeper.R;
import com.example.admin.housekeeper.bean.Live;

import java.util.ArrayList;

/**
 * Created by admin on 2016/7/14.
 */
public class MyAdapter extends BaseAdapter {
    private ArrayList<Live> mList;
    private Context         context;
    public MyAdapter(Context context,ArrayList<Live> list) {
        this.context = context;
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
        ViewHodler hodler;
        if(convertView == null){
            hodler = new ViewHodler();
            convertView = View.inflate(context, R.layout.item,null);
            hodler.mTextView = (TextView) convertView.findViewById(R.id.tv_name);
            convertView.setTag(hodler);
        }else{
             hodler = (ViewHodler) convertView.getTag();
        }


//        TextView tvNumber = (TextView) convertView.findViewById(R.id.tv_number);
         hodler.mTextView.setText(mList.get(position).getName());
//        tvNumber.setText(mList.get(position).getNumber());
        return convertView;
    }
    private static class ViewHodler{
        TextView mTextView;
    }
}
