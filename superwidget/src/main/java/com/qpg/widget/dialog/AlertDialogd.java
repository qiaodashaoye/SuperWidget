package com.qpg.widget.dialog;

import android.app.Dialog;

import com.qpg.widget.R;

/**
 * @author qpg
 * @date 2017/12/1 0001
 * @description:
 */
public class AlertDialogd extends BaseMDialog<AlertDialogd>{

    public AlertDialogd(BuilderAlertDialog builderAlertDialog){
     //   mDialog = new Dialog(mContext, R.style.MyDialogStyle);
    }

    public static class BuilderAlertDialog{

        public BuilderAlertDialog(){

        }

        public AlertDialogd builder(){
            return new AlertDialogd(this);
        }

    }

}
