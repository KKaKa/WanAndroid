package com.kkaka.wanandroid.common.behavior;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPropertyAnimatorListenerAdapter;
import android.support.v4.view.animation.FastOutLinearInInterpolator;
import android.support.v4.view.animation.LinearOutSlowInInterpolator;
import android.util.AttributeSet;
import android.view.View;

/**
 * @author Laizexin on 2019/12/9
 * @description
 */
public class ScaleBehavior<V extends View> extends CoordinatorLayout.Behavior<V> {

    private FastOutLinearInInterpolator fastOutLinearInInterpolator = new FastOutLinearInInterpolator();
    private LinearOutSlowInInterpolator linearOutSlowInInterpolator = new LinearOutSlowInInterpolator();

    private boolean isRunning;//是否在滑动

    public ScaleBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onStartNestedScroll(@NonNull CoordinatorLayout coordinatorLayout, @NonNull V child, @NonNull View directTargetChild, @NonNull View target, int axes, int type) {
        return axes == ViewCompat.SCROLL_AXIS_VERTICAL;//垂直滚动
    }

    @Override
    public void onNestedScroll(@NonNull CoordinatorLayout coordinatorLayout, @NonNull V child, @NonNull View target, int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed, int type) {
        super.onNestedScroll(coordinatorLayout, child, target, dxConsumed, dyConsumed, dxUnconsumed, dyUnconsumed, type);
        if(dyConsumed > 0 && !isRunning && child.getVisibility() == View.VISIBLE){
            //向下滑动 缩放隐藏控件
            scaleHide(child);
        }else if(dyConsumed < 0 && !isRunning && child.getVisibility() ==View.INVISIBLE){
            //向上滑动 缩放隐藏控件
            scaleShow(child);
        }
    }

    private void scaleHide(final View child) {
        ViewCompat.animate(child)
                .setDuration(500)
                .translationX(child.getWidth())
                .translationY(0)
                .setInterpolator(fastOutLinearInInterpolator)
                .setListener(new ViewPropertyAnimatorListenerAdapter(){
                    @Override
                    public void onAnimationStart(View view) {
                        super.onAnimationStart(view);
                        isRunning = true;
                    }

                    @Override
                    public void onAnimationEnd(View view) {
                        super.onAnimationEnd(view);
                        isRunning = false;
                        child.setVisibility(View.INVISIBLE);
                    }

                    @Override
                    public void onAnimationCancel(View view) {
                        super.onAnimationCancel(view);
                        isRunning = false;
                    }
                });
    }

    private void scaleShow(View child) {
        child.setVisibility(View.VISIBLE);
        ViewCompat.animate(child)
                .setDuration(500)
                .setInterpolator(linearOutSlowInInterpolator)
                .translationX(0)
                .translationY(0)
                .setListener(new ViewPropertyAnimatorListenerAdapter(){
                    @Override
                    public void onAnimationStart(View view) {
                        super.onAnimationStart(view);
                        isRunning = true;
                    }

                    @Override
                    public void onAnimationEnd(View view) {
                        super.onAnimationEnd(view);
                        isRunning = false;
                    }

                    @Override
                    public void onAnimationCancel(View view) {
                        super.onAnimationCancel(view);
                        isRunning = false;
                    }
                });
    }
}
