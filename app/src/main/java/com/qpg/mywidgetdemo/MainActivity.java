package com.qpg.mywidgetdemo;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.qpg.mywidgetdemo.bean.User;
import com.qpg.widget.aop.trace.CacheTrace;
import com.qpg.widget.aop.trace.CheckLoginTrace;
import com.qpg.widget.aop.trace.PrefsTrace;
import com.qpg.widget.aop.trace.SafeTrace;
import com.qpg.widget.aop.trace.SingleClickTrace;
import com.qpg.widget.common.TitleBar;
import com.qpg.widget.scrollview.ObservableScrollView;
import com.qpg.widget.scrollview.ScrollViewListener;
import com.safframework.cache.Cache;
import com.safframework.prefs.AppPrefs;

//1、gradlew install    2、gradlew bintrayUpload
public class MainActivity extends Activity implements View.OnClickListener {
    Button mButton, mDialog, mLoading;
    private Button a, btnEdit, btnLog, btnLogin;
    TitleBar titleBar;
    private int mScrollHeight;
    ObservableScrollView sv;
    ImageView imageView, imageView1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sv = findViewById(R.id.scrollView);
        mButton = findViewById(R.id.btn);
        mButton.setOnClickListener(this);
        mDialog = findViewById(R.id.dialog);
        mDialog.setOnClickListener(this);
        btnEdit = findViewById(R.id.btn_edit);
        btnLogin = findViewById(R.id.btn_login);
        btnEdit.setOnClickListener(this);
        mLoading = findViewById(R.id.loading);
        mLoading.setOnClickListener(this);
        btnLogin.setOnClickListener(this);

        titleBar = findViewById(R.id.title11);
        //   titleBar.setImmersive(MainActivity.this,true,false);
        titleBar
                //     .setCustomTitle(getLayoutInflater().inflate(R.layout.test,null))
                .setTitle("123123")
                .setLeftText("134")
                .setLeftTextColor(R.color.button_blue_normal)
                .addAction(new TitleBar.ImageAction(R.mipmap.ic_launcher) {
                    @Override
                    public void performAction(View view) {

                    }
                }, 1);

//        SuperDialog.customDialog(this)
//                .setContentView( LayoutInflater.from(this).inflate(
//                        R.layout.activity_main, null)).setTitle("2342").builder().show();
        imageView = titleBar.getCenterView().findViewById(R.id.image);
        imageView1 = (ImageView) ((LinearLayout) titleBar.getRightView()).getChildAt(1);
        mScrollHeight = 200;
        sv.setScrollViewListener(new ScrollViewListener() {
            @Override
            public void onScrollChanged(ObservableScrollView scrollView, int x, int y, int oldx, int oldy) {
                setActionBar(Math.abs(y));
            }
        });
        saveUserByCache();
        getUserByCache();
        saveUserByPrefs();
        getUserByByPrefs();
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn:
                startActivity(new Intent(MainActivity.this, ButtonActivity.class));
                break;
            case R.id.btn_login:
                asd();
                break;
            case R.id.dialog:
                startActivity(new Intent(MainActivity.this, DialogActivity.class));
                break;
            case R.id.loading:
                startActivity(new Intent(MainActivity.this, LoadingActivity.class));
                break;

        }
    }

    @SingleClickTrace()
    private void setActionBar(int y) {

        //背景渐变
        if (y > (mScrollHeight)) {
            //  titleBar.setBackgroundColor(Color.argb(240, 255, 255, 255));
            //    rlTabbar.setBackgroundColor(Color.argb((int) (rate * 240.0), 255, 255, 255));
        } else if (y <= 0) {
            //     titleBar.setBackgroundColor(Color.argb(0, 255, 255, 255));
            //      rlTabbar.setBackgroundColor(Color.argb((int) (rate * 240.0), 255, 255, 255));
        } else {//渐变时候,y取值范围为0-136
            float rate = (float) y / (mScrollHeight);
            //  Logger.i("zhaofei", "onScrollChanged mScrollHeight=" + mScrollHeight+" rate="+rate+"rate * 255 = "+(int)(rate*255.0));
            titleBar.setBackgroundColor(Color.argb((int) (rate * 240.0), 255, 64, 129));
            // imageView.setAlpha((int) (rate * 255));
            // imageView1.setAlpha((int) (rate * 255));
            //     titleBar.setBackgroundColor(Color.argb((int) (rate * 240.0), 255, 64, 129));
            // titleBar.invalidate();
        }
    }

    @CheckLoginTrace(actionDefine = 1)
    private void asd() {

    }


  //  @CacheTrace(key = "user",expiry = 2000)//自定义缓存过期时间
    @CacheTrace(key = "user")
    private User saveUserByCache() {
        User userInfo = new User();
        userInfo.setUid("111");
        userInfo.setToken("sfsdgwefc");
        userInfo.setName("乔少");
        return userInfo;
    }

    void getUserByCache() {
        Cache cache = Cache.get(this);
        User user = (User) cache.getObject("user");
        System.out.println(user);
    }


    @PrefsTrace(key = "user")
    private User saveUserByPrefs() {
        User userInfo = new User();
        userInfo.setUid("111");
        userInfo.setToken("sfsdgwefc");
        userInfo.setName("乔少");
        return userInfo;
    }

    void getUserByByPrefs() {
        AppPrefs appPrefs = AppPrefs.get(this);
        User user = (User) appPrefs.getObject("user");
        System.out.println(user);
    }

    @SafeTrace
    private void noTry() {
        int a = 10 / 0;
    }
}
