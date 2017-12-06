package com.qpg.widget.loading;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.qpg.widget.R;

/**
 * Created by Administrator on 2017/10/31 0031.
 */

public class LoadingDialog extends Dialog{
    LoadingView mLoadingView;
    Context context;

    public LoadingDialog(Context context) {
        this(context, R.style.loading_dialog);

        this.context = context;

    }
    public LoadingDialog(Context context, int themeResId) {
        super(context, themeResId);
        setCanceledOnTouchOutside(false);
        setCancelable(false);

//        Window window = getWindow();
//        WindowManager.LayoutParams layoutParams = window.getAttributes();
//        window.setGravity(Gravity.BOTTOM);
//        //dialog 默认的样式@android:style/Theme.Dialog 对应的style 有pading属性
//        window.getDecorView().setPadding(0, 0, 0, 0);
//        layoutParams.height = ScreenUtil.getDisplayHeight();
//        layoutParams.width = ScreenUtil.getDisplayWidth();
//        window.setAttributes(layoutParams);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loading_dialog_view);


        // 获取整个布局
        LinearLayout layout = findViewById(R.id.dialog_view);
        mLoadingView=findViewById(R.id.lv_circularring);
        // 页面中的LoadingView
        mLoadingView = findViewById(R.id.lv_circularring);
        // 页面中显示文本
        TextView loadingText =findViewById(R.id.loading_text);
        // 显示文本
        loadingText.setText("234234");
    }

    @Override
    public void show() {
        super.show();
        if(this!=null){
            mLoadingView.startAnim();
        }

    }

    @Override
    public void dismiss() {
        super.dismiss();
        if(this!=null){
            mLoadingView.stopAnim();
        }

    }
}
