package com.yzy.myview.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;

import java.util.List;

public class FloatLayout extends LinearLayout {


    private List<View> views;
    private Status status=Status.CLOSE;
    private int padding=30;
    private int minLine=2;
    private int maxLine=4;

    private View closeView;
    private View openView;
    public enum Status
    {
        OPEN, CLOSE
    }

    public FloatLayout(Context context) {
        super(context);
    }

    public FloatLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public FloatLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setViews(List<View> views){
       this.views.clear();
       this.removeAllViews();
       if(null==views)return;
       this.views.addAll(views);
       post(new Runnable() {
           @Override
           public void run() {
               drawViews();
           }
       });
    }

    private void drawViews(){
        if(null==views) return;
        int width=getMeasuredWidth();
        LinearLayout.LayoutParams params=new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.WRAP_CONTENT);

        int currentRow=1;
        LinearLayout row=new LinearLayout(getContext());
        row.setLayoutParams(params);
        row.setOrientation(LinearLayout.HORIZONTAL);
        row.setPadding(0,0,0,0);


        for (int i=0;i<views.size();i++){
            int measureSpec=View.MeasureSpec.makeMeasureSpec(0,View.MeasureSpec.UNSPECIFIED);
            View view=views.get(i);
            view.measure(measureSpec,measureSpec);
            int viewWidth=view.getMeasuredWidth();
            if(width-viewWidth>0)
            if(width>views.get(i).getMeasuredWidth())
            row.addView(views.get(i));



        }
    }


}
