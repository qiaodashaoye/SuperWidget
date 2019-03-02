package com.qpg.widget.dialog;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.PixelFormat;
import android.graphics.drawable.Drawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.qpg.widget.R;
import com.qpg.widget.utils.ScreenUtil;

/**
 * @author qpg
 * @date 2017/12/1 0001
 * @description:
 */
public class CustomDialog extends BaseMDialog<CustomDialog>{
    private final static int BUTTON_BOTTOM = 9;
    private final static int BUTTON_TOP = 9;
    private Builder mBuilder;
    private static Context mContext;
    private View mDialogView;
    private TextView mTitleView;
    private TextView mMessageView;
    private Window mAlertDialogWindow;
    private LinearLayout mButtonLayout;
    public CustomDialog(Builder builder){
        mBuilder = builder;
        mDialog = new Dialog(mContext);
        mDialogView = View.inflate(mContext, R.layout.layout_materialdialog, null);

        mTitleView = mDialogView.findViewById(R.id.title);
        mMessageView =  mDialogView.findViewById(R.id.message);
        mButtonLayout = mDialogView.findViewById(R.id.buttonLayout);
        mDialogView.setMinimumHeight((int) (ScreenUtil.getDisplayHeight() * builder.getHeight()));
        mDialog.setContentView(mDialogView);

        Window dialogWindow = mDialog.getWindow();
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        lp.width = (int) (ScreenUtil.getDisplayWidth() * builder.getWidth());
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        lp.gravity = Gravity.CENTER;
        dialogWindow.setAttributes(lp);
    }

    public static class Builder {

        private TextView mTitleView;
        private TextView mMessageView;
        private Window mAlertDialogWindow;
        private LinearLayout mButtonLayout;
        private float height;
        private float width;
        private boolean mCancel;
        private Builder mBuilder;
        private View mView;
        private int mTitleResId;
        private CharSequence mTitle;
        private int mMessageResId;
        private CharSequence mMessage;
        private Button mPositiveButton;
        private LinearLayout.LayoutParams mLayoutParams;
        private Button mNegativeButton;
        private boolean mHasShow = false;
        private Drawable mBackgroundDrawable;
        private int mBackgroundResId;
        private View mMessageContentView;
        private DialogInterface.OnDismissListener mOnDismissListener;

        @SuppressLint("NewApi")
        public Builder(Context context) {
            mContext=context;
            mDialog = new AlertDialog.Builder(mContext).create();

            height = 0.21f;
            width = 0.73f;

          //  mDialog.show();

            mDialog.getWindow().clearFlags(
                    WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE
                            | WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM);
            mDialog.getWindow().setSoftInputMode(
                    WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);

            mAlertDialogWindow = mDialog.getWindow();
            View contv = LayoutInflater.from(mContext).inflate(
                    R.layout.layout_materialdialog, null);
            contv.setFocusable(true);
            contv.setFocusableInTouchMode(true);

            mAlertDialogWindow.setContentView(contv);
            // mAlertDialogWindow.setContentView(R.layout.layout_materialdialog);

            // 7
            WindowManager.LayoutParams params = new WindowManager.LayoutParams(
                    WindowManager.LayoutParams.WRAP_CONTENT,
                    WindowManager.LayoutParams.WRAP_CONTENT,
                    WindowManager.LayoutParams.TYPE_PHONE,
                    WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM,
                    PixelFormat.TRANSLUCENT);

            mTitleView = mAlertDialogWindow.findViewById(R.id.title);
            mMessageView =  mAlertDialogWindow
                    .findViewById(R.id.message);
            mButtonLayout = mAlertDialogWindow
                    .findViewById(R.id.buttonLayout);
            if (mView != null) {
                LinearLayout linearLayout = mAlertDialogWindow
                        .findViewById(R.id.contentView);
                linearLayout.removeAllViews();
                linearLayout.addView(mView);
            }
            if (mTitleResId != 0) {
                setTitle(mTitleResId);
            }
            if (mTitle != null) {
                setTitle(mTitle);
            }
            if (mTitle == null && mTitleResId == 0) {
                mTitleView.setVisibility(View.GONE);
            }
            if (mMessageResId != 0) {
                setMessage(mMessageResId);
            }
            if (mMessage != null) {
                setMessage(mMessage);
            }
            if (mPositiveButton != null) {
                mButtonLayout.addView(mPositiveButton);
            }
            if (mLayoutParams != null && mNegativeButton != null) {
                if (mButtonLayout.getChildCount() > 0) {
                    mLayoutParams.setMargins(ScreenUtil.dip2px(12), 0, 0,
                            ScreenUtil.dip2px(BUTTON_BOTTOM));
                    mNegativeButton.setLayoutParams(mLayoutParams);
                    mButtonLayout.addView(mNegativeButton, 1);
                } else {
                    // Toast.makeText(mContext, "hdafd",
                    // Toast.LENGTH_SHORT).show();
                    mNegativeButton.setLayoutParams(mLayoutParams);
                    mButtonLayout.addView(mNegativeButton);
                }
            }
            if (mBackgroundResId != 0) {
                LinearLayout linearLayout = (LinearLayout) mAlertDialogWindow
                        .findViewById(R.id.material_background);
                linearLayout.setBackgroundResource(mBackgroundResId);
            }
            if (mBackgroundDrawable != null) {
                LinearLayout linearLayout = (LinearLayout) mAlertDialogWindow
                        .findViewById(R.id.material_background);
                linearLayout.setBackground(mBackgroundDrawable);
            }

            if (mMessageContentView != null) {
                this.setContentView(mMessageContentView);
            }
            mDialog.setCanceledOnTouchOutside(mCancel);
            if (mOnDismissListener != null) {
                mDialog.setOnDismissListener(mOnDismissListener);
            }
        }

