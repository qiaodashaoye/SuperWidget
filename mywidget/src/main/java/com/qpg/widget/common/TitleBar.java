package com.qpg.widget.common;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.qpg.widget.R;

import java.util.LinkedList;

public class TitleBar extends ViewGroup implements View.OnClickListener {
    private static final int DEFAULT_MAIN_TEXT_SIZE = 18;
    private static final int DEFAULT_SUB_TEXT_SIZE = 12;
    private static final int DEFAULT_ACTION_TEXT_SIZE = 15;
    private static final int DEFAULT_TITLE_BAR_HEIGHT = 48;

    private static final String STATUS_BAR_HEIGHT_RES_NAME = "status_bar_height";

    private TextView mLeftText;
    private LinearLayout mRightLayout;
    private LinearLayout mCenterLayout;
    private ImageView mRightImg;
    private TextView mCenterText;
    private TextView mSubTitleText;
    private TextView mRightText;
    private View mCustomCenterView;
    private View mDividerView;

    private boolean mImmersive;

    private int mScreenWidth;
    private int mStatusBarHeight;
    private int mActionPadding;
    private int mOutPadding;
    private int mActionTextColor;
    private int mHeight;


    private int leftImg;              //左侧图片（一般为返回按钮）
    private String leftText;             // 左侧文字
    private int leftTextColor;       //  左侧文字颜色
    private float leftTextSize;        //  左侧文字大小
    private String centerText;          // 中间文字
    private int centerTextColor;    // 中间文字颜色
    private float centerTextSize;     // 中间文字大小
    private String subtitleText;       // 二级标题（如果有则显示）
    private int subtitleTextColor; // 二级标题颜色
    private float subtitleTextSize;  //  二级标题大小
    private int rightImg;          // 右侧图片
    private String rightText;         //  右侧文字
    private int rightTextColor;   // 右侧文字颜色
    private float rightTextSize;    // 右侧文字大小

    public TitleBar(Context context) {
        super(context);
        init(context);
    }

    public TitleBar(Context context, AttributeSet attrs) {
      //  super(context, attrs);
        this(context, attrs,0);
        init(context);
    }

