package com.example.admin.housekeeper.ui;

import android.app.ActivityManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.telephony.TelephonyManager;
import android.text.format.Formatter;
import android.util.Log;
import android.view.Display;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.admin.housekeeper.R;
import com.example.admin.housekeeper.adapter.PhoneListBoxAdapter;
import com.example.admin.housekeeper.bean.Bettery;
import com.example.admin.housekeeper.bean.PhoneInfo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileFilter;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Pattern;

public class PhoneInfoActivity extends AppCompatActivity {
    private static final String TAG = "PhoneInfoActivity";
    private ArrayList<PhoneInfo> mList;
    private ListView mView;
    private PhoneListBoxAdapter mAdapter;
    private BatteryBroadCast mReceiver;
    private ProgressBar mBar;
    private TextView mTVbet;
    private TextView mTVtem;
    private Bettery mBttey;
    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            Bettery obj = (Bettery) msg.obj;
            int currentBattery = obj.getCurrentBattery();
            double currentBtTemperature = obj.getCurrentBtTemperature();
            mBar.setProgress(currentBattery);
            Log.d(TAG, "currentBattery: "+currentBattery+"\n"+"currentBtTemperature: "+currentBtTemperature);
            mTVbet.setText("电量:"+currentBattery+"");
            mTVtem.setText("温度:"+currentBtTemperature+"");
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone_info);
        mList = new ArrayList<>();
        mView = (ListView) findViewById(R.id.iv_phoneinfo);
        mBar = (ProgressBar) findViewById(R.id.progressBar);
        mTVbet = (TextView) findViewById(R.id.tv_phoneinfo_bet);
        mTVtem = (TextView) findViewById(R.id.tv_phoneinfo_tem);
        init();
        mAdapter = new PhoneListBoxAdapter(this,mList);
        mView.setAdapter(mAdapter);
        mReceiver = new BatteryBroadCast();
        IntentFilter filter = new IntentFilter(Intent.ACTION_BATTERY_CHANGED);
        registerReceiver(mReceiver,filter);



    }
    public void init(){
        String cupType = null;
        //获取设备品牌
        String brand = Build.BRAND;
        //获取基带版本
        String radioVersion = Build.getRadioVersion();
        //获取设备CPU类型
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            String[] supportedAbis = Build.SUPPORTED_ABIS;
                    cupType = supportedAbis[0];

        }
        //获取CPU数量
        File dir = new File("/sys/devices/system/cpu/");
        File[] files =dir.listFiles(new CpuFilter());
        int CUP = files.length;
        //获取设备制造商名称
        String manufacturer = Build.MANUFACTURER;
        //获取设备型号名称
        String model = Build.MODEL;
        //获取手机IMSI号
        TelephonyManager manager= (TelephonyManager) getSystemService(TELEPHONY_SERVICE);
        String subscriberId = manager.getSubscriberId();
        //获取手机IMEI
        String deviceId = manager.getDeviceId();
//        //获取wifi是否开启
//        WifiManager wifi= (WifiManager) getSystemService(WIFI_SERVICE);
//        boolean wifiEnabled = wifi.isWifiEnabled();
        //获取屏幕分辨率
        Display display = getWindowManager().getDefaultDisplay();
        int width = display.getWidth();
        int height = display.getHeight();
        String dis = width+"*"+height;
        //获取运行内存
        String formatTotalSpace = Formatter.formatFileSize(this,getMemory());
        //获取空闲内存
        ActivityManager.MemoryInfo info = new ActivityManager.MemoryInfo();
        ActivityManager am = (ActivityManager) getSystemService(ACTIVITY_SERVICE);
        am.getMemoryInfo(info);
        long availMem = info.availMem;
        String FreeArea = Formatter.formatFileSize(this, availMem);
        //设备设置版本号
        String id = Build.ID;
        //将数据添加到集合中
        mList.add(new PhoneInfo(R.drawable.diyige,"  设备品牌:"+brand,"  制造商名称:"+manufacturer));
        mList.add(new PhoneInfo(R.drawable.dierge,"  设备型号名称:"+model,"  基带版本:"+radioVersion));
        mList.add(new PhoneInfo(R.drawable.disange,"  CPU类型:"+cupType,"  CPU数量 : "+CUP));
        mList.add(new PhoneInfo(R.drawable.disige,"  手机IMSI号:"+subscriberId,"  手机IMEI:"+deviceId));
        mList.add(new PhoneInfo(R.drawable.diwuge,"  屏幕分辨率:"+dis,"  设备设置版本号:"+id));
        mList.add(new PhoneInfo(R.drawable.diliuge,"  运行内存 : "+formatTotalSpace,"  空闲内存 : "+FreeArea));
//        Log.d(TAG, " 获取设备品牌 : " + brand);
//        Log.d(TAG, " 基带版本 : " + radioVersion);
//        Log.d(TAG, " CPU类型 : " + cupType);
//        Log.d(TAG, " CPU数量 : " + CUP);
//        Log.d(TAG, " 制造商名称 : " + manufacturer);
//        Log.d(TAG, " 设备型号名称 : " + model);
//        Log.d(TAG, " 手机IMSI号 : " + subscriberId);
//        Log.d(TAG, " 手机IMEI : " + deviceId);
////        Log.d(TAG, " wifi是否开启 : " + wifi);
//        Log.d(TAG, " 屏幕分辨率 : " + width+"  "+height);
//        Log.d(TAG, " 运行内存 : " + formatTotalSpace);
//        Log.d(TAG, " 空闲内存 : " + FreeArea);
//        Log.d(TAG, " 设备设置版本号 : " + id);

    }
    //获取CPU数量
    class CpuFilter implements FileFilter {
        public boolean accept(File pathname) {
            if (Pattern.matches("cpu[0-9]", pathname.getName())) {
                return true;
            }
            return false;
        }
    }

    /**
     * 获取系统内存
     * @return
     */
    private static long getMemory(){
        try {
            FileReader reader = new FileReader("/proc/meminfo");
            BufferedReader br = new BufferedReader(reader);
            String text =br.readLine();
            String [] split = text.split("\\s+");
            return  Long.valueOf(split[1]) * 1024;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return 0;
    }
    public class BatteryBroadCast extends BroadcastReceiver{
        Integer maxBattery;
        Integer currentBattery;
        double currentBtTemperature;
        @Override
        public void onReceive(Context context, Intent intent) {
            if(intent.getAction().equals(Intent.ACTION_BATTERY_CHANGED)){
                Bundle bundle = intent.getExtras();
                //最大电量
                 maxBattery = (Integer) bundle.get(BatteryManager.EXTRA_SCALE);
                //当前电量
                 currentBattery = (Integer) bundle.get(BatteryManager.EXTRA_LEVEL);
                //电池温度
                Integer it = (Integer) bundle.get(BatteryManager.EXTRA_TEMPERATURE);
                mBttey =  new Bettery(currentBattery.intValue(),currentBtTemperature);
                currentBtTemperature = it / 10.0;

                Message message = Message.obtain();
                message.obj = mBttey;
                mHandler.sendMessage(message);
//                Log.d(TAG, "maxBattery: "+maxBattery+"currentBattery"+currentBattery+"currentBtTemperature"+currentBtTemperature);
            }
        }
    }

}
