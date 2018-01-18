package com.qpg.mywidgetdemo;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.qpg.widget.WidgetInit;
import com.qpg.widget.common.TitleBar;
import com.qpg.widget.scrollview.ObservableScrollView;
import com.qpg.widget.scrollview.ScrollViewListener;
//1、gradlew install    2、gradlew bintrayUpload
public class MainActivity extends Activity implements View.OnClickListener{
    Button mButton,mDialog,mLoading;
    private Button a,btnEdit;
    TitleBar titleBar;
    private int mScrollHeight;
    ObservableScrollView sv;
    ImageView imageView,imageView1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        WidgetInit.init(getApplication());
        sv=findViewById(R.id.scrollView);
        mButton=findViewById(R.id.btn);
        mButton.setOnClickListener(this);
        mDialog=findViewById(R.id.dialog);
        mDialog.setOnClickListener(this);
        btnEdit=findViewById(R.id.btn_edit);
        btnEdit.setOnClickListener(this);
        mLoading=findViewById(R.id.loading);
        mLoading.setOnClickListener(this);

        titleBar=findViewById(R.id.title11);
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
                },1);

//        SuperDialog.customDialog(this)
//                .setContentView( LayoutInflater.from(this).inflate(
//                        R.layout.activity_main, null)).setTitle("2342").builder().show();
        imageView=titleBar.getCenterView().findViewById(R.id.image);
        imageView1=(ImageView) ((LinearLayout)titleBar.getRightView()).getChildAt(1);
        mScrollHeight=200;
        sv.setScrollViewListener(new ScrollViewListener() {
            @Override
            public void onScrollChanged(ObservableScrollView scrollView, int x, int y, int oldx, int oldy) {
                setActionBar(Math.abs(y));
            }
        });

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn:
                startActivity(new Intent(MainActivity.this,ButtonActivity.class));

                    break;
            case R.id.dialog:
                startActivity(new Intent(MainActivity.this,DialogActivity.class));
                break;
            case R.id.loading:
                startActivity(new Intent(MainActivity.this,LoadingActivity.class));
                break;
        }
    }

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
            imageView.setAlpha((int) (rate * 255));
            imageView1.setAlpha((int) (rate * 255));
       //     titleBar.setBackgroundColor(Color.argb((int) (rate * 240.0), 255, 64, 129));
           // titleBar.invalidate();
        }
    }
}
