package com.qimiaowa.test_restart;

import android.app.ActivityManager;
import android.app.Service;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Handler;
import android.os.IBinder;
import android.os.RemoteException;


public class MainService extends Service {
    public static ICat mICat;
    private boolean isStart;
    private Handler mHandler;
    private MyBinder mMyBinder;
    private MyConn mMyConn;
    private Runnable mRunnable = new Runnable() {
        public void run() {
            if (!MainService.this.isStart) {
                return;
            }
            if (isBackground(MainService.this.getApplicationContext()) && SettingPreUtil.getInstance().getProtectFlag()) {
                Intent intent = new Intent(MainService.this.getApplicationContext(), MainActivity.class);
                intent.addCategory("android.intent.category.LAUNCHER");
                intent.setAction("android.intent.action.MAIN");
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                MainService.this.startActivity(intent);
                MainService.this.startService(new Intent(MainService.this.getApplicationContext(), MainService.class));
                MainService.this.startService(new Intent(MainService.this.getApplicationContext(), ProtectService.class));
                return;
            }
            MainService.this.mHandler.postDelayed(this, 3000);
        }
    };


    class MyConn implements ServiceConnection {
        MyConn() {
        }

        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            MainService.mICat = ICat.Stub.asInterface(iBinder);
        }


        public void onServiceDisconnected(ComponentName componentName) {
            MainService.this.startService(new Intent(MainService.this, ProtectService.class));
            MainService.this.bindService(new Intent(MainService.this, ProtectService.class), MainService.this.mMyConn, BIND_IMPORTANT);
        }
    }

    class MyBinder extends ICat.Stub {
        MyBinder() {
        }

        public void getServiceName() throws RemoteException {
        }
    }

    public IBinder onBind(Intent intent) {
        return this.mMyBinder;
    }

    public void onCreate() {
        super.onCreate();
        this.mMyBinder = new MyBinder();
        if (this.mMyConn == null) {
            this.mMyConn = new MyConn();
        }
        this.isStart = true;
    }

    public int onStartCommand(Intent intent, int flags, int startId) {
        init();
        return START_STICKY;
    }


    private void init() {

        if (this.mHandler == null) {
            this.mHandler = new Handler();
        }
        bindService(new Intent(this, ProtectService.class), this.mMyConn, Context.BIND_IMPORTANT);
        this.mHandler.removeCallbacksAndMessages(null);
        if (SettingPreUtil.getInstance().getProtectFlag()) {
            this.mHandler.post(this.mRunnable);
        }
    }

    public void onDestroy() {
        this.isStart = false;
        if (this.mHandler != null) {
            this.mHandler.removeCallbacksAndMessages(null);
            this.mHandler = null;
        }
        super.onDestroy();
    }

    public  boolean isBackground(Context context) {
        for (ActivityManager.RunningAppProcessInfo appProcess : ((ActivityManager) context.getSystemService(ACTIVITY_SERVICE)).getRunningAppProcesses()) {
            if (appProcess.processName.equals(context.getPackageName())) {
                if (appProcess.importance == 400 || appProcess.importance == 200 || appProcess.importance == 300) {
                    return true;
                }
                return false;
            }
        }
        return false;
    }

}
