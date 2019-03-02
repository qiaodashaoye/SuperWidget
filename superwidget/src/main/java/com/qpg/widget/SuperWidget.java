package com.qpg.widget;

import android.app.Application;
import android.content.Context;
import com.qpg.widget.aop.intf.ILogin;

/**
 * @author qpg
 * @date 2017/11/30 0030
 * @description:
 */
public class SuperWidget {
    private  Context mContext;
    private  ILogin mILogin;
    /* 持有私有静态实例，防止被引用，此处赋值为null，目的是实现延迟加载 */
    private static SuperWidget instance = null;

    public SuperWidget init(Application context) {
        init(context,null);
        return this;
    }
    /**
     * 初始化
     *
     * @param context Context
     * @param iLogin  登录事件
     */
    public SuperWidget init(Context context, ILogin iLogin) {
        if(mContext==null){
            mContext = context;
        }
        mILogin = iLogin;
        return this;
    }
    /*3.双重锁定:只在第一次初始化的时候加上同步锁*/
    public static SuperWidget getInstance() {
        if (instance == null) {
            synchronized (SuperWidget.class) {
                if (instance == null) {
                    instance = new SuperWidget();
                }
            }
        }
        return instance;
    }

    public Context getContext() {
        if (mContext == null) {
            throw new ExceptionInInitializerError("Please call WidgetInit.init(this) in Application to initialize!");
        }
        return mContext;
    }
    public ILogin getLogin() {
        return mILogin;
    }
}
