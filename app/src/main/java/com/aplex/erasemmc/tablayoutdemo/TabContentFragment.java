package com.aplex.erasemmc.tablayoutdemo;

import android.support.v4.app.Fragment;

/**
 * Created by chengmz on 2016/10/25.
 */
public class TabContentFragment{
    public static Fragment newInstance(String s){
        return new MyFragment();
    }
}
