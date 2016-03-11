package com.example.activitytest;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

/**
 * 主要是处理UI时用到的简单工具。
 * @author MrTaoge
 *
 */
public class UIUtil {
	/**吐司开始弹出的时间*/
	private static long toastStartTime;
	/**吐司弹出结束的时间*/
	private static long toastEndTime;
	/**吐司是否已经弹出过*/
	private static boolean hasShow = false;
	
	public static Context getContext() {
		return MyApplication.getContext();
	}

	public static Resources getreResources() {
		return getContext().getResources();
	}

	/**
	 * 用xml的资源ID将其转化为view对象。
	 * @param resId
	 */
	public static View inflateView(int resId) {
		return View.inflate(getContext(), resId, null);
	}

	/**
	 * 根据资源id的值获取dimen.xml中相应的值。
	 * @param resId
	 * @return
	 */
	public static int getDimenValue(int resId) {
		return (int) getContext().getResources().getDimension(resId);
	}

	public static int dip2px(Context context, float dipValue) {
		final float scale = context.getResources().getDisplayMetrics().density;
		return (int) (dipValue * scale + 0.5f);
	}

	public static int px2dip(Context context, float pxValue) {
		final float scale = context.getResources().getDisplayMetrics().density;
		return (int) (pxValue / scale + 0.5f);
	}
	/**
	 * 自定义吐司。
	 * <p>主要是控制一定时间内不重复弹出吐司，以免造成多次触发弹吐司事件时不断的弹出吐司，造成页面上不好的视觉体验。</p>
	 * @param content
	 */
	public static void showToast(String content){
		toastStartTime = System.currentTimeMillis();
		if(!hasShow){
			Toast.makeText(getContext(), content, 0).show();
			hasShow = true;
		}
		if(toastStartTime-toastEndTime>2000){//如果上一次弹吐司的时间距离下次弹吐司的时间不足两秒，那么不要弹出吐司。
			Toast.makeText(getContext(), content, 0).show();
		}
		toastEndTime = System.currentTimeMillis();
	}
	/**
	 * 根据字符串的资源Id弹出吐司。
	 * @param resId
	 */
	public static void showToast(int resId){
		toastStartTime = System.currentTimeMillis();
		if(!hasShow){
			Toast.makeText(getContext(), getreResources().getString(resId), 0).show();
			hasShow = true;
		}
		if(toastStartTime-toastEndTime>2000){
			Toast.makeText(getContext(), getreResources().getString(resId), 0).show();
		}
		toastEndTime = System.currentTimeMillis();
	}
	/**
	 * 点击条目，按下红色消失，松开红色出现的方法
	 * @param event
	 * @param view
	 */
	public static void changeRed(KeyEvent event, View view) {
		if (event.getAction() == KeyEvent.ACTION_DOWN&& event.getKeyCode() == KeyEvent.KEYCODE_DPAD_CENTER) {

			Log.i("liveurl", "我摁下了");
			view.setVisibility(View.INVISIBLE);
		} else {
			view.setVisibility(View.VISIBLE);
		}
		// if (event.getAction() == KeyEvent.ACTION_UP
		// && event.getAction() != KeyEvent.KEYCODE_BACK) {
		// Log.i("liveurl", "我松开了");
		// view.setVisibility(View.VISIBLE);
		// }
	}
	/**
	 * 展示图片的倒影。
	 * @param iv_source 要展示倒影的原图。
	 * @param iv_reflection 用来承载倒影图片的控件。
	 */
	public static void showReflection(ImageView iv_source,ImageView iv_reflection){
		if(iv_source!=null&&iv_reflection!=null){
			Bitmap reflectionBitmap = ImageReflect.createCutReflectedImage(ImageReflect.convertViewToBitmap(iv_source), 0);
			if(reflectionBitmap!=null){
				iv_reflection.setImageBitmap(reflectionBitmap);
			}else{
				Log.i("倒影", "倒影为空");
			}
		}else{
			Log.i("倒影", "倒影为空");
		}
	}
	/**
	 * 展示倒影的重载方法。
	 * @param bitmap
	 * @param iv_reflection
	 */
	public static void showReflection(Bitmap bitmap,ImageView iv_reflection){
		if(bitmap!=null&&iv_reflection!=null){
			//创建倒影
			Bitmap reflectionBitmap = ImageReflect.createCutReflectedImage(bitmap, 0);
			//用控件显示倒影。
			if(reflectionBitmap!=null){
				iv_reflection.setImageBitmap(reflectionBitmap);
			}else{
				Log.i("倒影", "倒影为空");
			}
		}else{
			Log.i("倒影", "倒影为空");
		}
	}
	/**
	 * 获取屏幕密度。
	 * @return
	 */
	public static int getDensitydpi(){
		return getreResources().getDisplayMetrics().densityDpi;
	}
	/**
	 * 获取屏幕分辨率(宽和高)
	 * @return
	 */
	public static List<Integer> getScreenResolution(){
		List<Integer> list = new ArrayList<Integer>();
		DisplayMetrics displayMetrics = getreResources().getDisplayMetrics();
		list.add(displayMetrics.widthPixels);
		list.add(displayMetrics.heightPixels);
		return list;
	}
}
