package com.qpg.widget;

import android.app.Application;
import android.content.Context;

/**
 * @author qpg
 * @date 2017/11/30 0030
 * @description:
 */
public class WidgetInit {

    private static Context mContext;
    public static void init(Application app) {
        if(mContext==null){
            mContext = app;
        }
    }

    public static Context getContext() {
        if (mContext == null) {
            throw new ExceptionInInitializerError("Please call WidgetInit.init(this) in Application to initialize!");
        }
        return mContext;
    }
}
