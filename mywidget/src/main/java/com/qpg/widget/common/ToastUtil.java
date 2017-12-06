package com.qpg.widget.common;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.qpg.widget.R;
import com.qpg.widget.WidgetInit;
import com.qpg.widget.utils.ScreenUtil;


public class ToastUtil {

    private static Toast toast = null;

    private static Toast customToast = null;

    private static Toast imageToast = null;

    public static Toast makeToast(Context mContext, int resId) {
        return Toast.makeText(mContext, resId, Toast.LENGTH_SHORT);
    }

    public static Toast makeToast(Context mContext, String text) {
        return Toast.makeText(mContext, text, Toast.LENGTH_SHORT);
    }

    public static void showToast(Context mContext, int resId) {
        final Context context = (WidgetInit.getContext()!= null ? WidgetInit.getContext() : mContext);

        if (toast != null) {
            toast.setText(resId);
            toast.setDuration(Toast.LENGTH_SHORT);
        } else {
            toast = Toast.makeText(context.getApplicationContext(), resId, Toast.LENGTH_SHORT);
        }

        toast.show();

    }

    public static void showToast(Context mContext, String msg) {
        final Context context = (WidgetInit.getContext()!= null ? WidgetInit.getContext() : mContext);

        if (toast != null) {
            toast.setText(msg);
            toast.setDuration(Toast.LENGTH_SHORT);
        } else {
            toast = Toast.makeText(context.getApplicationContext(), msg, Toast.LENGTH_SHORT);
        }
        toast.show();

    }

    public static void showLongToast(Context mContext, int resId) {
        final Context context = (WidgetInit.getContext() != null ? WidgetInit.getContext() : mContext);


        if (toast != null) {
            toast.setText(resId);
            toast.setDuration(Toast.LENGTH_LONG);
        } else {
            toast = Toast.makeText(context.getApplicationContext(), resId, Toast.LENGTH_LONG);
        }

        toast.show();

    }

    public static void showLongToast(Context mContext, String msg) {
        final Context context = (WidgetInit.getContext() != null ? WidgetInit.getContext() : mContext);

        if (toast != null) {
            toast.setText(msg);
            toast.setDuration(Toast.LENGTH_SHORT);
        } else {
            toast = Toast.makeText(context.getApplicationContext(), msg, Toast.LENGTH_SHORT);
        }
        toast.show();
    }

    public static void showCustomToastCenter(Context mContext, String message) {
        final Context context = (WidgetInit.getContext() != null ? WidgetInit.getContext(): mContext);

        Toast customToast = Toast.makeText(context.getApplicationContext(), "",
                Toast.LENGTH_SHORT);
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.layout_toast_custom,
                null);
        TextView textView = (TextView) view
                .findViewById(R.id.custom_toast_text);
        textView.setText(message);
        customToast.setView(view);
        customToast.setGravity(Gravity.CENTER, 0, 0);

        customToast.show();
    }

    public static void showCustomToast(String msg) {
        showCustomToast(WidgetInit.getContext(), msg);
    }

    public static void showCustomToast(Context mContext, String msg) {

        if (customToast == null) {
            final Context context;
            if (WidgetInit.getContext() != null) {
                context = WidgetInit.getContext();
            } else {
                if (mContext != null) {
                    context = mContext;
                } else {
                    return;
                }
            }

            customToast = Toast.makeText(context, "",
                    Toast.LENGTH_SHORT);
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View view = inflater.inflate(R.layout.layout_toast_custom,
                    null);
            TextView textView = (TextView) view
                    .findViewById(R.id.custom_toast_text);
            textView.setText(msg);
            customToast.setView(view);
            customToast.setGravity(Gravity.BOTTOM, 0, ScreenUtil.dip2px(70));
        } else {
            View view = customToast.getView();
            TextView textView = (TextView) view
                    .findViewById(R.id.custom_toast_text);
            textView.setText(msg);
        }
        customToast.show();
    }
}
