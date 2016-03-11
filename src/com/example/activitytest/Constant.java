package com.example.activitytest;

import java.util.ArrayList;


/**
 * <strong>图片资源id集合。</strong>
 * <p>Created by Administrator on 2015/8/17.</p>
 */
public class Constant {
    //横向图片的资源id集合
    public static  int horizontal_icons[] = {R.drawable.h1,R.drawable.h2,R.drawable.h3,R.drawable.h4,R.drawable.h5,R.drawable.h6,R.drawable.h7,R.drawable.h8,R.drawable.h9,
            R.drawable.h10,R.drawable.h11,R.drawable.h12,R.drawable.h13,R.drawable.h14,R.drawable.h15,R.drawable.h16,R.drawable.h17,R.drawable.h18,R.drawable.h19,
            R.drawable.h20};
    
    
    public static  ArrayList<Integer> images = new ArrayList<Integer>();
    public static  ArrayList<Integer> horizontal_images = new ArrayList<Integer>();
    //将资源id装进集合。
    static {
        for (int i=0;i<horizontal_icons.length;i++){//将资源id循环加入到集合中。
            horizontal_images.add(horizontal_icons[i]);
        }
    }
}
