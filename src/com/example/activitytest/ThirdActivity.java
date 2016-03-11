package com.example.activitytest;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;

public class ThirdActivity extends BaseActivity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// setContentView(R.layout.activity_main);
		TextView tv = new TextView(this);
		tv.setText("我是第三个activity");
		tv.setTextColor(Color.parseColor("#ffffff"));
		tv.setTextSize(20f);
		Button btn = new Button(this);
		btn.setLayoutParams(new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
		btn.setText("打开horizontalView");
		btn.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				Intent intent = new Intent(ThirdActivity.this,HorizontalActivity.class);
				startActivity(intent);
			}
		});
		ImageView iv = new ImageView(this);
		iv.setImageResource(R.drawable.scrollbar);
		ll_content.addView(tv);
		ll_content.addView(btn);
		ll_content.addView(iv);
	}
}
