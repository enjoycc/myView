package com.yzy.myview.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnFocusChangeListener;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;


public class RollMenu extends ViewGroup implements View.OnClickListener,OnFocusChangeListener {
    /**
     * 第一个子控件为主按钮
     */
    private View cButton;
    /**
     * 动画持续时间
     */
    private long rollDuration=600;
    /**
     * 菜单为open状态时，主按钮的缩放比例
     */
    private float cButtonShrink=0.7f;
    /**
     * 主菜单旋转的角度
     */
    private float cButtonRotate=45f;
    /**
     * 控件当前的状态，OPEN or CLOSE
     */
    private Status mCurrentStatus=Status.CLOSE;

    private OnClickListener onClickListener;

    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        if (cButton == null)
        {
            cButton = getChildAt(0);
        }
        if(hasFocus){
            open(cButton);
        }else {
            close(cButton);
        }
    }

    @Override
    public void onClick(View v) {
//        cButton = findViewById(R.id.id_button);
        if (cButton == null)
        {
            cButton = getChildAt(0);
        }
        if(mCurrentStatus==Status.CLOSE){
            open(cButton);
        }else {
            close(cButton);
        }

    }

    public enum Status
    {
        OPEN, CLOSE
    }

    public RollMenu(Context context) {
        this(context, null);
    }

    public RollMenu(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RollMenu(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setRollDuration(long rollDuration) {
        this.rollDuration = rollDuration;
    }

    public void setcButtonShrink(float cButtonShrink) {
        this.cButtonShrink = cButtonShrink;
    }

    public void setOnClickListener(OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    public void setcButtonRotate(float cButtonRotate) {
        this.cButtonRotate = cButtonRotate;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int count = getChildCount();

        for (int i = 0; i < count; i++)
        {
            // mesure child
            measureChild(getChildAt(i),widthMeasureSpec,heightMeasureSpec);
        }
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        if(changed){
            int cHeight=layoutCButton();
            int right = getMeasuredWidth() ;
            int bottom = getMeasuredHeight() -cHeight;
            int count=getChildCount();
            for(int i=count-1;i>0;i--){
                View child=getChildAt(i);
                child.setVisibility(GONE);
                int width = child.getMeasuredWidth();
                int height = child.getMeasuredHeight();
                child.layout(right-width,bottom-height,right,bottom);
                bottom-=height;
                final int position=i;

                if(!child.hasOnClickListeners()){
                    child.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if(onClickListener!=null)onClickListener.onclick(v,position);
                            close(cButton);
                        }
                    });
                }
            }
        }
    }

    private int layoutCButton(){
        cButton=getChildAt(0);
        cButton.setOnClickListener(this);
        cButton.setFocusableInTouchMode(true);
        cButton.setFocusable(true);
        cButton.setOnFocusChangeListener(this);
        int l = 0;
        int t = 0;
        int width = cButton.getMeasuredWidth();
        int height = cButton.getMeasuredHeight();
        l = getMeasuredWidth() - width;
        t = getMeasuredHeight() - height;

        cButton.layout(l,t, l + width, t + height);
        return height;
    }





    private void open(View view){
        if(mCurrentStatus==Status.OPEN)return;
        mCurrentStatus=Status.OPEN;
        AnimationSet animationSet=new AnimationSet(true);
        animationSet.addAnimation(rotateAnimation(0,cButtonRotate));
        animationSet.addAnimation(scaleAnimation(1.0f, cButtonShrink, 1.0f, cButtonShrink,
                Animation.RELATIVE_TO_SELF, 0.5f,
                Animation.RELATIVE_TO_SELF, 0.5f));
        animationSet.setFillAfter(true);
        view.startAnimation(animationSet);

        int bottom= (int)(getChildAt(0).getMeasuredHeight()*cButtonShrink);
        int count=getChildCount();
        for(int i=count-1;i>0;i--){
            View child=getChildAt(i);
            int cWidth = child.getMeasuredWidth();
            AnimationSet childAnimation=new AnimationSet(true);
            childAnimation.addAnimation(rotateAnimation(0,360));
            childAnimation.addAnimation(translateAnimation(0, 0, bottom, 0));
            childAnimation.addAnimation(alphaAnimation(0, 1));
            childAnimation.setFillAfter(true);
            child.startAnimation(childAnimation);
            child.setVisibility(VISIBLE);
            bottom+=cWidth;
        }

    }

    private void close(View view){
        if(mCurrentStatus==Status.CLOSE)return;
        mCurrentStatus=Status.CLOSE;
        AnimationSet animationSet=new AnimationSet(true);
        animationSet.addAnimation(rotateAnimation(cButtonRotate,0));
        animationSet.addAnimation(scaleAnimation(cButtonShrink, 1.0f, cButtonShrink, 1.0f,
                Animation.RELATIVE_TO_SELF, 0.5f,
                Animation.RELATIVE_TO_SELF, 0.5f));
        animationSet.setFillAfter(true);
        view.startAnimation(animationSet);
        int bottom=(int)(getChildAt(0).getMeasuredHeight()*cButtonShrink);
        int count=getChildCount();
        for(int i=count-1;i>0;i--){
            final View child=getChildAt(i);
            int cWidth = child.getMeasuredWidth();
            AnimationSet childAnimation=new AnimationSet(true);
            childAnimation.addAnimation(rotateAnimation(0f, -360f));
            childAnimation.addAnimation(translateAnimation(0, 0, 0, bottom));
            childAnimation.addAnimation(alphaAnimation(1, 0));
            childAnimation.setFillAfter(true);
            child.startAnimation(childAnimation);
            child.postDelayed(new Runnable() {
                @Override
                public void run() {
                    child.clearAnimation();
                    child.setVisibility(GONE);
                }
            }, rollDuration);
            bottom+=cWidth;
        }


    }


    public interface OnClickListener{
        void onclick(View view,int position);
    }


    private  Animation rotateAnimation(float fromDegrees, float toDegrees){//旋转动画
        RotateAnimation rotate = new RotateAnimation(fromDegrees, toDegrees,
                Animation.RELATIVE_TO_SELF, 0.5f,
                Animation.RELATIVE_TO_SELF, 0.5f);
        rotate.setDuration(rollDuration);
        rotate.setFillAfter(true);
        return rotate;
    }

    private Animation scaleAnimation(float fromX, float toX, float fromY, float toY,
                                     int pivotXType, float pivotXValue, int pivotYType, float pivotYValue){//放大缩小动画
        ScaleAnimation scale = new ScaleAnimation(fromX, toX, fromY, toY,
                pivotXType, pivotXValue, pivotYType,pivotYValue);
        scale.setDuration(rollDuration);
        scale.setFillAfter(true);
        return scale;
    }

    private Animation translateAnimation(float fromXDelta, float toXDelta, float fromYDelta, float toYDelta){//平移动画
        TranslateAnimation translateAnimation=new TranslateAnimation(fromXDelta, toXDelta, fromYDelta, toYDelta);
        translateAnimation.setDuration(rollDuration);
        translateAnimation.setFillAfter(true);
        return translateAnimation;
    }

    private Animation alphaAnimation(float fromAlpha, float toAlpha){//透明度动画
        AlphaAnimation alphaAnimation=new AlphaAnimation(fromAlpha,toAlpha);
        alphaAnimation.setDuration(rollDuration);
        alphaAnimation.setFillAfter(true);
        return alphaAnimation;
    }


}
