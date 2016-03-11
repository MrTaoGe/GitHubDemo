package com.example.activitytest;

import java.util.ArrayList;
import java.util.List;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.AdapterViewFlipper;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.ViewFlipper;

public class SecondActivity extends BaseActivity {
	private Button btn_open_3,btn_open_window;
	private PopupWindow window;
	private TextView contentView;
	private MarqueeTextView tv_customer;
	private MarqueeText tv_customer2;
	private MarqueeText2 tv_customer3;
	private ScrollAlwaysTextView tv_customer4;
	private AdapterViewFlipper flipper;
	private ViewFlippterAdapter adapter;
	private List<String> dataList;
	private ViewFlipper viewFlipper;
	private TextView tv_info;
	private TextView tv_stretchy;
	private Handler handler = new Handler(){
		public void handleMessage(android.os.Message msg) {
			
		};
	};
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		createPopupwindow();
		View view = View.inflate(this, R.layout.activity_second, null);
		ll_content.addView(view);
		btn_open_3 = (Button) view.findViewById(R.id.btn_open_3);
		btn_open_window = (Button) view.findViewById(R.id.btn_open_window);
		tv_customer = (MarqueeTextView) view.findViewById(R.id.tv_customer);
		tv_customer2 = (MarqueeText) view.findViewById(R.id.tv_customer2);
		tv_customer3 = (MarqueeText2) view.findViewById(R.id.tv_customer3);
		viewFlipper = (ViewFlipper) view.findViewById(R.id.vf_second);
		tv_info = (TextView) view.findViewById(R.id.tv_second);
		tv_stretchy = (TextView) view.findViewById(R.id.tv_stretchy);
//		tv_info.setLayoutParams(new ViewGroup.LayoutParams(0, 0));
		dataList = new ArrayList<String>();
		dataList.add("跨年狂欢夜，豪礼送不停");
		dataList.add("今晚8点，暴雪嘉年华专区星际争霸决赛火热开服");
		dataList.add("新年新生，新年好");
		dataList.add("2016马上就要来了，2015我想要留下点什么，是什么呢？");
		dataList.add("兄弟姐妹向前冲，少说多做就是赢！");
//		viewFlipper.setInAnimation(this,R.anim.push_up_in);
//		viewFlipper.setOutAnimation(this,R.anim.push_up_out);
//		for(int i=0; i<dataList.size(); i++){
//			tv_info.setText(dataList.get(i));
//		}
		
		tv_customer4 = (ScrollAlwaysTextView) view.findViewById(R.id.tv_customer4);
//		for(int i=0; i<5; i++){
//			dataList.add("测试文字测试文字测试文字测试文字测试文字测试文字"+i);
//		}
		flipper = (AdapterViewFlipper) view.findViewById(R.id.avf);
		if(adapter==null){
			adapter = new ViewFlippterAdapter();
			flipper.setAdapter(adapter);
		}else{
			adapter.notifyDataSetChanged();
		}
//		ObjectAnimator animator = ObjectAnimator.of
//		flipper.setOutAnimation(this,R.anim.fragment_left_exit);
//		flipper.setInAnimation(this, R.anim.fragment_left_enter);
		ObjectAnimator animator1 = ObjectAnimator.ofFloat(flipper, "translationY", 100,0);
		animator1.setDuration(1000);
		
//		animator1.addUpdateListener(new AnimatorUpdateListener() {
//			
//			public void onAnimationUpdate(ValueAnimator animation) {
//				
//				Toast.makeText(SecondActivity.this, "动画改变了", 0).show();
//			}
//		});
		ObjectAnimator animator2 = ObjectAnimator.ofFloat(flipper, "translationY", 0,-100);
		animator2.setDuration(1000);
//		flipper.
		flipper.setInAnimation(animator1);
		flipper.setOutAnimation(animator2);
		
