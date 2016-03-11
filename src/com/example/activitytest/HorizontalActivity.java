package com.example.activitytest;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.SystemClock;
import android.text.TextUtils.TruncateAt;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.view.View.OnKeyListener;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.RelativeLayout;
import android.widget.Scroller;
import android.widget.TextView;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.util.LogUtils;
import com.lidroid.xutils.view.annotation.ViewInject;

public class HorizontalActivity extends BaseActivity {
	private LinearLayout ll_container;
	private static final int PAGE_SIZE = 4;
	@ViewInject(R.id.horizontalview)
	private MyHorizontalView horizontalScrollView;
//	@ViewInject(R.id.ll_foucs_bottom)
//	private LinearLayout ll_focus_bottom;
	TextView tv_title;
	ImageView iv_photo;
	ImageView iv_border;
	private RelativeLayout rl_root;
	private Scroller scroller;
	private int times1 = 1;
	private int times2 = 1;
	/**移动时的总时间*/
	private long moveTotalTime = 500;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_horizontal);
		ViewUtils.inject(this);
		initView();
		initData();
	}
	@Override
	public void onWindowFocusChanged(boolean hasFocus) {
		super.onWindowFocusChanged(hasFocus);
//		initData();
	}
	private void initView() {
		ll_container = (LinearLayout) findViewById(R.id.ll_container);
	}

	private void initData() {
		scroller = new Scroller(this);
//		createNavigationViewOfBottom(20);
//		View view;
		for(int i=0; i<20; i++){
			final ActivityAreaItem item = new ActivityAreaItem(this);
			final LinearLayout contentView = item.getContentView();
			final LinearLayout ll_root = item.getRootView();
			final ImageView iv_icon = item.getIconView();
			iv_icon.setImageResource(Constant.horizontal_icons[i]);
			final TextView tv_title = item.getTitleView();
			item.setFocusable(true);
			item.setId(i);
			item.setOnFocusChangeListener(new OnFocusChangeListener() {
				private int width;
				private int height;
				
				@Override
				public void onFocusChange(View v, boolean hasFocus) {
					
					if(hasFocus){
						//设置内部边距是为了让添加的红色背景边框在获取到焦点时能够显示出来。
						contentView.setPadding(5, 5, 5, 5);
						contentView.setBackgroundResource(R.drawable.border_rectangle_red);
						width = item.getWidth();
						height = item.getHeight();
						//重新设置控件的尺寸，形成放大或者缩小的效果(有待优化)。
						Log.i("尺寸", "width=="+width+"=height="+height);
						ll_root.setLayoutParams(new LinearLayout.LayoutParams((int) (width*1.1f), (int) (height*1.1f)));
						tv_title.setTextSize(21*1.1f);
						tv_title.setSingleLine();
						tv_title.setEllipsize(TruncateAt.MARQUEE);
						tv_title.setMarqueeRepeatLimit(100);
						String description = "它管6123";//71458123674567896789它是对是错，快不快乐由我，听从自己内心的选择，哪怕前管它是1234567891011121314151管它是对是错，快不快乐由我，听从自己内心的选择，哪怕前方是没有尽头的坎坷";
						try {
							if(description!=null&&!description.equals("")){
								item.showPopupWindow(item.getContentView(),description);
//								LogUtils.i("没有展现出来");
//								item.getDescription().setText(description);
//								item.getDescription().setVisibility(View.VISIBLE);
							}else{
//								item.getDescription().setVisibility(View.INVISIBLE);
								item.dismissPopupWindow();
							}
						} catch (Exception e) {
						}
					}else{
						ll_root.setLayoutParams(new LinearLayout.LayoutParams(width, height));
						contentView.setBackgroundResource(0);
						contentView.setPadding(0, 0, 0, 0);
						tv_title.setSingleLine();
						tv_title.setEllipsize(TruncateAt.END);
						tv_title.setTextSize(21);
						item.dismissPopupWindow();
					}
				}
			});
			item.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
				}
			});
			item.setOnKeyListener(new OnKeyListener() {
				
				@SuppressLint("NewApi")
				@Override
				public boolean onKey(View v, int keyCode, KeyEvent event) {
					int ui = item.getWindowSystemUiVisibility();
					int edge = item.getHorizontalFadingEdgeLength();
					int right = item.getRight();
					int[] arr1 = new int[2];
					int[] arr2 = new int[2];
					int[] arr3 = new int[3];
					item.getLocationInWindow(arr1);
					item.getLocationOnScreen(arr2);
					contentView.getLocationOnScreen(arr3);
					int max = horizontalScrollView.getMaxScrollAmount();
					if(event.getAction()==KeyEvent.ACTION_DOWN){
						if(keyCode==KeyEvent.KEYCODE_DPAD_RIGHT){
							int delta = Math.abs(arr2[0]+item.getWidth()-UIUtil.getScreenResolution().get(0));
							LogUtils.i("detal=="+delta+"**width=="+Math.round(item.getWidth()/1.1));
							if(delta<=Math.round(item.getWidth()/1.1)){
								horizontalScrollView.moveTo(MyHorizontalView.TO_RIGHT, (int) (arr2[0]+Math.round(item.getWidth()/1.1)), 0, 2000);
								
								UIUtil.showToast("向右吧少年");
							}
//							LogUtils.i("ui=="+ui+"edge=="+edge+"right=="+right+"arr1=="+arr1[0]+"arr2=="+arr2[0]+"max=="+max+"getx=="+item.getX());
//							LogUtils.i("horizontalScrollView.getFocusedChild()=="+((ActivityAreaItem)ll_container.getFocusedChild()).getX());
//							float deltaX = Math.abs(((ActivityAreaItem)ll_container.getFocusedChild()).getX()+item.getWidth()-UIUtil.getScreenResolution().get(0));
//							
//							if(deltaX<item.getWidth()){
//								horizontalScrollView.moveTo(MyHorizontalView.TO_RIGHT, (int) Math.abs(((ActivityAreaItem)ll_container.getFocusedChild()).getX()+item.getWidth()), 0, 2000);
//								UIUtil.showToast("移动向右");
//							}
							
//							LogUtils.i("888==="+item.getX());
//							if(Math.abs(item.getX()+item.getWidth()-UIUtil.getScreenResolution().get(0)*times1)<item.getWidth()){
//								horizontalScrollView.moveTo(MyHorizontalView.TO_RIGHT, UIUtil.px2dip(HorizontalActivity.this,ll_root.getWidth())*PAGE_SIZE+476, 0, 2000);
//								times1++;
//								horizontalScrollView.get
//							}
							
//							if((item.getId()+1)%PAGE_SIZE==0){
////								horizontalScrollView.moveTo(MyHorizontalView.TO_RIGHT, (int) (UIUtil.px2dip(HorizontalActivity.this, ll_root.getWidth())*PAGE_SIZE-1+ll_root.getWidth()*1.1f), 0, 2000);
////								LogUtils.i("distanceXX==="+UIUtil.px2dip(HorizontalActivity.this, ll_root.getWidth())*PAGE_SIZE+"density==="+UIUtil.getDensitydpi()+"width==="+UIUtil.getScreenResolution().get(0));
//								horizontalScrollView.moveTo(MyHorizontalView.TO_RIGHT, UIUtil.px2dip(HorizontalActivity.this,ll_root.getWidth())*PAGE_SIZE+476, 0, 2000);
////								horizontalScrollView.pageScroll(MyHorizontalView.FOCUS_RIGHT);
//								LogUtils.i("distance=="+(UIUtil.getScreenResolution().get(0)*times-item.getX())+"x=="+item.getX());
//								times++;
//							}
						}else if(keyCode==KeyEvent.KEYCODE_DPAD_LEFT){
							if(arr2[0]<Math.round(item.getWidth()/1.1)){
								horizontalScrollView.moveTo(MyHorizontalView.TO_LEFT, UIUtil.getScreenResolution().get(0)-(int)Math.round(arr3[0]*1.1)-16,0,2000);
								UIUtil.showToast("左边距=="+((int)Math.round(arr3[0])));
							}
//							if(Math.abs(((ActivityAreaItem)ll_container.getFocusedChild()).getX())<item.getWidth()+8){
//								UIUtil.showToast("向左了");
////								horizontalScrollView.moveTo(MyHorizontalView.TO_RIGHT, UIUtil.px2dip(HorizontalActivity.this,ll_root.getWidth())*PAGE_SIZE+476, 0, 2000);
////								times2++;
//							}
//							if((item.getId()+1)%PAGE_SIZE==0){
////								horizontalScrollView.moveTo(MyHorizontalView.TO_LEFT, UIUtil.px2dip(HorizontalActivity.this,ll_root.getWidth())*PAGE_SIZE+476, 0, 2000);
//							}
						}
					}
					LogUtils.i("horizontal=="+horizontalScrollView.getScrollX());
					return false;
				}
			});
			ll_container.addView(item);
		}
	}
	/**
	 * 创建页面底部导航view
	 * @param size
	 */
//	private void createNavigationViewOfBottom(int size) {
//		if(ll_focus_bottom!=null){
//			ll_focus_bottom.removeAllViews();
//		}
//		int viewSize = (int) Math.ceil(size/(PAGE_SIZE + 0.0));
//		for(int i=0; i<viewSize; i++){
//			ImageView iv_focus = new ImageView(this);
//			iv_focus.setImageResource(R.drawable.unfocus_circle);
//			ll_focus_bottom.addView(iv_focus);
//		}
//	}
}
