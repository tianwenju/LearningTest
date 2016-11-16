package com.example.vwenjutian.Dagger2.Raw;

import android.util.Log;

import com.example.vwenjutian.Dagger2.bean.Display;
import com.example.vwenjutian.Dagger2.bean.keyboard;
import com.example.vwenjutian.Dagger2.bean.Master;

/**
 * Created by V.Wenju.Tian on 2016/10/28.
 */

public class Computer0 {
    private keyboard keyboard;
    private Master saladSauce;
    private Display display;

    public Computer0() {
        display = new Display();
        keyboard = new keyboard();
        saladSauce = new Master();
        makeSalad(display, keyboard, saladSauce);
    }

    private void makeSalad(Display display, keyboard keyboard, Master saladSauce) {
        Log.e("TAG", "制作完成");
    }
}
