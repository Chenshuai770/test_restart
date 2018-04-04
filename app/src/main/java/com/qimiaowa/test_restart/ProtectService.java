package com.qimiaowa.test_restart;

import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;



public class ProtectService extends Service {
    private MyBinder mMyBinder;
    private MyConn mMyConn;

    class MyConn implements ServiceConnection {
        MyConn() {
        }

        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        }


        public void onServiceDisconnected(ComponentName componentName) {
            ProtectService.this.startService(new Intent(ProtectService.this, MainService.class));
            ProtectService.this.bindService(new Intent(ProtectService.this, MainService.class), ProtectService.this.mMyConn, BIND_IMPORTANT);
        }
    }

    public class MyBinder extends ICat.Stub {
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
    }


    public int onStartCommand(Intent intent, int flags, int startId) {
        bindService(new Intent(this, MainService.class), this.mMyConn, BIND_IMPORTANT);
        return START_STICKY;
    }
}
