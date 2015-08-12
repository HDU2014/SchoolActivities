package com.lugeek.schoolevents.ui.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Looper;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;

import com.lugeek.schoolevents.R;


public class CustomBottom extends View {

    private int mColor = getResources().getColor(R.color.tab);
    private Bitmap mIconBitmap;
    private String mText = "tab";
    private int mTextSize = (int) TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_SP, 12, getResources().getDisplayMetrics());

    private Canvas mCanvas;
    private Bitmap mBitmap;
    private Paint mPaint;

    private float mAlpha;

    private Rect mIconRect;
    private Rect mTextBound;

    private Paint mTexPaint;

    /**
     * 调用两个参数的构造方法
     *
     * @param context
     */
    public CustomBottom(Context context) {
        this(context, null);
    }

    /**
     * 调用三个参数的构造方法
     *
     * @param context
     * @param attrs
     */
    public CustomBottom(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    /**
     * 获取自定义属性的值
     *
     * @param context
     * @param attrs
     * @param defStyleAttr
     */
    public CustomBottom(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        TypedArray a = context.obtainStyledAttributes(attrs,
                R.styleable.CustomBottom);
        for (int i = 0; i < a.getIndexCount(); i++) {
            int attr = a.getIndex(i);
            switch (attr) {
                case R.styleable.CustomBottom_micon :
                    BitmapDrawable drawable = (BitmapDrawable) a
                            .getDrawable(attr);
                    mIconBitmap = drawable.getBitmap();
                    break;
                case R.styleable.CustomBottom_mcolor :
                    mColor = a.getColor(attr, 0xFF45C01A);
                    break;
                case R.styleable.CustomBottom_mtext :
                    mText = a.getString(attr);
                    break;
                case R.styleable.CustomBottom_mtext_size :
                    mTextSize = (int) a.getDimension(attr, TypedValue
                            .applyDimension(TypedValue.COMPLEX_UNIT_SP, 12,
                                    getResources().getDisplayMetrics()));
                    break;
            }
        }

        a.recycle();

        mTextBound = new Rect();
        mTexPaint = new Paint();
        mTexPaint.setTextSize(mTextSize);
        mTexPaint.setColor(0xff555555);
        mTexPaint.getTextBounds(mText, 0, mText.length(), mTextBound);

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        // 比较view的横向宽度和纵向高度，取最小值为图标的宽度
        int iconWidth = Math.min(getMeasuredWidth() - getPaddingLeft()
                - getPaddingRight(), getMeasuredHeight() - getPaddingTop()
                - getPaddingBottom() - mTextBound.height());
        // 得到Icon的左和上
        int left = (getMeasuredWidth() - iconWidth) / 2;
        int top = (getMeasuredHeight() - mTextBound.height() - iconWidth) / 2;
        // 测出Icon的区域
        mIconRect = new Rect(left, top, left + iconWidth, top + iconWidth);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        // 获取透明度
        int alpha = (int) Math.ceil(255 * mAlpha);
        // 首先绘制不带颜色的图标
        canvas.drawBitmap(mIconBitmap, null, mIconRect, null);
        // 再绘制DST_IN图标
        setupTargetBitmap(alpha);
        canvas.drawBitmap(mBitmap, 0, 0, null);
        // 1、绘制原文本 ； 2、绘制变色的文本
        drawSourceText(canvas, alpha);
        drawTargetText(canvas, alpha);

    }

    /**
     * 在内存中绘制可变Icon
     */
    private void setupTargetBitmap(int alpha) {
        // 覆盖整个view的一个纯色图片，并支持透明度
        mBitmap = Bitmap.createBitmap(getMeasuredWidth(), getMeasuredHeight(),
                Config.ARGB_8888);
        mCanvas = new Canvas(mBitmap);
        mPaint = new Paint();
        mPaint.setColor(mColor);
        mPaint.setAntiAlias(true);
        mPaint.setDither(true);
        mPaint.setAlpha(alpha);
        // 在Icon区域画一个纯色矩形
        mCanvas.drawRect(mIconRect, mPaint);
        mPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_IN));
        mPaint.setAlpha(255);
        // 画一个DST_IN的图标，DST_IN的效果使得纯色矩形变成了图标的形状
        mCanvas.drawBitmap(mIconBitmap, null, mIconRect, mPaint);
    }
    /**
     * 绘制原文本
     *
     * @param canvas
     * @param alpha
     */
    private void drawSourceText(Canvas canvas, int alpha) {
        mTexPaint.setColor(0xff333333);
        // 从mText的左下角开始画，x轴向右为正，y轴向下为正
        int x = (getMeasuredWidth() - mTextBound.width()) / 2;
        int y = mIconRect.bottom + mTextBound.height();
        canvas.drawText(mText, x, y, mTexPaint);
    }

    /**
     * 绘制变色文本
     *
     * @param canvas
     * @param alpha
     */
    private void drawTargetText(Canvas canvas, int alpha) {
        mTexPaint.setColor(mColor);
        mTexPaint.setAlpha(alpha);
        int x = (getMeasuredWidth() - mTextBound.width()) / 2;
        int y = mIconRect.bottom + mTextBound.height();
        canvas.drawText(mText, x, y, mTexPaint);
    }

    /**
     * 设置渐变
     *
     * @param alpha
     */
    public void setIconAlpha(float alpha) {
        this.mAlpha = alpha;
        invalidateView();
    }

    /**
     * 重绘
     */
    private void invalidateView() {
        if (Looper.getMainLooper() == Looper.myLooper()) {
            invalidate();
        } else {
            postInvalidate();
        }
    }

    private static final String INSTANCE_STATUS = "instance_status";
    private static final String STATUS_ALPHA = "status_alpha";

    @Override
    protected Parcelable onSaveInstanceState()
    {
        Bundle bundle = new Bundle();
        bundle.putParcelable(INSTANCE_STATUS, super.onSaveInstanceState());
        bundle.putFloat(STATUS_ALPHA, mAlpha);
        return bundle;
    }

    @Override
    protected void onRestoreInstanceState(Parcelable state)
    {
        if (state instanceof Bundle)
        {
            Bundle bundle = (Bundle) state;
            mAlpha = bundle.getFloat(STATUS_ALPHA);
            super.onRestoreInstanceState(bundle.getParcelable(INSTANCE_STATUS));
            return;
        }
        super.onRestoreInstanceState(state);
    }

}