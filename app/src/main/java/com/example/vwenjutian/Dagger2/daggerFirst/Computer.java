package com.example.vwenjutian.Dagger2.daggerFirst;

import com.example.vwenjutian.Dagger2.bean.Display;
import com.example.vwenjutian.Dagger2.bean.Master;
import com.example.vwenjutian.Dagger2.bean.keyboard;

import javax.inject.Inject;

/**
 * Created by V.Wenju.Tian on 2016/10/28.
 */

public class Computer {
    @Inject
    Display display;
    @Inject
    keyboard keyboard;
    @Inject
    Master master;
    public Computer() {
        // DaggerComputerComponent.create().inject(this);
        DaggerComputerComponent.builder().computerModule(new ComputerModule("中国","联想")).build().inject(this);
        makeSalad(keyboard, display, master);

    }
    private void makeSalad(keyboard keyboard, Display display, Master master) {
        System.out.println("制造完成");
    }
    public static void main(String[] args) {
        new Computer();
    }
}
