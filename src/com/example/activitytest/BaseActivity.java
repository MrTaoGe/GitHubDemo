package com.example.activitytest;

import com.umeng.analytics.MobclickAgent;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class BaseActivity extends Activity implements OnClickListener{
	protected LinearLayout ll_navigation;
	protected LinearLayout ll_content;
	private Button btn1,btn2,btn3;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_base);
		ll_navigation = (LinearLayout) findViewById(R.id.ll_navigation);
		ll_content = (LinearLayout) findViewById(R.id.ll_content);
		btn1 = (Button) findViewById(R.id.btn1);
		btn2 = (Button) findViewById(R.id.btn2);
		btn3 = (Button) findViewById(R.id.btn3);
		btn1.setOnClickListener(this);
		btn2.setOnClickListener(this);
		btn3.setOnClickListener(this);
		TextView tv = new TextView(this);
		tv.setText("这边是导航栏");
		tv.setTextColor(Color.parseColor("#ffffff"));
		tv.setTextSize(20f);
		ll_navigation.addView(tv);
	}
	
	@Override
	protected void onResume() {
		super.onResume();
		MobclickAgent.onResume(this);
	}
	public void onClick(View v) {
		Intent intent = null;
		switch (v.getId()) {
		case R.id.btn1:
			intent = new Intent(BaseActivity.this,MainActivity.class);
			break;
		case R.id.btn2:
			intent = new Intent(BaseActivity.this,SecondActivity.class);
			break;
		case R.id.btn3:
			intent = new Intent(BaseActivity.this,ThirdActivity.class);
			break;
		default:
			break;
		}
		startActivity(intent);
	}
	@Override
	protected void onPause() {
		super.onPause();
		MobclickAgent.onPause(this);
	}
}
