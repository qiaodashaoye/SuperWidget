package com.qpg.mywidgetdemo;

import android.app.Application;
import android.content.Context;
import android.content.Intent;

import com.qpg.widget.SuperWidget;
import com.qpg.widget.aop.intf.ILogin;
import com.qpg.widget.common.ToastUtil;

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        SuperWidget.getInstance().init(this);
    }

}
