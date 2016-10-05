package com.frank.protean;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;

/**
 * Created by maowenqiang on 16/9/27.
 */
public class AutoTextview extends TextView {
    private float minTextSize, maxTextSize;
    private float DEFAULT_MIN_TEXT_SIZE;
    private float DEFAULT_MAX_TEXT_SIZE;
    private ChangeLayoutListener listener;
    private View parentView;
    private int width = 0;
    private int oldWidth = 0;
    private int height = 0;
    private boolean isChangeHeight;     //变化文字的大小后是否跟随文字高度保持同步
    public AutoTextview(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray a = getContext().obtainStyledAttributes(attrs,
                R.styleable.AutoTextView);
        isChangeHeight = a.getBoolean(
                R.styleable.AutoTextView_isChangeHeight, false);
        DEFAULT_MIN_TEXT_SIZE = a.getDimension(R.styleable.AutoTextView_minTextSize, 1);
        DEFAULT_MAX_TEXT_SIZE = a.getDimension(R.styleable.AutoTextView_maxTextSize, 30);
        a.recycle();
        initialise();
    }


    public void setChangeLayoutListener(ChangeLayoutListener listener, View view) {
        this.listener = listener;
        this.parentView = view;
    }

    public boolean isChangeHeight() {
        return isChangeHeight;
    }


    public void setChangeHeight(boolean isChangeHeight) {
        this.isChangeHeight = isChangeHeight;
    }


    private void initialise() {
        maxTextSize = this.getTextSize();

        if (maxTextSize <= DEFAULT_MIN_TEXT_SIZE) {
            maxTextSize = DEFAULT_MAX_TEXT_SIZE;
        }


        minTextSize = DEFAULT_MIN_TEXT_SIZE;
    };

    /**
     *
     * @param text   显示的字符串
     * @param textWidth   textview的宽度
     * @param preWidth
    textview变化前的宽度
     */


    private void resize(String text, int textWidth, int preWidth) {


        if(listener!=null) {
            if(!listener.isOk(parentView)) {   //是否需要重新计算宽度
                return;
            }
        }
        boolean isUpdate = false;
        if (textWidth > 0) {
            int availableWidth = textWidth - this.getPaddingLeft()
                    - this.getPaddingRight();
            float preSize = getPaint().getTextSize();
            float curSize = 0;
            curSize = maxTextSize;
            getPaint().setTextSize(curSize);
            if((curSize > minTextSize)
                    && (getPaint().measureText(text) > availableWidth)) { //尝试用最初的字体大小计算宽度是否达到调整的标准

                if(preWidth < textWidth) {  //为了避免一直计算，判断当前是从小到大还是从大小
                    curSize = maxTextSize;   //从小到大，使用最大字体大小计算变化量
                }else {
                    curSize = preSize;   //从大到小或者不变，使用当前的字体大小计算变化量
                }
                getPaint().setTextSize(curSize);
                while ((curSize > minTextSize)
                        && (getPaint().measureText(text) > availableWidth)) { //如果符合标准并且当前的大小也需要调整，调整宽度到正好合适
                    isUpdate = true;
                    curSize -= 1;
                    if (curSize <= minTextSize) {
                        curSize = minTextSize;
                        getPaint().setTextSize(curSize);
                        break;
                    }
                    getPaint().setTextSize(curSize);


                }
                if (isUpdate) {  //成功调整完毕，接口通知完成刷新


// invalidate();
                    if (listener != null) {
                        listener.isChange(parentView,text);
                    }
                }
            }

        }




    };




    @Override
    protected void onDraw(Canvas canvas) {
// TODO Auto-generated method stub
        width = getWidth();

        if(oldWidth == 0) {
            oldWidth = width;
        }
        if (getWidth() > 0) {
            height = getHeight();
            resize(getText().toString(), width,oldWidth);
            super.onDraw(canvas);
            oldWidth = width;


        } else {
            super.onDraw(canvas);
            height = getHeight();
            width = getWidth();
            resize(getText().toString(), width,oldWidth);
            oldWidth = width;
        }


    }




    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        if (!isChangeHeight && height > 0 && width > 0) {   //保持原有高度不变
            super.onMeasure(widthMeasureSpec, heightMeasureSpec);
            setMeasuredDimension(getMeasuredWidth(), height);

        } else {
            super.onMeasure(widthMeasureSpec, heightMeasureSpec);


        }
    }


    public interface ChangeLayoutListener {    //计算前和计算后的监听接口

        public void isChange(View view,String text);   //计算完成通知外层刷新
        public boolean isOk(View view);   //是否要进行计算
    }



}