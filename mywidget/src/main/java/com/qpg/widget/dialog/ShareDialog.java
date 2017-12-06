package com.qpg.widget.dialog;

import android.app.Activity;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import com.qpg.widget.R;
@SuppressWarnings("all")
public class ShareDialog extends BottomDialog implements
        DialogInterface.OnCancelListener, DialogInterface.OnDismissListener {
    private Activity mActivity;
    private boolean isOnlyBitmap;

    public ShareDialog(@NonNull Activity activity) {
        super(activity, true);
        this.mActivity = activity;
        LayoutInflater inflater = LayoutInflater.from(getContext());
        View contentView = inflater.inflate(R.layout.dialog_md, null, false);
     //   RecyclerView shareRecycle = (RecyclerView) contentView.findViewById(R.id.share_recycler);
        setContentView(contentView);
        setOnCancelListener(this);
        setOnDismissListener(this);
    }

    @Override
    public void onCancel(DialogInterface dialog) {

    }

    @Override
    public void onDismiss(DialogInterface dialog) {

    }

//    public ShareDialog with() {
//        mShare.setAppShareIcon(R.mipmap.ic_share_app_logo);
//        if (mShare.getBitmapResID() == 0)
//            mShare.setBitmapResID(R.mipmap.ic_share_app_logo);
//        return this;
//    }
//
//    public ShareDialog title(String title) {
//        mShare.setTitle(title);
//        if (mAboutShare == null)
//            mAboutShare = new About.Share();
//        mAboutShare.title = title;
//        return this;
//    }
//
//    public ShareDialog content(String content) {
//        mShare.setContent(content);
//        summary(content);
//        description(content);
//        mAboutShare.content = content;
//        return this;
//    }
//
//    public ShareDialog type(int type) {
//        mAboutShare.type = type;
//        return this;
//    }

    public void cancelLoading() {
        if (this != null && this.isShowing()) {
            this.cancel();
            this.dismiss();
            //mAlertDialog.dismiss();
        }
    }


}
