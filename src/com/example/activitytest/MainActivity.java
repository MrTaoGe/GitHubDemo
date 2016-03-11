package com.example.activitytest;

import com.lidroid.xutils.util.LogUtils;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends BaseActivity {

	private Button btn_open_2;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// setContentView(R.layout.activity_main);
		// TextView tv = new TextView(this);
		// tv.setText("我是第一个activity");
		// tv.setTextColor(Color.parseColor("#ffffff"));
		// // tv.setTextSize(20f);
		View view = View.inflate(this, R.layout.activity_main, null);
		ll_content.addView(view);
		btn_open_2 = (Button) view.findViewById(R.id.btn_open);
		btn_open_2.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				Intent intent = new Intent(MainActivity.this,
						SecondActivity.class);
				startActivity(intent);
			}
		});
		
		LogUtils.i("deviceInfo-->"+getDeviceInfo(this));
	}

	public static String getDeviceInfo(Context context) {
		  try{
		    org.json.JSONObject json = new org.json.JSONObject();
		    String device_id = android.provider.Settings.Secure.getString(context.getContentResolver(),android.provider.Settings.Secure.ANDROID_ID);

		    android.net.wifi.WifiManager wifi = (android.net.wifi.WifiManager) context.getSystemService(Context.WIFI_SERVICE);

		    String mac = wifi.getConnectionInfo().getMacAddress();
		    json.put("mac", mac);

		    if( android.text.TextUtils.isEmpty(device_id) ){
		      device_id = mac;
		    }

		    json.put("device_id", device_id);

		    return json.toString();
		  }catch(Exception e){
		    e.printStackTrace();
		  }
		  return null;
		}
}
