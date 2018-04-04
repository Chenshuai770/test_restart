package com.qimiaowa.test_restart;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

/**
 * Created by Administrator on 2018/4/3/003.
 */

public class SecondActivity extends AppCompatActivity implements View.OnClickListener {
    private Button mTvTwo;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        initView();
    }

    private void initView() {
        mTvTwo = (Button) findViewById(R.id.tv_two);
        mTvTwo.setOnClickListener(this);
        this.mTvTwo.setText("关闭守护");

    }

    @Override
    public void onClick(View view) {

        if (SettingPreUtil.getInstance().getProtectFlag()) {
            SettingPreUtil.getInstance().saveProtectFlag(false);
            this.mTvTwo.setText("开启守护");
            return;
        }
        SettingPreUtil.getInstance().saveProtectFlag(true);
        this.mTvTwo.setText("关闭守护");
    }
}
