package com.example.activitytest;


import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils.TruncateAt;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.lidroid.xutils.util.LogUtils;

public class ActivityAreaItem2 extends LinearLayout {

	private LinearLayout ll_root,ll_parent;
	private ImageView iv_icon,iv_reflection;
	private ScrollAlwaysTextView tv_content;
	private PopupWindow popupWindow;
	private TextView tv_description;
	private int densityDPI;//屏幕密度
	/**X轴方向上的偏移量*/
	private int deltaX = 0;
	private int xOffSet = 0;
	
	private Handler handler = new Handler(){
		public void handleMessage(android.os.Message msg) {
			switch (msg.what) {
			case 0:
				int w = View.MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED);
				int h = View.MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED);
				tv_description.measure(w, h);
				xOffSet = tv_description.getMeasuredWidth();
				LogUtils.i("这是什么=="+xOffSet);
				break;

			default:
				break;
			}
		};
	};
	
	public ActivityAreaItem2(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		initData();
		initView(context);
	}

	public ActivityAreaItem2(Context context, AttributeSet attrs) {
		super(context, attrs);
		initData();
		initView(context);
	}

	public ActivityAreaItem2(Context context) {
		super(context);
		initData();
		initView(context);
//		tv_description = new TextView(context);
//		tv_description.setTextColor(getResources().getColor(R.color.white));
//		tv_description.setBackgroundResource(R.drawable.bubble_area_item);
//		tv_description.setSizeChangeListener(new SizeChangeListener() {
//			
//			@Override
//			public void onSizeChanged(int w, int h, int oldW, int oldH) {
//				xOffSet = w;
//				LogUtils.i("xOffSet**=="+xOffSet);
//			}
//		});
//		LogUtils.i("xOffSet**=="+xOffSet);
		if(densityDPI==240){
			tv_description.setMaxWidth(300);
			tv_description.setMaxHeight(100);
		}else if(densityDPI==160){
			tv_description.setMaxWidth(200);
			tv_description.setMaxHeight(67);
		}
//		tv_description.setTextSize(16);

		popupWindow = new PopupWindow(tv_description);
		popupWindow.setWidth(LayoutParams.WRAP_CONTENT);
		popupWindow.setHeight(LayoutParams.WRAP_CONTENT);
		popupWindow.setAnimationStyle(0);
	}

	private void initData() {
		densityDPI = UIUtil.getDensitydpi();
	}

	private void initView(Context context) {
		View view = View.inflate(getContext(), R.layout.item_enlarge2,this);
		ll_root = (LinearLayout) view.findViewById(R.id.ll_root_area2);
		ll_parent = (LinearLayout) view.findViewById(R.id.ll_parent_arem2);
		iv_icon = (ImageView) view.findViewById(R.id.iv_icon2);
		tv_content = (ScrollAlwaysTextView) view.findViewById(R.id.tv_content2);
		tv_content.setSingleLine();
		tv_content.setEllipsize(TruncateAt.END);
		iv_reflection = (ImageView) view.findViewById(R.id.iv_reflection2);
		tv_description = (TextView) view.findViewById(R.id.tv_description);
//		Bitmap bitmap = ImageReflect.createCutReflectedImage(ImageReflect.convertViewToBitmap(iv_icon), 0);
//		if(bitmap!=null){
//			iv_reflection.setImageBitmap(bitmap);
//		}else{
//			Log.i("倒影", "倒影为空");
//		}
	}
	/**
	 * 获取包裹图片和文字的父布局，以便于进行选中时的效果处理。
	 * @return
	 */
	public LinearLayout getContentView(){
		return ll_parent;
	}
	/**
	 * 提供自定义控件的根布局。
	 */
	public LinearLayout getRootView(){
		return ll_root;
	}
	/**
	 * 将条目的缩略图控件提供出去。
	 * @return
	 */
	public ImageView getIconView(){
		return iv_icon;
	}
	/**
	 * 倒影控件
	 * @return
	 */
	public ImageView getReflectionView(){
		return iv_reflection;
	}
	/**
	 * 将条目的说明文字控件提供出去。
	 * @return
	 */
	public ScrollAlwaysTextView getTitleView(){
		return tv_content;
	}
	/**
	 * 弹出popupwindow。
	 * @param item
	 * @param description
	 */
	public void showPopupWindow(View item,String description){
		try {
			if(popupWindow!=null&&!popupWindow.isShowing()){
				//将所有半角字符转化为全角。
				String fullCharacterStr = half2FullCharacter(description);
				tv_description.setText(fullCharacterStr);
				int lines = getDescriptionTextLines(fullCharacterStr);
				if(lines>=2){
					lines = 2;
				}
				int textLength = getTextLength(description);
				int xOff = -1;//popupwindow显示位置的横坐标。
				int yOff = -1;//popupwindow显示位置的纵坐标。
				if(densityDPI==240){
					yOff = 433;
				}else if(densityDPI==160){
					switch (lines) {
					case 0:
						yOff = 288;
						break;
					case 1:
						yOff = 280;
						break;
					case 2:
						yOff = 275;
						break;
					default:
						break;
					}
				}
				if(textLength>0&&textLength<=3){
					xOff = 0;
				}else if(textLength>3&&textLength<=6){
					xOff = 1;
					deltaX = 40;
				}else if(textLength>6&&textLength<=9){
					xOff = 2;
				}else{
					xOff = 3;
				}
				Message msg = Message.obtain();
				msg.what = 0;
				handler.sendMessage(msg);
				LogUtils.i("xOffSet===***++++"+xOffSet);
				popupWindow.showAsDropDown(item,(int) ((item.getWidth()*1.1f-117)/2), -(yOff+lines*23));
				
			}
		} catch (Exception e) {
		}
	}
	/**
	 * 关闭popupwindow。
	 */
	public void dismissPopupWindow(){
		try {
			if(popupWindow!=null&&popupWindow.isShowing()){
				popupWindow.dismiss();
			}
		} catch (Exception e) {
		}
	}
	public PopupWindow getPopupWindow(){
		return popupWindow;
	}
	
	/**
	 * 计算描述文字共几行。
	 * @param text
	 * @return
	 */
	public int getDescriptionTextLines(String text){
		//中文的个数
		int sum = chineseNum(text);
		//描述文字的初始长度。
		int initTextLength = text.length();
		LogUtils.i("text==**"+initTextLength);
		int lines = 0;
		LogUtils.i("sum==="+sum);
		lines =(Math.round((initTextLength-sum)/2)+sum)/11;
		return lines;
	}
	/**
	 * 半角字符转化为全角字符。
	 * @param text
	 * @return
	 */
	public String half2FullCharacter(String text){
		char[] charArray = text.toCharArray();
		for(int i=0; i<charArray.length; i++){
			if(charArray[i]==12288){
				charArray[i] = (char)32;
				continue;
			}
			if(charArray[i]>65280&&charArray[i]<65375){
				charArray[i] = (char)(charArray[i]-65248);
			}
		}
		return new String(charArray);
	}
	
	/**
	 * 计算字符串中包含中文的个数。
	 * @param text
	 * @return
	 */
	public static int chineseNum(String text){
		int sum = 0;
		char[] chaArr = text.toCharArray();
		for(int i=0; i<chaArr.length; i++){
			char c = chaArr[i];
			Character.UnicodeBlock ub = Character.UnicodeBlock.of(c);
			if(ub==Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS
					||ub==Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A
					||ub==Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION
					||ub==Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS
					||ub==Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS
					||ub==Character.UnicodeBlock.GENERAL_PUNCTUATION){
				sum++;
			}
		}
		return sum;
	}
	
	/**
	 * 描述文字的真实长度(以中文为标准，两个数字或字母的长度算一个长度)
	 * @param text
	 * @return
	 */
	public static int getTextLength(String text){
		//中文的个数
		int sum = chineseNum(text);
		//描述文字的初始长度。
		int initTextLength = text.length();
		//字母和数字二者加起来的长度
		int letterOrDigitLength = 0;
		//计算后的真实长度
		int realLength = 0;
		
//		//将字符串转化为字符数组，逐个判断是不是数字/字母。
//		char[] textArr = text.toCharArray();
//		for(int i=0; i<textArr.length; i++){
//			if(Character.isLetterOrDigit(textArr[i])){//用这个方法判断某一个字符是否是字母时会有问题，因为编码问题，中文也会被当成字母，所以返回true。
//				sum++;
//			}
//		}
		letterOrDigitLength = initTextLength-sum;
		realLength = letterOrDigitLength%2==0?letterOrDigitLength/2+sum:letterOrDigitLength/2+1+sum;
		return realLength;
	}
	
	public int getXoff(final View view,final TextView tv){
		ViewTreeObserver observer = tv.getViewTreeObserver();
		observer.addOnGlobalLayoutListener(new OnGlobalLayoutListener() {
			
			@Override
			public void onGlobalLayout() {
				tv.getViewTreeObserver().removeGlobalOnLayoutListener(this);
				xOffSet = (view.getWidth()-tv.getWidth())/2;
//				Message msg = Message.obtain();
//				msg.what = 0;
//				msg.arg1 = tv.getWidth();
//				
//				handler.sendMessage(msg);
			}
		});
		return xOffSet;
	}
	public TextView getDescription(){
		return tv_description;
	}
	public void showPopupwindow(){
		
	}
}
