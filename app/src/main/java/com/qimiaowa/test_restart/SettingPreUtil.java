package com.qimiaowa.test_restart;

import android.content.SharedPreferences;

/**
 * Created by Administrator on 2018/4/4/004.
 */

public class SettingPreUtil {
    private static final String BIND_DEVICE = "bind_device";//是否绑定设备
    private static final String COMPANY_NAME = "company_name";//公司名字
    private static final String DEVICE_CODE = "device_code";//设备代码
    private static final String DEVICE_ID = "device_id";//设备编号
    private static final String DEV_CONTROL_TAG = "dev_control_tag";//设备控制标签
    private static final String DEV_MODE = "dev_mode";//开发模式
    private static final String EDI_PHONE = "eti_phone";//联系电话
    private static final String MSG_UPLOAD_FLAG = "msgUploadFlag";//消息上传标志
    private static final String MSG_VERSION = "msgVersion";//消息上传版本号
    private static final String PROTECT_SERVICE = "protect_service";//是否开启守护
    private static final String SETTING_PREFERENCES = "setting_preferences";//设备参数
    private static final String SHOP_ADDRESS = "shop_address";//店铺地址
    private static final String SHOP_ID = "shop_id";//店铺编号
    private static final String SHOP_NAME = "shop_name";//店铺名称
    private static final String TERM_CODE = "term_code";//条款编码
    private static final String TERM_TYPE = "term_type";//条款类型
    private static SettingPreUtil mInstance = new SettingPreUtil();
    private static SharedPreferences mPref;

    public SettingPreUtil() {
        mPref = MyApplication.getInstance().getSharedPreferences(SETTING_PREFERENCES, 0);
    }

    public static synchronized SettingPreUtil getInstance() {
        SettingPreUtil settingPreUtil;
        synchronized (SettingPreUtil.class) {
            if (mInstance == null) {
                mInstance = new SettingPreUtil();
            }
            settingPreUtil = mInstance;
        }
        return settingPreUtil;
    }

    public void saveProtectFlag(boolean flag) {
        mPref.edit().putBoolean(PROTECT_SERVICE, flag).commit();
    }

    public boolean getProtectFlag() {
        return mPref.getBoolean(PROTECT_SERVICE, true);
    }

    public void saveDeviceFlag(boolean flag) {
        mPref.edit().putBoolean(BIND_DEVICE, flag).commit();
    }

    public boolean getDeviceFlag() {
        return mPref.getBoolean(BIND_DEVICE, false);
    }

    public void saveCompany(String name) {
        mPref.edit().putString(COMPANY_NAME, name).commit();
    }

    public String getCompany() {
        return mPref.getString(COMPANY_NAME, "");
    }

    public void saveShopName(String name) {
        mPref.edit().putString(SHOP_NAME, name).commit();
    }

    public String getShopName() {
        return mPref.getString(SHOP_NAME, "");
    }

    public void saveShopAddress(String address) {
        mPref.edit().putString(SHOP_ADDRESS, address).commit();
    }

    public String getShopAddress() {
        return mPref.getString(SHOP_ADDRESS, "");
    }

    public void saveTermCode(String code) {
        mPref.edit().putString(TERM_CODE, code).commit();
    }

    public String getTermCode() {
        return mPref.getString(TERM_CODE, "");
    }

    public void saveTermType(String code) {
        mPref.edit().putString(TERM_TYPE, code).commit();
    }

    public String getTermType() {
        return mPref.getString(TERM_TYPE, "");
    }

    public void saveDeviceId(String id) {
        mPref.edit().putString("device_id", id).commit();
    }

    public String getDeviceId() {
        return mPref.getString("device_id", "");
    }

    public void saveDeviceCode(String code) {
        mPref.edit().putString(DEVICE_CODE, code).commit();
    }

    public String getDeviceCode() {
        return mPref.getString(DEVICE_CODE, "");
    }

    public void saveShopId(String id) {
        mPref.edit().putString(SHOP_ID, id).commit();
    }

    public String getShopId() {
        return mPref.getString(SHOP_ID, "");
    }

    public void savePhone(String phone) {
        mPref.edit().putString(EDI_PHONE, phone).commit();
    }

    public String getPhone() {
        return mPref.getString(EDI_PHONE, "");
    }

    public void saveVersion(String version) {
        mPref.edit().putString(MSG_VERSION, version).commit();
    }

    public String getVersion() {
        return mPref.getString(MSG_VERSION, "");
    }

    public void saveUploadFlag(boolean flag) {
        mPref.edit().putBoolean(MSG_UPLOAD_FLAG, flag).commit();
    }

    public boolean getUploadFlag() {
        return mPref.getBoolean(MSG_UPLOAD_FLAG, false);
    }

    public void saveControlIndex(int tag) {
        mPref.edit().putInt(DEV_CONTROL_TAG, tag).commit();
    }

    public int getControlIndex() {
        return mPref.getInt(DEV_CONTROL_TAG, 0);
    }

    public void saveDevMode(String mode) {
        mPref.edit().putString(DEV_MODE, mode).commit();
    }

    public String getDevMode() {
        return mPref.getString(DEV_MODE, "");
    }

    public void clearBindDevice() {
        SharedPreferences.Editor editor = mPref.edit();
        editor.putBoolean(BIND_DEVICE, false);
        editor.putString(COMPANY_NAME, "");
        editor.putString(SHOP_NAME, "");
        editor.putString(SHOP_ADDRESS, "");
        editor.putString(TERM_CODE, "");
        editor.putString(TERM_TYPE, "");
        editor.putString("device_id", "");
        editor.putString(DEVICE_CODE, "");
        editor.putString(SHOP_ID, "");
        editor.putString(EDI_PHONE, "");
        editor.putString(MSG_VERSION, "");
        editor.putBoolean(MSG_UPLOAD_FLAG, false);
        editor.commit();
    }
}

