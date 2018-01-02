package com.qpg.mywidgetdemo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

import com.qpg.widget.WidgetInit;
import com.qpg.widget.common.TitleBar;
import com.qpg.widget.dialog.DialogOnClickListener;
import com.qpg.widget.dialog.MDEditDialog;
import com.qpg.widget.dialog.MDSelectionDialog;
import com.qpg.widget.dialog.ShareDialog;
import com.qpg.widget.dialog.SuperDialog;

import java.util.ArrayList;

public class MainActivity extends Activity implements View.OnClickListener{
    Button mButton,mDialog,mLoading;
    private Button a,btnEdit;
    TitleBar titleBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        WidgetInit.init(getApplication());
        mButton=findViewById(R.id.btn);
        mButton.setOnClickListener(this);
        mDialog=findViewById(R.id.dialog);
        mDialog.setOnClickListener(this);
        btnEdit=findViewById(R.id.btn_edit);
        btnEdit.setOnClickListener(this);
        mLoading=findViewById(R.id.loading);
        mLoading.setOnClickListener(this);

        titleBar=findViewById(R.id.title11);
        titleBar.setImmersive(MainActivity.this,true,false);
//        SuperDialog.customDialog(this)
//                .setContentView( LayoutInflater.from(this).inflate(
//                        R.layout.activity_main, null)).setTitle("2342").builder().show();


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
}