		handler.post(new Runnable() {
			
			int j = 0;
			
			public void run() {
//				((MarqueeText2)flipper.getCurrentView().findViewById(R.id.srcolltext)).stopScroll();
//				flipper.showNext();
//				flipper.showPrevious();
//				flipper.
				if(j==dataList.size()){
					j=0;
				}
				if(j<dataList.size()){
				tv_stretchy.setText(dataList.get(j));
//					TextView tvv = new TextView(SecondActivity.this);
//					tvv.setText(dataList.get(j));
//					tvv.setTextColor(Color.parseColor("#ffffff"));
//					tvv.setTextSize(20);
//					tvv.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
////					tv_info.setText(dataList.get(j));
////					tv_info.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
//					tvv.setBackgroundResource(R.drawable.info);
//					viewFlipper.addView(tvv);
				}
//				viewFlipper.startFlipping();
//				flipper.stopFlipping();
				handler.postDelayed(this, 12000);
//				((MarqueeText2)flipper.getCurrentView().findViewById(R.id.srcolltext)).startScroll();
//				flipper.startFlipping();
				j++;
			}
		});
//		flipper.setAutoStart(true);
		btn_open_3.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				Intent intent = new Intent(SecondActivity.this,ThirdActivity.class);
				startActivity(intent);
			}
		});
		btn_open_window.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				showPopupWindow();
			}
		});
		
		tv_customer.setText("这是自定义的能控制速滚动度的textview这是自定义的能控制速滚动度的textview");
		tv_customer.setTextSize(20);
		tv_customer.setTextColor(Color.parseColor("#ffffff"));
		tv_customer.setSpeed(10);
		tv_customer.setDelayed(100);
		tv_customer.startScroll();
		
		tv_customer2.setText("这是自定义的能控制速滚动度的textview2222这是自定义的能控制速滚动度的textview2222");
		tv_customer2.setTextSize(20);
		tv_customer2.setTextColor(Color.parseColor("#ffffff"));
		tv_customer2.startScroll();
		
		tv_customer3.setText("这是自定义的能控制速滚动度的textview3333这是自定义的能控制速滚动度的textview3333");
		tv_customer3.setTextSize(20);
		tv_customer3.setTextColor(Color.parseColor("#ffffff"));
		tv_customer3.startScroll();
		
		tv_customer4.setText("这是自定义的能控制速滚动度的textview3333这是自定义的能控制速滚动度的textview4444");
		tv_customer4.setTextSize(20);
		tv_customer4.setTextColor(Color.parseColor("#ffffff"));
		
		
	}
	private void createPopupwindow() {
		
	}
	@Override
	public void onWindowFocusChanged(boolean hasFocus) {
		super.onWindowFocusChanged(hasFocus);
		contentView = new TextView(this);
		contentView.setText("popupwindow的使用");
		contentView.setTextSize(20);
		contentView.setTextColor(Color.parseColor("#ffffff"));
		window = new PopupWindow(contentView, LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, false);
		window.setAnimationStyle(0);
		window.showAsDropDown(contentView);
	}
	protected void showPopupWindow() {
		contentView = new TextView(this);
		contentView.setText("popupwindow的使用");
		contentView.setTextSize(20);
		contentView.setTextColor(Color.parseColor("#ffffff"));
		window = new PopupWindow(contentView, LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, false);
		window.setAnimationStyle(0);
		window.showAsDropDown(contentView);
		
	}
	@Override
	protected void onStart() {
		super.onStart();
//		window.showAsDropDown(contentView);
	}
	@Override
	protected void onResume() {
		super.onResume();
	}
	class ViewFlippterAdapter extends BaseAdapter{

		public int getCount() {
			return dataList.size();
		}

		public Object getItem(int position) {
			return position;
		}

		public long getItemId(int position) {
			return position;
		}

		public View getView(int position, View convertView, ViewGroup parent) {
			if(convertView==null){
				convertView = View.inflate(SecondActivity.this, R.layout.item_textview, null);
			}
			TextView tv = (TextView) convertView.findViewById(R.id.srcolltext);
			tv.setText(dataList.get(position));
			return convertView;
		}
	}
	
//	protected void showTitleOrNot(boolean hasFocus) {
//		final RelativeLayout.LayoutParams params = (android.widget.RelativeLayout.LayoutParams) ll_parent
//				.getLayoutParams();
//		// 获取条目展开后的高度。
//		int targetedHeight = getOriginalHeight();
//		// 条目隐藏时的高度。
//		int hiddenHeight = 480;
//		if (hasFocus) {
//			targetedHeight = getOriginalHeight();
//			hiddenHeight = 480;
//			iv_border.setVisibility(View.VISIBLE);
//		} else {
//			targetedHeight = 480;
//			hiddenHeight = getOriginalHeight();
//			iv_border.setVisibility(View.INVISIBLE);
//		}
//		ValueAnimator animator = ValueAnimator.ofInt(hiddenHeight,
//				targetedHeight);
//		animator.addUpdateListener(new AnimatorUpdateListener() {
//
//			@Override
//			public void onAnimationUpdate(ValueAnimator animation) {
				// 不断获取变化中的高度给控件进行赋值，形成动画效果。
//				int changeHeight = (Integer) animation.getAnimatedValue();
//				params.height = changeHeight;
//				ll_parent.setLayoutParams(params);
//			}
//		});
//		animator.setDuration(500);
//		animator.start();
//	}
//	private int getOriginalHeight() {
//		int width = tv_title.getMeasuredWidth();// 获取控件测量的宽度。
//		// 把当前控件的高度改成包裹内容。
//		ll_parent.getLayoutParams().height = RelativeLayout.LayoutParams.WRAP_CONTENT;
//		// 生成测量的标准
//		int measuredWidth = MeasureSpec.makeMeasureSpec(width,
//				MeasureSpec.EXACTLY);
//		int measuredHeight = MeasureSpec.makeMeasureSpec(1000,
//				MeasureSpec.AT_MOST);
//		// 重新测量控件尺寸。
//		ll_parent.measure(measuredWidth, measuredHeight);
//		// 返回新的测量值
//		return ll_parent.getMeasuredHeight();
//	}
}
