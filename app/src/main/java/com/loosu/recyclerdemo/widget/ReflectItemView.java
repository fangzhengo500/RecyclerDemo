package com.loosu.recyclerdemo.widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;

public class ReflectItemView extends FrameLayout {
    private static final String TAG = "ReflectItemView";

    private Paint mRefPaint = null;
    private Bitmap mReflectBitmap;
    private Canvas mReflectCanvas;
    private View mContentView;

    private boolean mIsReflection = true;   // 是否显示阴影
    private int mRefMargin = 0;     // 阴影间距
    private int mRefheight = 200;   // 阴影高度

    public ReflectItemView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context, attrs);
    }

    public ReflectItemView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public ReflectItemView(Context context) {
        super(context);
        init(context, null);
    }

    private void init(Context context, AttributeSet attrs) {
        if (mRefPaint == null) {
            mRefPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
            // 倒影渐变.
            mRefPaint.setShader(new LinearGradient(0, 0, 0, mRefheight,
                    0xCCffffff, 0x00ffffff, Shader.TileMode.MIRROR));
            mRefPaint.setXfermode(new PorterDuffXfermode(
                    PorterDuff.Mode.MULTIPLY));
        }
        setClipChildren(false);
        setClipToPadding(false);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        if (getChildCount() > 0) {
            mContentView = getChildAt(0);
        }
    }

    @Override
    public void addView(View child) {
        mContentView = child;
        super.addView(child);
    }

    public View getContentView() {
        return mContentView;
    }


    public void setReflection(boolean ref) {
        mIsReflection = ref;
        invalidate();
    }

    public boolean isReflection() {
        return this.mIsReflection;
    }

    @Override
    protected void dispatchDraw(Canvas canvas) {
        super.dispatchDraw(canvas);
        if (mIsReflection) {
            // 创建一个画布.
            if (mReflectBitmap == null && mContentView != null) {
                mReflectBitmap = Bitmap.createBitmap(mContentView.getWidth(),
                        mRefheight, Bitmap.Config.ARGB_8888);
                mReflectCanvas = new Canvas(mReflectBitmap);
            }

            // 绘制倒影.
            if (mContentView != null) {
                drawReflection(mReflectCanvas);
                canvas.save();
                int dy = mContentView.getBottom() + mRefMargin;
                int dx = mContentView.getLeft();
                canvas.translate(dx, dy);
                canvas.drawBitmap(mReflectBitmap, 0, 0, null);
                canvas.restore();
            }
        }
    }

    public Bitmap getReflectBitmap() {
        return mReflectBitmap;
    }

    /**
     * 绘制倒影.
     */
    public void drawReflection(Canvas canvas) {
        canvas.save();
        canvas.clipRect(0, 0, mContentView.getWidth(), mRefheight);
        canvas.save();
        canvas.scale(1, -1);
        canvas.translate(0, -mContentView.getHeight());
        mContentView.draw(canvas);
        canvas.restore();
        canvas.drawRect(0, 0, mContentView.getWidth(), mRefheight, mRefPaint);
        canvas.restore();
    }
}