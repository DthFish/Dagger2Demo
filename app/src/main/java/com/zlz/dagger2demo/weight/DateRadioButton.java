package com.zlz.dagger2demo.weight;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.RadioButton;

import com.zlz.dagger2demo.R;


/**
 * Description ${Desc}
 * Author Zhaolizhi
 * Date 2016/10/21.
 */

public class DateRadioButton extends RadioButton {
    private String TAG="TAG";
    private int TEXTMARGIN = 16;
    private int RADIUS = 6;
    private Paint mPaint;
    private Context mContext;
    private int mPinkColor;
    private int mNormalColor;
    private Paint mTextPaint;
    private int mHeight;
    private int mWidth;
    private RectF mRectF;
    private Rect mNumRect;
    private Rect mDayRect;
    private Paint mDayPaint;
    private int mRightArea =40;
    private RectF mLeftRectF;
    private Paint mRingPaint;

    public DateRadioButton(Context context) {
        this(context, null);
    }

    public DateRadioButton(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DateRadioButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        init();
    }

    public DateRadioButton(Context context,String text,int size,int padding,boolean isChecked){
        this(context);
        setText(text);
        setPadding(padding,padding,padding,padding);
        setChecked(isChecked);
        setTextSize(size);
    }

    private void init() {
        this.setClickable(true);
        mPinkColor = ContextCompat.getColor(mContext, R.color.color_check);
        mNormalColor = ContextCompat.getColor(mContext, R.color.color_normal);
        mPaint = new Paint();
        mPaint.setDither(true);
        mPaint.setAntiAlias(true);
        mPaint.setColor(mPinkColor);
        mPaint.setStyle(Paint.Style.FILL);

        mTextPaint = new Paint();
        mTextPaint.setAntiAlias(true);
        mTextPaint.setDither(true);
        mTextPaint.setStyle(Paint.Style.FILL);
        mTextPaint.setTypeface(Typeface.DEFAULT_BOLD);
        mTextPaint.setTextSize(getTextSize());
        mTextPaint.setColor(Color.WHITE);

        mDayPaint = new Paint();
        mDayPaint.setAntiAlias(true);
        mDayPaint.setDither(true);
        mDayPaint.setStyle(Paint.Style.FILL);
        mDayPaint.setTypeface(Typeface.DEFAULT_BOLD);
        mDayPaint.setTextSize(getTextSize());
        mDayPaint.setColor(Color.WHITE);

        mRingPaint = new Paint();
        mRingPaint.setAntiAlias(true);
        mRingPaint.setDither(true);
        mRingPaint.setStyle(Paint.Style.STROKE);
        mRingPaint.setStrokeWidth(dip2px(mContext,1.5f));
        mRingPaint.setColor(mPinkColor);

        mLeftRectF =new RectF();
        mRectF = new RectF();
        mNumRect = new Rect();
        mDayRect = new Rect();
        mRectF = new RectF();

        mRightArea = dip2px(mContext,20);
        TEXTMARGIN= dip2px(mContext,8);
        RADIUS=dip2px(mContext,3);
    }

    @Override
    public void setChecked(boolean checked) {
        super.setChecked(checked);
        invalidate();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mHeight = getMeasuredHeight();
        mWidth = getMeasuredWidth();
        mRectF.left = 0;
        mRectF.top = 0;
        mRectF.right = mWidth;
        mRectF.bottom = mHeight;

        mLeftRectF.right = mWidth - mRightArea;
        mLeftRectF.bottom = mHeight;

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        getTextRect(mTextPaint, getText().toString().trim(),mNumRect);
        getTextRect(mDayPaint, "DAY",mDayRect);
        int height = mNumRect.bottom - mNumRect.top + mDayRect.bottom - mDayRect.top + TEXTMARGIN + getPaddingTop() + getPaddingBottom();
        int width = Math.max((mNumRect.right - mNumRect.left), (mDayRect.right - mDayRect.left)) + getPaddingLeft() + getPaddingRight();
        int size = Math.max(height, width);

        if (widthMode != MeasureSpec.EXACTLY) {

            widthSize = size + mRightArea;
        }
        if (heightMode != MeasureSpec.EXACTLY) {
            heightSize = size;
        }
        setMeasuredDimension(widthSize, heightSize);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        mPaint.setColor(isChecked() ? mPinkColor : mNormalColor);
        canvas.drawRoundRect(mLeftRectF, RADIUS, RADIUS, mPaint);
        getTextRect(mTextPaint, getText().toString().trim(),mNumRect);
        getTextRect(mDayPaint, "DAY",mDayRect);
        int hight = -mNumRect.top - mDayRect.top + TEXTMARGIN;
        int width = mDayRect.right;
        Log.d(TAG, "num: " + mNumRect.left + "," +mNumRect.right);

        canvas.drawText(getText().toString().trim(),(mRectF.right-mRightArea-mNumRect.right)*.5f,(mRectF.bottom-TEXTMARGIN)*.5f,mTextPaint);
        canvas.drawText("DAY",(mRectF.right-mRightArea-width)*.5f,(mRectF.bottom+hight)*.5f,mDayPaint);
        if(isChecked()){
            canvas.drawCircle(mRectF.right - dip2px(mContext,7),mRectF.bottom *.5f,dip2px(mContext,2.5f),mPaint);
            canvas.drawCircle(mRectF.right - dip2px(mContext,7),mRectF.bottom *.5f,dip2px(mContext,5),mRingPaint);
        }
    }

    public void getTextRect(Paint paint, String text, Rect rect) {
        paint.getTextBounds(text, 0, text.length(), rect);
    }


    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

}
