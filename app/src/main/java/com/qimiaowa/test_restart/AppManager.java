package com.qimiaowa.test_restart;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;

import java.util.Iterator;
import java.util.Stack;

import static android.content.Context.ACTIVITY_SERVICE;

/**
 * Created by Administrator on 2018/4/3/003.
 */

public class AppManager {
    private static AppManager mInstance;
    private Stack<Activity> activityStack;

    private AppManager() {
    }

    public static AppManager getInstance() {
        if (mInstance == null) {
            synchronized (AppManager.class) {
                if (mInstance == null) {
                    mInstance = new AppManager();
                }
            }
        }
        return mInstance;
    }

    public void addActivity(Activity activity) {
        if (this.activityStack == null) {
            this.activityStack = new Stack();
        }
        this.activityStack.add(activity);
    }

    public Activity currentActivity() {
        return (Activity) this.activityStack.lastElement();
    }

    public void finishActivity() {
        finishActivity((Activity) this.activityStack.lastElement());
    }

    public void finishActivity(Activity activity) {
        if (activity != null) {
            this.activityStack.remove(activity);
            activity.finish();
        }
    }

    public void finishActivity(Class<?> cls) {
        Iterator it = this.activityStack.iterator();
        while (it.hasNext()) {
            Activity activity = (Activity) it.next();
            if (activity.getClass().equals(cls)) {
                finishActivity(activity);
            }
        }
    }

    public void finishAllActivity() {
        if (this.activityStack != null) {
            int size = this.activityStack.size();
            for (int i = 0; i < size; i++) {
                if (this.activityStack.get(i) != null) {
                    ((Activity) this.activityStack.get(i)).finish();
                }
            }
            this.activityStack.clear();
        }
    }

    public void AppExit(Context context) {
        try {
            finishAllActivity();
            ((ActivityManager) context.getSystemService(ACTIVITY_SERVICE)).killBackgroundProcesses(context.getPackageName());
        } catch (Exception e) {
        }
    }

    public boolean isAppExit() {
        return this.activityStack == null || this.activityStack.isEmpty();
    }
}