        public float getHeight() {
            return height;
        }

        public Builder setHeight(float height) {
            this.height = height;
            return this;
        }

        public float getWidth() {
            return width;
        }

        public Builder setWidth(float width) {
            this.width = width;
            return this;
        }


        public Builder setTitle(int resId) {
            mTitleView.setText(resId);
            return this;
        }

        public Builder setTitle(CharSequence title) {
            mTitleView.setText(title);
            return this;
        }

        public void setMessage(int resId) {
            mMessageView.setText(resId);
        }

        public void setMessage(CharSequence message) {
            mMessageView.setText(message);
        }

        /**
         * set positive button
         *
         * @param text
         *            the name of button
         * @param listener
         */
        public void setPositiveButton(String text,
                                      final View.OnClickListener listener) {
            Button button = new Button(mContext);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT);
            button.setLayoutParams(params);
            button.setBackgroundResource(R.drawable.material_card);
            button.setTextColor(Color.argb(255, 35, 159, 242));
            button.setText(text);
            button.setGravity(Gravity.CENTER);
            button.setTextSize(14);
            button.setPadding(ScreenUtil.dip2px(12), 0, ScreenUtil.dip2px(32), ScreenUtil.dip2px(BUTTON_BOTTOM));
            button.setOnClickListener(listener);
            mButtonLayout.addView(button);
        }

        /**
         * set negative button
         *
         * @param text
         *            the name of button
         * @param listener
         */
        public void setNegativeButton(String text,
                                      final View.OnClickListener listener) {
            Button button = new Button(mContext);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT);
            button.setLayoutParams(params);
            button.setBackgroundResource(R.drawable.material_card);
            button.setText(text);
            button.setTextColor(Color.argb(222, 0, 0, 0));
            button.setTextSize(14);
            button.setGravity(Gravity.CENTER);
            button.setPadding(0, 0, 0, ScreenUtil.dip2px(8));
            button.setOnClickListener(listener);
            if (mButtonLayout.getChildCount() > 0) {
                params.setMargins(20, 0, 10, ScreenUtil.dip2px(BUTTON_BOTTOM));
                button.setLayoutParams(params);
                mButtonLayout.addView(button, 1);
            } else {
                button.setLayoutParams(params);
                mButtonLayout.addView(button);
            }
        }

        public void setView(View view) {
            LinearLayout l = (LinearLayout) mAlertDialogWindow
                    .findViewById(R.id.contentView);
            l.removeAllViews();
            ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT);
            view.setLayoutParams(layoutParams);

            view.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                @SuppressLint("NewApi")
                @Override
                public void onFocusChange(View v, boolean hasFocus) {
                    System.out.println("-->" + hasFocus);
                    mAlertDialogWindow
                            .setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
                    // show imm
                    InputMethodManager imm = (InputMethodManager) mContext
                            .getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.toggleSoftInput(InputMethodManager.SHOW_FORCED,
                            InputMethodManager.HIDE_IMPLICIT_ONLY);

                }
            });

            l.addView(view);

            if (view instanceof ViewGroup) {

                ViewGroup viewGroup = (ViewGroup) view;

                for (int i = 0; i < viewGroup.getChildCount(); i++) {
                    if (viewGroup.getChildAt(i) instanceof EditText) {
                        EditText editText = (EditText) viewGroup.getChildAt(i);
                        editText.setFocusable(true);
                        editText.requestFocus();
                        editText.setFocusableInTouchMode(true);
                    }
                }
            }
        }

        public Builder setContentView(View contentView) {
            ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT);
            contentView.setLayoutParams(layoutParams);
            LinearLayout linearLayout = (LinearLayout) mAlertDialogWindow
                    .findViewById(R.id.message_content_view);
            if (linearLayout != null) {
                linearLayout.removeAllViews();
                linearLayout.addView(contentView);
            }
            return this;
        }

        public void setBackground(Drawable drawable) {
            LinearLayout linearLayout = (LinearLayout) mAlertDialogWindow
                    .findViewById(R.id.material_background);
            linearLayout.setBackground(drawable);
        }

        public void setBackgroundResource(int resId) {
            LinearLayout linearLayout = (LinearLayout) mAlertDialogWindow
                    .findViewById(R.id.material_background);
            linearLayout.setBackgroundResource(resId);
        }

        public void setCanceledOnTouchOutside(boolean canceledOnTouchOutside) {
            mDialog.setCanceledOnTouchOutside(canceledOnTouchOutside);
        }

        public CustomDialog builder(){
            return new CustomDialog(this);
        }
    }
}
