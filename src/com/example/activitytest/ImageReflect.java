package com.example.activitytest;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.Shader.TileMode;
import android.view.View;

/**
 * 倒影生成
 * 
 * @author 王超
 * */
public class ImageReflect {
	private static int reflectImageHeight = 80;

	public static Bitmap convertViewToBitmap(View paramView) {
		paramView.measure(View.MeasureSpec.makeMeasureSpec(0, 0),View.MeasureSpec.makeMeasureSpec(0, 0));
		paramView.layout(0, 0, paramView.getMeasuredWidth(),paramView.getMeasuredHeight());
		paramView.buildDrawingCache();
		return paramView.getDrawingCache();
	}

	public static Bitmap createCutReflectedImage(Bitmap paramBitmap,int paramInt) {
		int i = -1;
		int j = -1;
		if(paramBitmap!=null){
			i = paramBitmap.getWidth();
			j = paramBitmap.getHeight();
		}
		Bitmap localBitmap2 = null;
//		System.out.println("倒影" + j + "======="
//				+ (paramInt + reflectImageHeight)
//				+ "================================");
		if (j <= paramInt + reflectImageHeight) {
			localBitmap2 = null;
		} else {
			Matrix localMatrix = new Matrix();
			localMatrix.preScale(1.0F, -1.0F);
			Bitmap localBitmap1 = Bitmap.createBitmap(paramBitmap, 0, j
					- reflectImageHeight - paramInt, i, reflectImageHeight,
					localMatrix, true);
			localBitmap2 = Bitmap.createBitmap(i, reflectImageHeight,
					Bitmap.Config.ARGB_8888);
			Canvas localCanvas = new Canvas(localBitmap2);
			localCanvas.drawBitmap(localBitmap1, 0.0F, 0.0F, null);
			LinearGradient localLinearGradient = new LinearGradient(0.0F, 0.0F,
					0.0F, localBitmap2.getHeight(), 0x70ffffff,
					Color.TRANSPARENT, TileMode.CLAMP);
			Paint localPaint = new Paint();
			localPaint.setShader(localLinearGradient);
			localPaint.setXfermode(new PorterDuffXfermode(Mode.DST_IN));
			localCanvas.drawRect(0.0F, 0.0F, i, localBitmap2.getHeight(),
					localPaint);
			if (!localBitmap1.isRecycled())
				localBitmap1.recycle();
			System.gc();
		}
		return localBitmap2;
	}

}