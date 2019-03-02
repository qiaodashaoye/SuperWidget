package com.qpg.mywidgetdemo;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.qpg.widget.dialog.DialogOnClickListener;
import com.qpg.widget.dialog.MDEditDialog;
import com.qpg.widget.dialog.SuperDialog;
import com.qpg.widget.common.ToastUtil;

public class DialogActivity extends AppCompatActivity implements View.OnClickListener{

    Button mAlertDialog,mBottomDg,mEdtiDg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog);
        mAlertDialog=findViewById(R.id.dg_select);
        mAlertDialog.setOnClickListener(this);
        mBottomDg=findViewById(R.id.dg_bottom);
        mBottomDg.setOnClickListener(this);
        mEdtiDg=findViewById(R.id.dg_edit);
        mEdtiDg.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.dg_select:
                SuperDialog.alertDialog(this)
                .setWidth(0.7f)  //屏幕宽度*0.7
                .setTitleVisible(true)
                .setTitleText("温馨提示")
                .setTitleTextColor(R.color.black_light)
                .setContentText("确定删除该条说说吗？")
                .setContentTextColor(R.color.black_light)
                .setLeftButtonText("不删除")
                .setLeftButtonTextColor(R.color.black_light)
                .setRightButtonText("删除")
                .setRightButtonTextColor(R.color.colorAccent)
                .setTitleTextSize(16)
                .setContentTextSize(14)
                .setButtonTextSize(14)
                .setOnclickListener(new DialogOnClickListener() {
                    @Override
                    public void clickLeftButton(View view) {
                        ToastUtil.showCustomToast("点击了左侧按钮");
                    }

                    @Override
                    public void clickRightButton(View view) {
                        ToastUtil.showCustomToast("点击了右侧按钮");
                    }
                }).build().show();
                break;
            case R.id.dg_bottom:

                break;
                case R.id.dg_edit:
                SuperDialog.editDialog(this).setOnclickListener(new MDEditDialog.OnClickEditDialogListener() {
                    @Override
                    public void clickLeftButton(View view, String editText) {

                    }

                    @Override
                    public void clickRightButton(View view, String editText) {

                    }
                }).build().show();
                break;
        }
    }
}
