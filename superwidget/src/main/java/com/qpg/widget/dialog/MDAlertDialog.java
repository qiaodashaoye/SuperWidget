package com.qpg.widget.dialog;

import android.app.Dialog;
import android.content.Context;
import androidx.annotation.ColorRes;
import androidx.core.content.ContextCompat;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.qpg.widget.R;
import com.qpg.widget.utils.ScreenUtil;

public class MDAlertDialog extends BaseMDialog<MDAlertDialog> implements View.OnClickListener {

    private View mDialogView;
    private TextView mTitle;
    private TextView mContent;
    private TextView mLeftBtn;
    private TextView mRightBtn;
    private static Context mContext;
    private Builder mBuilder;

    public MDAlertDialog(Builder builder) {

        mBuilder = builder;
        mDialog = new Dialog(mContext, R.style.MyDialogStyle);
        mDialogView = View.inflate(mContext, R.layout.widget_md_dialog, null);
        mTitle = mDialogView.findViewById(R.id.md_dialog_title);
        mContent = mDialogView.findViewById(R.id.md_dialog_content);
        mLeftBtn = mDialogView.findViewById(R.id.md_dialog_leftbtn);
        mRightBtn = mDialogView.findViewById(R.id.md_dialog_rightbtn);
        mDialogView.setMinimumHeight((int) (ScreenUtil.getDisplayHeight()
                 * builder.getHeight()));
        mDialog.setContentView(mDialogView);

        Window dialogWindow = mDialog.getWindow();
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        lp.width = (int) (ScreenUtil.getDisplayWidth() * builder.getWidth());
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        lp.gravity = Gravity.CENTER;
        dialogWindow.setAttributes(lp);
        initDialog();

    }

