package com.example.activitytest;

import com.lidroid.xutils.util.LogUtils;

import android.content.Context;
import android.util.AttributeSet;
import android.view.ViewTreeObserver;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.widget.TextView;

public class MyTextView extends TextView {
	private SizeChangeListener sizeChangeListener;

	public MyTextView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

	public MyTextView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public MyTextView(Context context) {
		super(context);
	}
	
	public void setSizeChangeListener(SizeChangeListener sizeChangeListener) {
		this.sizeChangeListener = sizeChangeListener;
	}
	@Override
	protected void onSizeChanged(int w, int h, int oldw, int oldh) {
		sizeChangeListener.onSizeChanged(w, h, oldw, oldh);
		super.onSizeChanged(w, h, oldw, oldh);
	}
	public int getXOff(){
		int xOff = 0;
		ViewTreeObserver observer = this.getViewTreeObserver();
		observer.addOnGlobalLayoutListener(new OnGlobalLayoutListener() {
			
			@SuppressWarnings("deprecation")
			@Override
			public void onGlobalLayout() {
				MyTextView.this.getViewTreeObserver().removeGlobalOnLayoutListener(this);
				LogUtils.i("哈哈=="+getWidth());
			}
		});
		return xOff;
		
	}
	public interface SizeChangeListener{
		public abstract void onSizeChanged(int w,int h,int oldW,int oldH);
	}
}
