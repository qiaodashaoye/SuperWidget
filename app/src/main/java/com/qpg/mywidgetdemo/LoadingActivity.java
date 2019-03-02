package com.qpg.mywidgetdemo;

import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import androidx.annotation.RequiresApi;
import android.view.View;
import android.widget.Button;

import com.qpg.widget.loading.MProgressDialog;
import com.qpg.widget.loading.MStatusDialog;

import java.util.Timer;
import java.util.TimerTask;

@RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
public class LoadingActivity extends Activity implements View.OnClickListener{
    MProgressDialog mMProgressDialog;
    MStatusDialog mStatusDialog;
    Button mLoadingText,mLoadingNoText,mLoadingProgess,mTips;
    private float currentProgress = 0.0f;
    private Timer timer;
    private TimerTask task;
    private Handler mHandler = new Handler();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading);
        mLoadingText=findViewById(R.id.loading_htext);
        mLoadingText.setOnClickListener(this);
        mLoadingNoText=findViewById(R.id.loading_notext);
        mLoadingNoText.setOnClickListener(this);
        mLoadingProgess=findViewById(R.id.loading_progress);
        mLoadingProgess.setOnClickListener(this);
        mTips=findViewById(R.id.loading_tips);
        mTips.setOnClickListener(this);
        //延时关闭
       // delayDismissProgressDialog();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.loading_htext:
                mMProgressDialog = new MProgressDialog(this);
                mMProgressDialog.show();
                break;
            case R.id.loading_notext:
                mMProgressDialog = new MProgressDialog.Builder(this)
                        .isCanceledOnTouchOutside(true)
                        .setOnDialogDismissListener(new MProgressDialog.OnDialogDismissListener() {
                            @Override
                            public void dismiss() {
                             //   mHandler.removeCallbacksAndMessages(null);
                            }
                        })
                        .build();
                mMProgressDialog.showNoText();
          //      mMProgressDialog.show("加载中...");
                break;

            case R.id.loading_progress:

                mMProgressDialog = new MProgressDialog.Builder(this)
                        .isCanceledOnTouchOutside(true)
                        .setProgressRimColor(getResources().getColor(R.color.white))
                        .setProgressRimWidth(1)
                        .setOnDialogDismissListener(new MProgressDialog.OnDialogDismissListener() {
                            @Override
                            public void dismiss() {
                                mHandler.removeCallbacksAndMessages(null);
                            }
                        })
                        .build();
                mMProgressDialog.showWithProgress();
                initTimer();
                break;
            case R.id.loading_tips:
                mStatusDialog = new MStatusDialog(this);
                mStatusDialog.show("保存成功",getResources().getDrawable(R.mipmap.ic_launcher));
                break;
        }
    }


    private void initTimer() {
        destroyTimer();
        timer = new Timer();
        task = new TimerTask() {
            @Override
            public void run() {
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        if (currentProgress < 1.0f) {
                            int pro = (int) (currentProgress * 100);
                            mMProgressDialog.setDialogProgress(currentProgress, "视频下载进度: " + pro + "%");

                            currentProgress += 0.1;
                        } else {
                            destroyTimer();
                            currentProgress = 0.0f;
                            mMProgressDialog.setDialogProgress(1.0f, "完成");
                            //关闭
                            mHandler.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    mMProgressDialog.dismiss();

                                }
                            }, 500);
                        }
                    }
                });
            }
        };
        timer.schedule(task, 0, 1000); //延时1000ms后执行，1000ms执行一次
    }

    private void destroyTimer() {
        if (timer != null && task != null) {
            timer.cancel();
            task.cancel();
            timer = null;
            task = null;
        }
    }
}