    private void initDialog() {

        mDialog.setCanceledOnTouchOutside(mBuilder.isTouchOutside());

        if (mBuilder.getTitleVisible()) {

            mTitle.setVisibility(View.VISIBLE);
        } else {

            mTitle.setVisibility(View.GONE);
        }

        mTitle.setText(mBuilder.getTitleText());
        mTitle.setTextColor(mBuilder.getTitleTextColor());
        mTitle.setTextSize(mBuilder.getTitleTextSize());
        mContent.setText(mBuilder.getContentText());
        mContent.setTextColor(mBuilder.getContentTextColor());
        mContent.setTextSize(mBuilder.getContentTextSize());
        mLeftBtn.setText(mBuilder.getLeftButtonText());
        mLeftBtn.setTextColor(mBuilder.getLeftButtonTextColor());
        mLeftBtn.setTextSize(mBuilder.getButtonTextSize());
        mRightBtn.setText(mBuilder.getRightButtonText());
        mRightBtn.setTextColor(mBuilder.getRightButtonTextColor());
        mRightBtn.setTextSize(mBuilder.getButtonTextSize());

        mLeftBtn.setOnClickListener(this);
        mRightBtn.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {

        int i = view.getId();
        if (i == R.id.md_dialog_leftbtn && mBuilder.getListener() != null) {
            this.dismiss();
            mBuilder.getListener().clickLeftButton(mLeftBtn);
            return;
        }

        if (i == R.id.md_dialog_rightbtn && mBuilder.getListener() != null) {
            this.dismiss();
            mBuilder.getListener().clickRightButton(mRightBtn);

            return;
        }

    }

    public static class Builder {

        private String titleText;
        private int titleTextColor;
        private int titleTextSize;
        private String contentText;
        private int contentTextColor;
        private int contentTextSize;
        private String leftButtonText;
        private int leftButtonTextColor;
        private String rightButtonText;
        private int rightButtonTextColor;
        private int buttonTextSize;
        private boolean isTitleVisible;
        private boolean isLeftBtnVisible;
        private boolean isTouchOutside;
        private float height;
        private float width;
        private DialogOnClickListener listener;

        public Builder(Context context) {

            mContext = context;
            titleText = "提示";
            titleTextColor = ContextCompat.getColor(mContext, R.color.black_light);
            contentText = "";
            contentTextColor = ContextCompat.getColor(mContext, R.color.black_light);
            leftButtonText = "取消";
            leftButtonTextColor = ContextCompat.getColor(mContext, R.color.black_light);
            rightButtonText = "确定";
            rightButtonTextColor = ContextCompat.getColor(mContext, R.color.black_light);
            listener = null;
            isTitleVisible = true;
            isLeftBtnVisible = true;
            isTouchOutside = true;
            height = 0.21f;
            width = 0.73f;
            titleTextSize = 16;
            contentTextSize = 14;
            buttonTextSize = 14;
        }

        public String getTitleText() {
            return titleText;
        }

        public Builder setTitleText(String titleText) {
            this.titleText = titleText;
            return this;
        }

        public int getTitleTextColor() {
            return titleTextColor;
        }

        public Builder setTitleTextColor(@ColorRes int titleTextColor) {
            this.titleTextColor = ContextCompat.getColor(mContext, titleTextColor);
            return this;
        }

        public String getContentText() {
            return contentText;
        }

        public Builder setContentText(String contentText) {
            this.contentText = contentText;
            return this;
        }

        public int getContentTextColor() {
            return contentTextColor;
        }

        public Builder setContentTextColor(@ColorRes int contentTextColor) {
            this.contentTextColor = ContextCompat.getColor(mContext, contentTextColor);
            return this;
        }

        public String getLeftButtonText() {
            return leftButtonText;
        }

        public Builder setLeftButtonText(String leftButtonText) {
            this.leftButtonText = leftButtonText;
            return this;
        }

        public int getLeftButtonTextColor() {
            return leftButtonTextColor;
        }

        public Builder setLeftButtonTextColor(@ColorRes int leftButtonTextColor) {
            this.leftButtonTextColor = ContextCompat.getColor(mContext, leftButtonTextColor);
            return this;
        }

        public String getRightButtonText() {
            return rightButtonText;
        }

        public Builder setRightButtonText(String rightButtonText) {
            this.rightButtonText = rightButtonText;
            return this;
        }

        public int getRightButtonTextColor() {
            return rightButtonTextColor;
        }

        public Builder setRightButtonTextColor(@ColorRes int rightButtonTextColor) {
            this.rightButtonTextColor = ContextCompat.getColor(mContext, rightButtonTextColor);
            return this;
        }

        public boolean getTitleVisible() {
            return isTitleVisible;
        }
        public boolean getLeftBtnVisible() {
            return isLeftBtnVisible;
        }

        public Builder setTitleVisible(boolean titleVisible) {
            isTitleVisible = titleVisible;
            return this;
        }
        public Builder setLeftBtnVisible(boolean leftBtnVisible) {
            isLeftBtnVisible = leftBtnVisible;
            return this;
        }

        public boolean isTouchOutside() {
            return isTouchOutside;
        }

        public Builder setCanceledOnTouchOutside(boolean touchOutside) {
            isTouchOutside = touchOutside;
            return this;
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

        public int getContentTextSize() {
            return contentTextSize;
        }

        public Builder setContentTextSize(int contentTextSize) {
            this.contentTextSize = contentTextSize;
            return this;
        }

        public int getTitleTextSize() {
            return titleTextSize;
        }

        public Builder setTitleTextSize(int titleTextSize) {
            this.titleTextSize = titleTextSize;
            return this;
        }

        public int getButtonTextSize() {
            return buttonTextSize;
        }

        public Builder setButtonTextSize(int buttonTextSize) {
            this.buttonTextSize = buttonTextSize;
            return this;
        }

        public DialogOnClickListener getListener() {
            return listener;
        }

        public Builder setOnclickListener(DialogOnClickListener listener) {
            this.listener = listener;
            return this;
        }

        public MDAlertDialog build() {

            return new MDAlertDialog(this);
        }
    }

}