    public TitleBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        //获取自定义属性的值
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.TitleBar, defStyleAttr, 0);

        leftImg=a.getResourceId(R.styleable.TitleBar_leftImg,0);
        leftText=a.getString(R.styleable.TitleBar_leftText);
        leftTextColor=a.getColor(R.styleable.TitleBar_leftTextColor, Color.WHITE);
        leftTextSize=a.getDimension(R.styleable.TitleBar_leftTextSize,DEFAULT_ACTION_TEXT_SIZE);
        centerText=a.getString(R.styleable.TitleBar_centerText);
        centerTextColor=a.getColor(R.styleable.TitleBar_centerTextColor,Color.WHITE);
        centerTextSize=a.getDimension(R.styleable.TitleBar_centerTextSize,DEFAULT_MAIN_TEXT_SIZE);
        subtitleText=a.getString(R.styleable.TitleBar_subtitleText);
        subtitleTextColor=a.getColor(R.styleable.TitleBar_subtitleTextColor,Color.WHITE);
        subtitleTextSize=a.getDimension(R.styleable.TitleBar_subtitleTextSize,DEFAULT_SUB_TEXT_SIZE);
        rightImg=a.getResourceId(R.styleable.TitleBar_rightImg,0);
        rightText=a.getString(R.styleable.TitleBar_rightText);
        rightTextColor=a.getColor(R.styleable.TitleBar_rightTextColor,Color.WHITE);
        rightTextSize=a.getDimension(R.styleable.TitleBar_rightTextSize,DEFAULT_ACTION_TEXT_SIZE);
        init(context);
        a.recycle();  //注意回收

    }

    private void init(Context context) {
        if (mImmersive) {
            mStatusBarHeight = getStatusBarHeight();
        }
        mActionPadding = dip2px(5);
        mOutPadding = dip2px(8);
        mHeight = dip2px(DEFAULT_TITLE_BAR_HEIGHT);
        initView(context);
    }

    private void initView(Context context) {
        mLeftText = new TextView(context);
        mCenterLayout = new LinearLayout(context);
        mRightLayout = new LinearLayout(context);
        mDividerView = new View(context);

        mRightImg = new ImageView(context);
        mCenterText = new TextView(context);
        mSubTitleText = new TextView(context);
        mRightText = new TextView(context);

        LayoutParams layoutParams = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.MATCH_PARENT);

        mLeftText.setTextSize(DEFAULT_ACTION_TEXT_SIZE);
        mLeftText.setSingleLine();
        mLeftText.setGravity(Gravity.CENTER_VERTICAL);
        mLeftText.setPadding(mOutPadding + mActionPadding, 0, mOutPadding, 0);

        mRightText.setTextSize(DEFAULT_ACTION_TEXT_SIZE);
        mRightText.setSingleLine();
        mRightText.setGravity(Gravity.CENTER_VERTICAL);
        mRightText.setPadding(mOutPadding, 0, mOutPadding + mActionPadding, 0);

      //  mCenterLayout.setOrientation(LinearLayout.VERTICAL);
        mCenterLayout.addView(mCenterText);
        mCenterLayout.addView(mSubTitleText);
      //  mRightLayout.addView(mRightImg);
        mRightLayout.addView(mRightText,layoutParams);

        mCenterLayout.setGravity(Gravity.CENTER);
        mCenterText.setTextSize(DEFAULT_MAIN_TEXT_SIZE);
        mCenterText.setSingleLine();
        mCenterText.setGravity(Gravity.CENTER);
        mCenterText.setEllipsize(TextUtils.TruncateAt.END);

        mSubTitleText.setTextSize(DEFAULT_SUB_TEXT_SIZE);
        mSubTitleText.setSingleLine();
        mSubTitleText.setGravity(Gravity.CENTER);
        mSubTitleText.setEllipsize(TextUtils.TruncateAt.END);

        mRightLayout.setPadding(mOutPadding, 0, mOutPadding, 0);

        addView(mLeftText, layoutParams);
        addView(mCenterLayout);
        addView(mRightLayout, layoutParams);
        addView(mDividerView, new LayoutParams(LayoutParams.MATCH_PARENT, 1));

        setLeftImageResource(leftImg);
        mLeftText.setText(leftText);
        mLeftText.setTextColor(leftTextColor);
        mLeftText.setTextSize(leftTextSize);

        mCenterText.setText(centerText);
        mCenterText.setTextColor(centerTextColor);
        mCenterText.setTextSize(centerTextSize);

        mSubTitleText.setText(subtitleText);
        mSubTitleText.setTextColor(subtitleTextColor);
        mSubTitleText.setTextSize(subtitleTextSize);

        mRightImg.setImageResource(rightImg);
        mRightText.setText(rightText);
        mRightText.setTextColor(rightTextColor);
        mRightText.setTextSize(rightTextSize);
    }

    /**
     * 设置是否沉浸式，如果传入true得确定已经在代
     * 码中设置好沉浸式效果，如果没有设置也可调用
     * setImmersive(Activity activity,boolean isTransparent)
     * 帮你完成琛沉浸式效果
     * @param immersive
     * @return
     */
    public TitleBar setImmersive(boolean immersive) {
        mImmersive = immersive;
        if (mImmersive) {
            mStatusBarHeight = getStatusBarHeight();
        } else {
            mStatusBarHeight = 0;
        }
        return this;
    }

    /**
     * 设置状态栏半透明还是全透明
     * @param activity
     * @param isTransparent
     * @return
     */
    public TitleBar setImmersive(Activity activity,boolean isTransparent) {
        if (hasKitKat() && !hasLollipop()) {
            //透明状态栏
            activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        } else if (hasLollipop()) {
            Window window = activity.getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS
                    | WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
//                            | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            if(isTransparent){
                window.setStatusBarColor(Color.TRANSPARENT);//全透明状态栏
            }else {
                window.setStatusBarColor(Color.parseColor("#0e000000"));//半透明状态栏
            }

        }
        this.setImmersive(true);
        return this;
    }

    //自定义状态栏颜色
    public TitleBar setImmersive(Activity activity,String colorS) {
        if (hasKitKat() && !hasLollipop()) {
            //透明状态栏
            activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        } else if (hasLollipop()) {
            Window window = activity.getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS
                    | WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
//                            | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            if(colorS==null){
                window.setStatusBarColor(Color.TRANSPARENT);//全透明状态栏
            }else {
                window.setStatusBarColor(Color.parseColor(colorS));
            }

        }
        this.setImmersive(true);
        return this;
    }

    public TitleBar setHeight(int height) {
        mHeight = height;
        setMeasuredDimension(getMeasuredWidth(), mHeight);
        return this;
    }

    public void setLeftImageResource(int resId) {
        mLeftText.setCompoundDrawablesWithIntrinsicBounds(resId, 0, 0, 0);
    }

    public TitleBar setLeftClickListener(OnClickListener l) {
        mLeftText.setOnClickListener(l);
        return this;
    }

    public TitleBar setLeftText(CharSequence title) {
        mLeftText.setText(title);
        return this;
    }

    public TitleBar setLeftText(int resid) {
        mLeftText.setText(resid);
        return this;
    }

    public TitleBar setLeftTextSize(float size) {
        mLeftText.setTextSize(size);
        return this;
    }

    public TitleBar setLeftTextColor(int color) {
        mLeftText.setTextColor(color);
        return this;
    }

    public TitleBar setLeftVisible(boolean visible) {
        mLeftText.setVisibility(visible ? View.VISIBLE : View.GONE);
        return this;
    }

    public TitleBar setTitle(CharSequence title) {
        int index = title.toString().indexOf("\n");
        if (index > 0) {
            setTitle(title.subSequence(0, index), title.subSequence(index + 1, title.length()), LinearLayout.VERTICAL);
        } else {
            index = title.toString().indexOf("\t");
            if (index > 0) {
                setTitle(title.subSequence(0, index), "  " + title.subSequence(index + 1, title.length()), LinearLayout.HORIZONTAL);
            } else {
                mCenterText.setText(title);
                mSubTitleText.setVisibility(View.GONE);
            }
        }
        return this;
    }

    private TitleBar setTitle(CharSequence title, CharSequence subTitle, int orientation) {
        mCenterLayout.setOrientation(orientation);
        mCenterText.setText(title);

        mSubTitleText.setText(subTitle);
        mSubTitleText.setVisibility(View.VISIBLE);
        return this;
    }

    public TitleBar setCenterClickListener(OnClickListener l) {
        mCenterLayout.setOnClickListener(l);
        return this;
    }

    public TitleBar setTitle(int resid) {
        setTitle(getResources().getString(resid));
        return this;
    }

    public TitleBar setTitleColor(int resid) {
        mCenterText.setTextColor(resid);
        return this;
    }

    public TitleBar setTitleSize(float size) {
        mCenterText.setTextSize(size);
        return this;
    }

    public TitleBar setTitleBackground(int resid) {
        mCenterText.setBackgroundResource(resid);
        return this;
    }

    public TitleBar setSubTitleColor(int resid) {
        mSubTitleText.setTextColor(resid);
        return this;
    }

    public TitleBar setSubTitleSize(float size) {
        mSubTitleText.setTextSize(size);
        return this;
    }

    public TitleBar setCustomTitle(View titleView) {
        if (titleView == null) {
            mCenterText.setVisibility(View.VISIBLE);
            if (mCustomCenterView != null) {
                mCenterLayout.removeView(mCustomCenterView);
            }

        } else {
            if (mCustomCenterView != null) {
                mCenterLayout.removeView(mCustomCenterView);
            }
            LayoutParams layoutParams = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
            mCustomCenterView = titleView;
            mCenterLayout.addView(titleView, layoutParams);
            mCenterText.setVisibility(View.GONE);
        }
        return this;
    }

    public TitleBar setDivider(Drawable drawable) {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            mDividerView.setBackground(drawable);
        } else {
            mDividerView.setBackgroundDrawable(drawable);
        }

        return this;
    }

    public TitleBar setDividerColor(int color) {
        mDividerView.setBackgroundColor(color);
        return this;
    }

    public TitleBar setDividerHeight(int dividerHeight) {
        mDividerView.getLayoutParams().height = dividerHeight;
        return this;
    }

    public TitleBar setActionTextColor(int colorResId) {
        mActionTextColor = colorResId;
        return this;
    }

    /**
     * Function to set a click listener for Title TextView
     *
     * @param listener the onClickListener
     */
    public TitleBar setOnTitleClickListener(OnClickListener listener) {
        mCenterText.setOnClickListener(listener);
        return this;
    }

    @Override
    public void onClick(View view) {
        final Object tag = view.getTag();
        if (tag instanceof Action) {
            final Action action = (Action) tag;
            action.performAction(view);
        }
    }

    /**
     * Adds a list of {@link Action}s.
     * @param actionList the actions to add
     */
    public void addActions(ActionList actionList) {
        int actions = actionList.size();
        for (int i = 0; i < actions; i++) {
            addAction(actionList.get(i));
        }
    }

    /**
     * Adds a new {@link Action}.
     * @param action the action to add
     */
    public View addAction(Action action) {
        final int index = mRightLayout.getChildCount();
        return addAction(action, index);
    }

    /**
     * Adds a new {@link Action} at the specified index.
     * @param action the action to add
     * @param index the position at which to add the action
     */
    public View addAction(Action action, int index) {
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT,
                LayoutParams.MATCH_PARENT);
        View view = inflateAction(action);
        mRightLayout.addView(view, index, params);
        return view;
    }

    /**
     * Removes all action views from this action bar
     */
    public void removeAllActions() {
        mRightLayout.removeAllViews();
    }

    /**
     * Remove a action from the action bar.
     * @param index position of action to remove
     */
    public void removeActionAt(int index) {
        mRightLayout.removeViewAt(index);
    }

    /**
     * Remove a action from the action bar.
     * @param action The action to remove
     */
    public void removeAction(Action action) {
        int childCount = mRightLayout.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View view = mRightLayout.getChildAt(i);
            if (view != null) {
                final Object tag = view.getTag();
                if (tag instanceof Action && tag.equals(action)) {
                    mRightLayout.removeView(view);
                }
            }
        }
    }

    /**
     * Returns the number of actions currently registered with the action bar.
     * @return action count
     */
    public int getActionCount() {
        return mRightLayout.getChildCount();
    }

    /**
     * Inflates a {@link View} with the given {@link Action}.
     * @param action the action to inflate
     * @return a view
     */
    private View inflateAction(Action action) {
        View view = null;
        if (TextUtils.isEmpty(action.getText())) {
            ImageView img = new ImageView(getContext());
            img.setImageResource(action.getDrawable());
            view = img;
        } else {
            TextView text = new TextView(getContext());
            text.setGravity(Gravity.CENTER);
            text.setText(action.getText());
            text.setTextSize(DEFAULT_ACTION_TEXT_SIZE);
            if (mActionTextColor != 0) {
                text.setTextColor(mActionTextColor);
            }
            view = text;
        }

        view.setPadding(mActionPadding, 0, mActionPadding, 0);
        view.setTag(action);
        view.setOnClickListener(this);
        return view;
    }

    public View getViewByAction(Action action) {
        View view = findViewWithTag(action);
        return view;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int height;
        if (heightMode != MeasureSpec.EXACTLY) {
            height = mHeight + mStatusBarHeight;
            heightMeasureSpec = MeasureSpec.makeMeasureSpec(mHeight, MeasureSpec.EXACTLY);
        } else {
            height = MeasureSpec.getSize(heightMeasureSpec) + mStatusBarHeight;
        }
        mScreenWidth = MeasureSpec.getSize(widthMeasureSpec);
        measureChild(mLeftText, widthMeasureSpec, heightMeasureSpec);
        measureChild(mRightLayout, widthMeasureSpec, heightMeasureSpec);
        if (mLeftText.getMeasuredWidth() > mRightLayout.getMeasuredWidth()) {
            mCenterLayout.measure(
                    MeasureSpec.makeMeasureSpec(mScreenWidth - 2 * mLeftText.getMeasuredWidth(), MeasureSpec.EXACTLY)
                    , heightMeasureSpec);
        } else {
            mCenterLayout.measure(
                    MeasureSpec.makeMeasureSpec(mScreenWidth - 2 * mRightLayout.getMeasuredWidth(), MeasureSpec.EXACTLY)
                    , heightMeasureSpec);
        }
        measureChild(mDividerView, widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(MeasureSpec.getSize(widthMeasureSpec), height);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        mLeftText.layout(0, mStatusBarHeight, mLeftText.getMeasuredWidth(), mLeftText.getMeasuredHeight() + mStatusBarHeight);
        mRightLayout.layout(mScreenWidth - mRightLayout.getMeasuredWidth(), mStatusBarHeight,
                mScreenWidth, mRightLayout.getMeasuredHeight() + mStatusBarHeight);
        if (mLeftText.getMeasuredWidth() > mRightLayout.getMeasuredWidth()) {
            mCenterLayout.layout(mLeftText.getMeasuredWidth(), mStatusBarHeight,
                    mScreenWidth - mLeftText.getMeasuredWidth(), getMeasuredHeight());
        } else {
            mCenterLayout.layout(mRightLayout.getMeasuredWidth(), mStatusBarHeight,
                    mScreenWidth - mRightLayout.getMeasuredWidth(), getMeasuredHeight());
        }
        mDividerView.layout(0, getMeasuredHeight() - mDividerView.getMeasuredHeight(), getMeasuredWidth(), getMeasuredHeight());
    }

    public static int dip2px(int dpValue) {
        final float scale = Resources.getSystem().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    /**
     * 计算状态栏高度高度
     * getStatusBarHeight
     * @return
     */
    public static int getStatusBarHeight() {
        return getInternalDimensionSize(Resources.getSystem(), STATUS_BAR_HEIGHT_RES_NAME);
    }

    private static int getInternalDimensionSize(Resources res, String key) {
        int result = 0;
        int resourceId = res.getIdentifier(key, "dimen", "android");
        if (resourceId > 0) {
            result = res.getDimensionPixelSize(resourceId);
        }
        return result;
    }


    /**
     * A {@link LinkedList} that holds a list of {@link Action}s.
     */
    @SuppressWarnings("serial")
    public static class ActionList extends LinkedList<Action> {
    }

    /**
     * Definition of an action that could be performed, along with a icon to
     * show.
     */
    public interface Action {
        String getText();
        int getDrawable();
        void performAction(View view);
    }

    public static abstract class ImageAction implements Action {
        private int mDrawable;

        public ImageAction(int drawable) {
            mDrawable = drawable;
        }

        @Override
        public int getDrawable() {
            return mDrawable;
        }

        @Override
        public String getText() {
            return null;
        }
    }

    public static abstract class TextAction implements Action {
        final private String mText;

        public TextAction(String text) {
            mText = text;
        }

        @Override
        public int getDrawable() {
            return 0;
        }

        @Override
        public String getText() {
            return mText;
        }
    }


    private boolean hasKitKat() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT;
    }

    private boolean hasLollipop() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP;
    }


}
