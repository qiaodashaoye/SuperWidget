package com.qpg.widget.dialog;

import android.app.Dialog;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

/**
 * @author qpg
 * @date 2017/12/1 0001
 * @description:
 */
public class BaseMDialog<D> {
    protected static Dialog mDialog;
    protected String mTitle;
    protected Button mPositiveButton;
    protected LinearLayout.LayoutParams mLayoutParams;
    protected Button mNegativeButton;
    protected View mContentView;

  //  protected static Context mContext;
    public BaseMDialog(){
   //     mContext= WidgetInit.getContext();
    }


    public D show(){
        if(mDialog!=null){
            mDialog.show();
        }
        return (D)this;
    }
    public D dismiss(){
        if(mDialog!=null){
            mDialog.dismiss();
        }

        return (D)this;
    }



}
