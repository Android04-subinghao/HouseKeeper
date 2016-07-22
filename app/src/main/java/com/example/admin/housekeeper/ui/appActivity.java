package com.example.admin.housekeeper.ui;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.admin.housekeeper.R;
import com.example.admin.housekeeper.adapter.AppInfoAdapter;

import java.util.ArrayList;
import java.util.List;

class AppActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {
    private ListView mView;
    private ArrayList<PackageInfo> mList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app);
        mView = (ListView) findViewById(R.id.lv_app);
        mList = new ArrayList<>();
        info();
        mView.setAdapter(new AppInfoAdapter(this,mList));
        mView.setOnItemClickListener(this);

    }
    public void info() {

        PackageManager manager = this.getPackageManager();
        List<PackageInfo> infoList = manager.getInstalledPackages(0);
        for (int i = 0; i < infoList.size(); i++) {
            PackageInfo packageInfo = infoList.get(i);
            if ((packageInfo.applicationInfo.flags & packageInfo.applicationInfo.FLAG_SYSTEM) <= 0) {
                mList.add(packageInfo);
            }

        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        String packageName = mList.get(position).packageName;
        Intent intent = new Intent(Intent.ACTION_DELETE);
        intent.setData(Uri.parse("package:"+packageName));
        startActivity(intent);
    }
}
