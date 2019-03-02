package com.qpg.widget.dialog;

import android.app.Activity;
import android.content.Context;

/**
 * @author qpg
 * @date 2017/12/1 0001
 * @description:
 */
public class SuperDialog {

    private static class SuperDialogHolder{
        private static final SuperDialog INSTANCE=new SuperDialog();
    }

    public static final SuperDialog getInstance() {
        return SuperDialogHolder.INSTANCE;
    }

    public static MDAlertDialog.Builder alertDialog(Context context){
        return new MDAlertDialog.Builder(context);
    }

    public static MDEditDialog.Builder editDialog(Context context){
        return new MDEditDialog.Builder(context);
    }

    public static CustomDialog.Builder customDialog(Context context){

        return new CustomDialog.Builder(context);
    }

//    public static ShareDialog  bottomDialog(Activity activity){
//        return new ShareDialog(activity);
//    }

}
