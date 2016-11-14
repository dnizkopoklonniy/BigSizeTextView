package com.niz.d.bigsizetextview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.NonNull;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * That custom TextView for big text size.
 * TextView without top and bottom padding.
 * Distance themselves in the big font size.
 */
public class BigSizeTextView extends TextView {

    public BigSizeTextView(Context context) {
        super(context);
    }

    public BigSizeTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public BigSizeTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(@NonNull Canvas canvas) {
        final String text = calculateTextParams();

        final int left = mBounds.left;
        final int bottom = mBounds.bottom;
        mBounds.offset(-mBounds.left, -mBounds.top);
        mPaint.setAntiAlias(true);
        mPaint.setColor(getCurrentTextColor());
        canvas.drawText(text, -left, mBounds.bottom - bottom, mPaint);
    }

    private final Paint mPaint = new Paint();
    private final Rect mBounds = new Rect();

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        calculateTextParams();
        setMeasuredDimension(mBounds.width() + 1, -mBounds.top + 1);
    }

    private String calculateTextParams() {
        final String text = getText().toString();
        final int textLength = text.length();

        mPaint.setTextSize(getTextSize());
        mPaint.getTextBounds(text, 0, textLength, mBounds);

        if (textLength == 0) {
            mBounds.right = mBounds.left;
        }
        return text;
    }
}