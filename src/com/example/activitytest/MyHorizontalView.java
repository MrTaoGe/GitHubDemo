package com.example.activitytest;

import com.lidroid.xutils.util.LogUtils;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.HorizontalScrollView;
import android.widget.Scroller;
/**
 * 自定义的可以控制水平滚动速度的控件。
 * @author MrTaoge
 *
 */
public class MyHorizontalView extends HorizontalScrollView {
	/**向左滚动*/
	public static final int TO_LEFT = 0;
	/**向右滚动*/
	public static final int TO_RIGHT = 1;
	private Scroller scroller;

	public MyHorizontalView(Context context) {
		super(context);
		initData();
	}

	public MyHorizontalView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		initData();
	}

	public MyHorizontalView(Context context, AttributeSet attrs) {
		super(context, attrs);
		initData();
	}
	
	private void initData() {
		scroller = new Scroller(getContext());
	}
	/**
	 * 通过控制滚动速度来实现控件平滑的滚动。
	 * @param direction 滚动方向。
	 * @param distanceX 水平滚动距离。
	 * @param distanceY 竖直值滚动距离。
	 * @param duration 滚动持续的时间。
	 */
	public void moveTo(int direction,int distanceX,int distanceY,int duration){
		if(direction==TO_RIGHT){
			distanceX = Math.abs(distanceX);
		}else if(direction==TO_LEFT){
			distanceX = - Math.abs(distanceX);
		}
		scroller.startScroll(getScrollX(),0,distanceX,distanceY,duration);
		invalidate();
		LogUtils.i("getScrollX()=="+getScrollX()+"***distanceX=="+distanceX);
	}
	
	/**
	 * 计算滚动过程中控件的坐标变化。
	 */
	@Override
	public void computeScroll() {
		super.computeScroll();
		if(scroller!=null&&scroller.computeScrollOffset()){
			int xOff = scroller.getCurrX();
			smoothScrollTo(xOff, 0);
			invalidate();
		}
	}
	@Override
	public boolean pageScroll(int direction) {
		return super.pageScroll(direction);
	}
}
