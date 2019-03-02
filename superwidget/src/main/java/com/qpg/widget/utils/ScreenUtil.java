package com.qpg.widget.utils;

import android.content.Context;
import android.content.res.Resources;
import android.util.DisplayMetrics;
import com.qpg.widget.SuperWidget;
import java.lang.reflect.Field;


/**
 * 手机屏幕的信息
 */
public class ScreenUtil {
    private static final String TAG = "ScreenUtil";

    public static double DIALOG_RATIO = 0.85;

    private static int screenWidth;  //屏幕宽度
    private static int screenHeight;
    private static int screenMin;// 宽与高中较小的值
    private static int screenMax;// 宽与高中较大的值

    private static float density;
    private static float scaleDensity;
    private static float xdpi;
    private static float ydpi;
    private static int densityDpi;

    private static int dialogWidth;

    private static DisplayMetrics dm;
    //状态栏高度
    private static int statusbarheight;
    //导航栏高度
    public static int navbarheight;

    public static void GetInfo(Context context) {
        if (null == context) {
            return;
        }
        dm = context.getApplicationContext().getResources().getDisplayMetrics();
        screenWidth = dm.widthPixels;
        screenHeight = dm.heightPixels;
        screenMin = (screenWidth > screenHeight) ? screenHeight : screenWidth;
        screenMax = (screenWidth < screenHeight) ? screenHeight : screenWidth;
        density = dm.density;
        scaleDensity = dm.scaledDensity;
        xdpi = dm.xdpi;
        ydpi = dm.ydpi;
        densityDpi = dm.densityDpi;
//		statusbarheight = getStatusBarHeight(context);
//		navbarheight = getNavBarHeight(context);
//        Logger.i(TAG, "screenWidth=" + screenWidth + " screenHeight=" + screenHeight + " density=" + density + " densityDpi=" + densityDpi);
    }

    public static int getStatusBarHeight(Context context) {
        Class<?> c = null;
        Object obj = null;
        Field field = null;
        int x = 0, sbar = 0;
        try {
            c = Class.forName("com.android.internal.R$dimen");
            obj = c.newInstance();
            field = c.getField("status_bar_height");
            x = Integer.parseInt(field.get(obj).toString());
            sbar = context.getResources().getDimensionPixelSize(x);
        } catch (Exception E) {
            E.printStackTrace();
        }
        return sbar;
    }

    public static int getNavBarHeight(Context context) {
        Resources resources = context.getResources();
        int resourceId = resources.getIdentifier("navigation_bar_height", "dimen", "android");
        if (resourceId > 0) {
            return resources.getDimensionPixelSize(resourceId);
        }
        return 0;
    }

    public static DisplayMetrics getDisplayMetrics() {
        if (dm == null) {
            GetInfo(SuperWidget.getInstance().getContext());
        }
        return dm;
    }

    /**
     * dp 的单位 转成为 px(像素)
     */
    public static int dip2px(float dipValue) {
        final float scale = getDisplayDensity();
        return (int) (dipValue * scale + 0.5f);
    }

    public static int px2dip(float pxValue) {
        final float scale = getDisplayDensity();
        return (int) (pxValue / scale + 0.5f);
    }

    private static float getDisplayDensity() {
        if (density == 0) {
            GetInfo(SuperWidget.getInstance().getContext());
        }
        return density;
    }

    public static int getDisplayWidth() {
        if (screenWidth == 0) {
            GetInfo(SuperWidget.getInstance().getContext());
        }
        return screenWidth;
    }

    public static int getDisplayHeight() {
        if (screenHeight == 0) {
            GetInfo(SuperWidget.getInstance().getContext());
        }
        return screenHeight;
    }

    public static int getScreenMin() {
        if (screenMin == 0) {
            GetInfo(SuperWidget.getInstance().getContext());
        }
        return screenMin;
    }

    public static int getScreenMax() {
        if (screenMin == 0) {
            GetInfo(SuperWidget.getInstance().getContext());
        }
        return screenMax;
    }

    public static float getScaleDensity() {
        if (screenMin == 0) {
            GetInfo(SuperWidget.getInstance().getContext());
        }
        return scaleDensity;

    }

    public static int getDialogWidth() {
        dialogWidth = (int) (getScreenMin() * DIALOG_RATIO);
        return dialogWidth;
    }
}
