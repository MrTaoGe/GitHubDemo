package com.example.activitytest;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * @author mingtj 自定义TextView控件 文字长度过长自动滚动
 *         主要就是重写isFocused方法，在内部返回true，也就是她始终可以获得焦点，这样的话设置跑马灯效果时可以实现滚动效果。
 *         <p>
 *         add by MrTaoge on 2015-12-30
 *         </p>
 *         <p>
 *         该textview新添加的功能：1.可以控制跑马灯的速度2.控制时间间隔3.控制滚动的停止与开始。
 *         </p>
 */
public class ScrollAlwaysTextView extends TextView implements Runnable {
	/** 初始滚动的位置*/
	private int currentScrollX = 0;
	private int firstScrollX = 0;
	private boolean isStop = false;
	private int textWidth;
	/**控件宽度*/ 
	private int mWidth = 0; 
	private int speed = 2;
	private int delayed = 1000;
	/**滚动到哪个位置*/
	private int endX; 
	/**当首次渲染文本或文本改变时重置*/ 
	private boolean isFirstDraw = true; 

	public ScrollAlwaysTextView(Context context) {
		this(context, null);
	}

	public ScrollAlwaysTextView(Context context, AttributeSet attrs) {
		this(context, attrs, android.R.attr.textViewStyle);
	}

	public ScrollAlwaysTextView(Context context, AttributeSet attrs,
			int defStyle) {
		super(context, attrs, defStyle);
	}

	@Override
	protected void onFocusChanged(boolean focused, int direction,
			Rect previouslyFocusedRect) {
		if (focused)
			super.onFocusChanged(focused, direction, previouslyFocusedRect);
	}

	@Override
	public void onWindowFocusChanged(boolean focused) {
		if (focused)
			super.onWindowFocusChanged(focused);
	}

	@Override
	public boolean isFocused() {
		return true;
	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		if (isFirstDraw) {// 文字宽度只需获取一次就可以了
			getTextWidth();
			firstScrollX = getScrollX(); // 起始位置不一定为0,改变内容后会变，需重新赋值
			currentScrollX = firstScrollX;
			mWidth = this.getWidth();
			endX = firstScrollX + textWidth - mWidth / 2;
			isFirstDraw = false;
		}
	}

	/** 每次滚动几点 */

	public void setSpeed(int sp) {
		speed = sp;
	}

	/** 滚动间隔时间,毫秒 */

	public void setDelayed(int delay) {
		delayed = delay;
	}

	/**
	 * 获取文字宽度
	 */
	private void getTextWidth() {
		Paint paint = this.getPaint();
		String str = this.getText().toString();
		textWidth = (int) paint.measureText(str);
	}

	public void run() {
		// currentScrollX += 1;// 滚动速度
		currentScrollX += speed;// 滚动速度,每次滚动几点
		scrollTo(currentScrollX, 0);
		if (isStop) {
			return;
		}
		// 从头开始
		if (currentScrollX >= endX) {
			// scrollTo(0, 0);
			// currentScrollX = 0; //原文重置为0,发现控件所放的位置不同，初始位置不一定为0
			scrollTo(firstScrollX, 0);
			currentScrollX = firstScrollX;
			postDelayed(this, 4000);
		} else {
			postDelayed(this, delayed);
		}
	}

	/** 开始滚动 */
	public void startScroll() {
		isStop = false;
		this.removeCallbacks(this);
		postDelayed(this, 4000);
	}

	/** 停止滚动 */
	public void stopScroll() {
		isStop = true;
	}

	/** 从头开始滚动 */
	public void scrollFromBeginning() {
		currentScrollX = 0;
		startScroll();
	}
}
