package com.example.activitytest;

import android.view.View;

public class PopupwindowArgs {
	private int xOff;
	private int yOff;
	private int lines;
	private View item;
	
	public int getxOff() {
		return xOff;
	}
	public void setxOff(int xOff) {
		this.xOff = xOff;
	}
	public int getyOff() {
		return yOff;
	}
	public void setyOff(int yOff) {
		this.yOff = yOff;
	}
	public int getLines() {
		return lines;
	}
	public void setLines(int lines) {
		this.lines = lines;
	}
	public View getItem() {
		return item;
	}
	public void setItem(View item) {
		this.item = item;
	}
	
}
