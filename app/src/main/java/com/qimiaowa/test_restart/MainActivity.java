package com.qimiaowa.test_restart;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button mButton;
    private Button mButton2;
    private List<String> mList;
    private LinearLayout mLlLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();

        startService(new Intent(this, MainService.class));
        startService(new Intent(this, ProtectService.class));
        //AppManager.getInstance().finishAllActivity();

    }

    @Override
    protected void onStart() {
        super.onStart();

        startService(new Intent(this, MainService.class));
        startService(new Intent(this, ProtectService.class));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    private void initView() {
        SettingPreUtil.getInstance().saveProtectFlag(true);
        mButton = (Button) findViewById(R.id.btn_main1);
        mButton.setOnClickListener(this);
       /* mButton2 = (Button) findViewById(R.id.btn_main2);
        mButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences login = getSharedPreferences("login", MODE_PRIVATE);
                SharedPreferences.Editor edit = login.edit();
                edit.putBoolean("isOpen",false);
                edit.commit();

            }
        });
*/
       /* if (SettingPreUtil.getInstance().getProtectFlag()) {
            this.mButton.setText("关闭守护");
        } else {
            this.mButton.setText("开启守护");
        }*/


    }

    @Override
    public void onClick(View view) {


        switch (view.getId()) {
            case R.id.btn_main1:

                Intent intent = new Intent(this, SecondActivity.class);
                startActivity(intent);
                break;
        }
    }
}
